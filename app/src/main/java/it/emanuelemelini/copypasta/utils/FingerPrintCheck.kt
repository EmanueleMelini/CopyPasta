package it.emanuelemelini.copypasta.utils;

import android.content.Context;

import androidx.biometric.BiometricManager;

public class FingerPrintCheck {

    public static boolean check(Context c) {
        BiometricManager biometricManager = androidx.biometric.BiometricManager.from(c);
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS;
    }

}
