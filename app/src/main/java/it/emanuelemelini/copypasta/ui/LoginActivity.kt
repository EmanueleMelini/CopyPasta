package it.emanuelemelini.copypasta.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

import io.realm.Realm;
import it.emanuelemelini.copypasta.R;
import it.emanuelemelini.copypasta.http.APIClient;
import it.emanuelemelini.copypasta.http.APIInterface;
import it.emanuelemelini.copypasta.model.Login;
import it.emanuelemelini.copypasta.realm.MyHelper;
import it.emanuelemelini.copypasta.utils.FingerPrintCheck;

public class LoginActivity extends AppCompatActivity {

    Button login_finger;
    Button login_btn;
    EditText username;
    EditText password;

    boolean first;
    Login login;
    MyHelper helper;
    Realm realm;
    APIInterface apiInterface;
    boolean fingerprint;
    Executor executor;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_finger = findViewById(R.id.fingerprint_button);
        login_btn = findViewById(R.id.login_button);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        apiInterface = APIClient.getClient(this).create(APIInterface.class);
        realm = Realm.getDefaultInstance();
        helper = new MyHelper(realm);
        helper.saveLoginFromDB();
        if (realm.where(Login.class).findFirst() == null) {
            first = true;
        }

        fingerprint = FingerPrintCheck.check(this);

        executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //call login
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("CopyPasta")
                .setDescription("Use your fingerprint to login ").setNegativeButtonText("Cancel").build();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(fingerprint && !first) {
            biometricPrompt.authenticate(promptInfo);
            login_finger.setVisibility(View.VISIBLE);
            login_finger.setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));
        } else {
            login_finger.setVisibility(View.GONE);
        }

        login_btn.setOnClickListener(v -> {

        });

        /*
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("CopyPasta", copy_text.getText());
        clipboard.setPrimaryClip(clip);
         */

    }
}