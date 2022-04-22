package it.emanuelemelini.copypasta.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import io.realm.Realm
import it.emanuelemelini.copypasta.R
import it.emanuelemelini.copypasta.http.APIClient
import it.emanuelemelini.copypasta.http.APIInterface
import it.emanuelemelini.copypasta.http.BaseResponse
import it.emanuelemelini.copypasta.http.login.BaseLoginResponse
import it.emanuelemelini.copypasta.http.login.User
import it.emanuelemelini.copypasta.model.LoginModel
import it.emanuelemelini.copypasta.realm.MyHelper
import it.emanuelemelini.copypasta.utils.FingerPrintCheck
import it.emanuelemelini.copypasta.utils.RunnablesUI
import it.emanuelemelini.copypasta.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {

    private lateinit var loginFinger: Button
    private lateinit var loginBtn: Button
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var progress: ProgressBar

    private var first: Boolean = false
    private lateinit var helper: MyHelper
    private lateinit var realm: Realm
    private lateinit var apiInterface: APIInterface
    private var fingerprint: Boolean = false
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: PromptInfo
    private lateinit var mContext: Context

    //TODO: think about remove native login or change login api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginFinger = findViewById(R.id.fingerprint_button)
        loginBtn = findViewById(R.id.login_button)
        username = findViewById(R.id.login_username)
        password = findViewById(R.id.login_password)
        progress = findViewById(R.id.login_progress_bar)

        mContext = this

        apiInterface = APIClient.getClient(mContext).create(APIInterface::class.java)

        realm = Realm.getDefaultInstance()
        helper = MyHelper(realm)
        helper.saveLoginFromDB()
        if (realm.where(LoginModel::class.java).findFirst() == null) {
            first = true
        }


        fingerprint = FingerPrintCheck.check(mContext)
        executor = ContextCompat.getMainExecutor(mContext)
        biometricPrompt = BiometricPrompt(this@LoginActivity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                ToastUtils.simpleToast(mContext, "Fingerprint authentication error")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                helper.saveLoginFromDB()
                val lg = helper.getLogin()
                if (lg != null)
                    login(lg.username, lg.password)
                ToastUtils.simpleToast(mContext, "Fingerprint authentication succeded")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                ToastUtils.simpleToast(mContext, "Fingerprint authentication failed!")
            }
        })
        promptInfo = PromptInfo.Builder()
            .setTitle("CopyPasta")
            .setDescription("Use your fingerprint to login ")
            .setNegativeButtonText("Cancel")
            .build()
    }

    override fun onResume() {
        super.onResume()

        RunnablesUI.visible(loginBtn)
        RunnablesUI.gone(progress, loginFinger)

        mContext = this

        if (fingerprint && !first) {
            biometricPrompt.authenticate(promptInfo)
            RunnablesUI.visible(loginFinger)
            loginFinger.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
        }

        loginBtn.setOnClickListener {
            if (username.text.toString().isNotBlank() && password.text.toString().isNotBlank()) {
                login(username.text.toString(), password.text.toString())
            }
        }

    }

    private fun login(usr: String, psw: String) {

        RunnablesUI.gone(loginBtn)
        RunnablesUI.visible(progress)

        /*var callLogin = apiInterface.loginOld(
            username = usr,
            password = psw
        )*/

        val callLogin = apiInterface.login(
            loginModel = LoginModel(
                username = usr,
                password = psw
            )
        )

        callLogin.enqueue(object : Callback<BaseLoginResponse> {
            override fun onResponse(call: Call<BaseLoginResponse>, response: Response<BaseLoginResponse>) {
                RunnablesUI.gone(progress)
                RunnablesUI.visible(loginBtn)
                val loginRsp = response.body()
                if (response.isSuccessful && loginRsp != null) {
                    when (loginRsp.error) {
                        0 -> {
                            println(loginRsp)
                            startActivity(Intent(mContext, WebviewActivity::class.java)
                                .putExtra("Login", loginRsp))
                            finish()
                        }
                        1, 2 -> {
                            ToastUtils.simpleToast(mContext, loginRsp.message)
                        }
                        else -> {
                            ToastUtils.unexpectedReturnToast(mContext)
                        }
                    }
                } else
                    ToastUtils.connErrorToast(mContext)
            }

            override fun onFailure(call: Call<BaseLoginResponse>, t: Throwable) {
                RunnablesUI.gone(progress)
                RunnablesUI.visible(loginBtn)
                t.printStackTrace()
                ToastUtils.connErrorToast(mContext)
            }

        })

    }

}