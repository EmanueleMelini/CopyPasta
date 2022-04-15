package it.emanuelemelini.copypasta.utils

import android.content.Context
import androidx.biometric.BiometricManager

class FingerPrintCheck {

    companion object {

        fun check(c: Context): Boolean {
            val biometricManager = BiometricManager.from(c)
            return biometricManager.canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS
        }

    }

}