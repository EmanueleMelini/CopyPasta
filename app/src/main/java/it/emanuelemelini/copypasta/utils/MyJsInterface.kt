package it.emanuelemelini.copypasta.utils

import android.content.Context
import android.webkit.JavascriptInterface

class MyJsInterface(private var ctx: Context) {

    @JavascriptInterface
    fun logout(reason: String) {
        if (reason.lowercase().contentEquals("logout")) {
            RunnablesUI.goLogin(ctx)
            ToastUtils.simpleToast(ctx, "Logout")
        }
    }

}