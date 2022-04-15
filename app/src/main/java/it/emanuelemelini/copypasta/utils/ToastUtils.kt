package it.emanuelemelini.copypasta.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.annotation.StringRes
import it.emanuelemelini.copypasta.R

class ToastUtils {

    companion object {

        const val SHORT: Int = 0
        const val LONG: Int = 1

        fun connErrorToast(ctx: Context) {
            Toast.makeText(ctx, R.string.conn_error, Toast.LENGTH_LONG).show()
        }

        fun connErrorToast(ctx: Context, @Duration l: Int) {
            Toast.makeText(ctx, R.string.conn_error, l).show()
        }

        fun unexpectedReturnToast(ctx: Context) {
            Toast.makeText(ctx, R.string.unexpected_return, Toast.LENGTH_LONG).show()
        }

        fun unexpectedReturnToast(ctx: Context, @Duration l: Int) {
            Toast.makeText(ctx, R.string.unexpected_return, l).show()
        }

        fun simpleToast(ctx: Context, s: String) {
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show()
        }

        fun simpleToast(ctx: Context, s: String, @Duration l: Int) {
            Toast.makeText(ctx, s, l).show()
        }

        fun simpleToast(ctx: Context, @StringRes s: Int) {
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show()
        }

        fun simpleToast(ctx: Context, @StringRes s: Int, @Duration l: Int) {
            Toast.makeText(ctx, s, l).show()
        }


        @IntDef(value = [SHORT, LONG])
        @Retention(AnnotationRetention.SOURCE)
        annotation class Duration

    }

}