package br.com.matheus.chamada.view.intro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.orhanobut.hawk.Hawk;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.ActivityLoginFingerprintBinding;
import br.com.matheus.chamada.helper.PreferencesHelper;
import br.com.matheus.chamada.helper.Utils;
import br.com.matheus.chamada.model.response.ErrorResponse;
import br.com.matheus.chamada.model.response.LoginResponse;
import br.com.matheus.chamada.repository.ChamadaRepository;
import br.com.matheus.chamada.service.ChamadaResponse;
import br.com.matheus.chamada.view.main.MainActivity;

public class LoginFingerprintActivity extends AppCompatActivity {

    private ActivityLoginFingerprintBinding binding;
    private FingerprintIdentify fingerprintIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_fingerprint);

        fingerprintIdentify = new FingerprintIdentify(this);
        fingerprintIdentify.startIdentify(3, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                binding.tvSubtitleFingerprint.setTextColor(getResources().getColor(R.color.colorAccent));
                binding.tvSubtitleFingerprint.setText(getResources().getString(R.string.act_fingerprint_ok_finger));

                String login = Hawk.get(PreferencesHelper.FINGERPRINT_LOGIN_USER, "");

                String password = Hawk.get(login, "");

                ChamadaRepository.login(getSupportFragmentManager(),
                        login,
                        password,
                        new ChamadaResponse<LoginResponse>() {
                            @Override
                            public void onResponseSuccess(LoginResponse response) {
                                Toast.makeText(LoginFingerprintActivity.this,
                                        "Seja bem-vindo " + response.getUser().getName(),
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginFingerprintActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                            @Override
                            public void onResponseError(ErrorResponse error) {
                                Toast.makeText(LoginFingerprintActivity.this,
                                        error.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                binding.tvSubtitleFingerprint.setText(getResources().getString(R.string.act_fingerprint_subtitle));
                            }
                        });
            }

            @Override
            public void onNotMatch(int availableTimes) {
                binding.tvSubtitleFingerprint.setTextColor(Color.RED);
                binding.tvSubtitleFingerprint.setText(getResources().getString(R.string.act_fingerprint_error_finger));
                YoYo.with(Techniques.Tada)
                        .duration(500)
                        .interpolate(new DecelerateInterpolator())
                        .playOn(binding.tvSubtitleFingerprint);
            }

            @Override
            public void onFailed(boolean isDeviceLocked) {
                Toast.makeText(LoginFingerprintActivity.this,
                        getResources().getString(R.string.act_fingerprint_error_three_try),
                        Toast.LENGTH_SHORT).show();
                returnLogin();
            }

            @Override
            public void onStartFailedByDeviceLocked() {

            }
        });

        binding.btExitFingerprint.setOnClickListener(v -> returnLogin());
    }

    private void returnLogin() {
        Intent intent = new Intent(LoginFingerprintActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fingerprintIdentify != null) {
            fingerprintIdentify.cancelIdentify();
        }
    }
}
