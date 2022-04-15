package it.emanuelemelini.copypasta.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import io.realm.Realm
import it.emanuelemelini.copypasta.R
import it.emanuelemelini.copypasta.http.APIClient
import it.emanuelemelini.copypasta.http.APIInterface
import it.emanuelemelini.copypasta.http.BaseResponse
import it.emanuelemelini.copypasta.http.login.User
import it.emanuelemelini.copypasta.model.Login
import it.emanuelemelini.copypasta.realm.MyHelper
import it.emanuelemelini.copypasta.utils.FingerPrintCheck
import it.emanuelemelini.copypasta.utils.RunnablesUI
import it.emanuelemelini.copypasta.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import kotlin.math.log

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginFinger = findViewById(R.id.fingerprint_button)
        loginBtn = findViewById(R.id.login_button)
        username = findViewById(R.id.login_username)
        password = findViewById(R.id.login_password)
        progress = findViewById(R.id.login_progress_bar)

        apiInterface = APIClient.getClient(this).create(APIInterface::class.java)

        realm = Realm.getDefaultInstance()
        helper = MyHelper(realm)
        helper.saveLoginFromDB()
        if (realm.where(Login::class.java).findFirst() == null) {
            first = true
        }

        fingerprint = FingerPrintCheck.check(this)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this@LoginActivity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                helper.saveLoginFromDB()
                val lg = helper.getLogin()
                if (lg != null)
                    login(lg.username, lg.password)
                Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
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

        mContext = this

        if (fingerprint && !first) {
            biometricPrompt.authenticate(promptInfo)
            loginFinger.visibility = View.VISIBLE
            loginFinger.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
        } else {
            loginFinger.visibility = View.GONE
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

        val callLogin = apiInterface.login(
            username = usr,
            password = psw
        )

        callLogin.enqueue(object : Callback<BaseResponse<User>> {
            override fun onResponse(call: Call<BaseResponse<User>>, response: Response<BaseResponse<User>>) {
                RunnablesUI.gone(progress)
                RunnablesUI.visible(loginBtn)
                val loginRsp = response.body()
                if (response.isSuccessful && loginRsp != null) {
                    when (loginRsp.error) {
                        0 -> {

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

            override fun onFailure(call: Call<BaseResponse<User>>, t: Throwable) {
                RunnablesUI.gone(progress)
                RunnablesUI.visible(loginBtn)
                t.printStackTrace()
                ToastUtils.connErrorToast(mContext)
            }

        })

    }

}