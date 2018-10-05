package br.com.matheus.chamada.view.intro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import java.util.Objects;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.ActivityLoginBinding;
import br.com.matheus.chamada.helper.PreferencesHelper;
import br.com.matheus.chamada.helper.Utils;
import br.com.matheus.chamada.model.response.ErrorResponse;
import br.com.matheus.chamada.model.response.LoginResponse;
import br.com.matheus.chamada.repository.ChamadaRepository;
import br.com.matheus.chamada.service.ChamadaResponse;
import br.com.matheus.chamada.view.main.MainActivity;

/**
 * Created by mathe on 04/11/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        configureEditText();
        configureSwitchTouch();

        binding.etPassword.setOnKeyListener((view, i, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                binding.btLogin.performClick();
                return true;
            }
            return false;
        });

        binding.btLogin.setOnClickListener(view -> {
            Utils.hideSoftKeyBoard(view, LoginActivity.this);
            if (!Utils.isOnline(LoginActivity.this)) {
                Toast.makeText(LoginActivity.this, R.string.noConnection, Toast.LENGTH_SHORT).show();
            } else if (!Utils.isValidEmail(binding.etLogin.getText())) {
                binding.tilLogin.setError(getResources().getString(R.string.invalidEmail));
            } else if (Objects.requireNonNull(binding.etPassword.getText()).toString().trim().length() < 6) {
                binding.tilLogin.setError("");
                binding.tilPassword.setError(getResources().getString(R.string.invalidPassword));
            } else {
                binding.tilPassword.setError("");
                login();
            }
        });
    }

    private void configureEditText() {
        Utils.clearError(binding.etLogin, binding.tilLogin);
        Utils.clearError(binding.etPassword, binding.tilPassword);
    }

    private void configureSwitchTouch() {
        FingerprintIdentify fingerprintIdentify = new FingerprintIdentify(this);
        if (fingerprintIdentify.isHardwareEnable() &&
                fingerprintIdentify.isRegisteredFingerprint()) {
            binding.swLogin.setVisibility(View.VISIBLE);
            binding.swLogin.setChecked(Hawk.get(PreferencesHelper.FINGERPRINT_CHECKED, false));
        }
    }

    private void login() {
        ChamadaRepository.login(getSupportFragmentManager(),
                Objects.requireNonNull(binding.etLogin.getText()).toString().trim(),
                Objects.requireNonNull(binding.etPassword.getText()).toString().trim(),
                new ChamadaResponse<LoginResponse>() {
                    @Override
                    public void onResponseSuccess(LoginResponse response) {
                        Toast.makeText(LoginActivity.this,
                                "Seja bem-vindo " + response.getUser().getName(),
                                Toast.LENGTH_SHORT).show();

                        storeUserData(binding.etLogin.getText().toString().trim(),
                                binding.etPassword.getText().toString().trim());

                        if (binding.swLogin.isChecked()) {
                            Toast.makeText(LoginActivity.this,
                                    R.string.touch_id_active,
                                    Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onResponseError(ErrorResponse error) {
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void storeUserData(String login, String password) {
        Hawk.put(PreferencesHelper.FINGERPRINT_CHECKED, binding.swLogin.isChecked());
        Hawk.put(PreferencesHelper.FINGERPRINT_LOGIN_USER, login);
        Hawk.put(login, password);
    }
}
