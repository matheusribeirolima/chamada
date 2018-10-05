package br.com.matheus.chamada.view.main.account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Objects;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.FragmentAccountBinding;
import br.com.matheus.chamada.helper.AlertListener;
import br.com.matheus.chamada.helper.PreferencesHelper;
import br.com.matheus.chamada.helper.Utils;
import br.com.matheus.chamada.view.intro.LoginActivity;
import br.com.matheus.chamada.view.intro.SplashScreenActivity;

/**
 * Created by mathe on 05/11/2017.
 */

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private static AccountFragment instance;

    public static synchronized AccountFragment getInstance() {
        if (instance == null) {
            instance = new AccountFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);

        ImageLoader.getInstance().displayImage(Utils.getCurrentUser().getPhoto(), binding.civUserImage);
        binding.tvUserName.setText(Utils.getCurrentUser().getName());
        binding.tvUserEmail.setText(Utils.getCurrentUser().getLogin());

        binding.tvClassroom.setOnClickListener(view -> {

        });

        binding.tvHistory.setOnClickListener(view -> {

        });

        binding.tvAbout.setOnClickListener(view -> {

        });

        binding.tvExit.setOnClickListener(view -> Utils.showAlertDialog(getActivity(),
                getResources().getString(R.string.exitChamada),
                getResources().getString(R.string.logoutChamada),
                getResources().getDrawable(R.drawable.ic_alert),
                new AlertListener() {
                    @Override
                    public void onClickPositiveButton() {
                        PreferencesHelper.logout();
                        Intent intent = new Intent(getActivity(), SplashScreenActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onClickNegativeButton() {

                    }
                }));

        return binding.getRoot();
    }
}
