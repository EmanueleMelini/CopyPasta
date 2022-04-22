package it.emanuelemelini.copypasta.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import it.emanuelemelini.copypasta.R
import it.emanuelemelini.copypasta.http.login.BaseLoginResponse
import it.emanuelemelini.copypasta.utils.ConfigHelper
import it.emanuelemelini.copypasta.utils.MyJsInterface
import it.emanuelemelini.copypasta.utils.RunnablesUI
import it.emanuelemelini.copypasta.utils.ToastUtils

class WebviewActivity : AppCompatActivity() {

    private lateinit var webview: WebView

    private lateinit var mContext: Context
    private var loginRsp: BaseLoginResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webview = findViewById(R.id.webview_copy)

        mContext = this

        loginRsp = intent.extras?.getSerializable("Login") as BaseLoginResponse
        if (loginRsp == null)
            RunnablesUI.goLogin(mContext)

        println(loginRsp)

        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()
        webview.addJavascriptInterface(MyJsInterface(mContext), "Android")

    }

    override fun onResume() {
        super.onResume()

        mContext = this

        var url = ConfigHelper.getConfigValue(mContext, "app_url")

        url += "?IDutente=${loginRsp?.response?.IDuser}"
        println("URL: $url")

        if(url.lowercase().contentEquals("error"))
            ToastUtils.simpleToast(mContext, "Error")
        else
            webview.loadUrl(url)

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}