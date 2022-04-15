package it.emanuelemelini.copypasta.utils

import android.content.Context
import android.content.Intent
import android.view.View
import it.emanuelemelini.copypasta.ui.LoginActivity

class RunnablesUI {

    companion object {

        private lateinit var run: Runnable

        fun visible(vararg views: View) {

            run = Runnable {
                for (v in views) {
                    v.visibility = View.VISIBLE
                }
            }
            run.run()

        }

        fun gone(vararg views: View) {

            run = Runnable {
                for (v in views) {
                    v.visibility = View.GONE
                }
            }
            run.run()

        }

        fun goLogin(ctx: Context) {
            ctx.startActivity(
                Intent(ctx, LoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

    }

}