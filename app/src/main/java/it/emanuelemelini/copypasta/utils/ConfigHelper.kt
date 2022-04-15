package it.emanuelemelini.copypasta.utils

import android.content.Context
import android.content.res.Resources
import java.io.InputStream
import it.emanuelemelini.copypasta.R
import android.content.res.Resources.NotFoundException
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.util.*

class ConfigHelper {

    companion object {

        fun getConfigValue(context: Context, name: String): String {
            val resources = context.resources
            return try {
                val rawResource = resources.openRawResource(R.raw.config)
                val properties = Properties()
                properties.load(rawResource)
                properties.getProperty(name)
            } catch (e: Exception) {
                e.printStackTrace()
                "Error"
            }
        }

    }

}