package br.com.matheus.chamada.view.main;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.orhanobut.hawk.Hawk;

import java.util.Objects;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.DialogFragmentSettingsBinding;
import br.com.matheus.chamada.helper.PreferencesHelper;

/**
 * Created by mathe on 17/03/2018.
 */

public class SettingsDialogFragment extends DialogFragment {

    private static final String TAG = "tag_settings_dialog_fragment";
    private static boolean show = false;
    private DialogFragmentSettingsBinding binding;
    private boolean singleChecked, doubleChecked, allChecked;

    public static SettingsDialogFragment newInstance() {
        return new SettingsDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_fragment_settings,
                null,
                false);

        setCancelable(false);

        binding.ibCloseConfig.setOnClickListener(v -> dismiss());

        binding.btConfirmConfig.setOnClickListener(v -> {
            Hawk.put(PreferencesHelper.SINGLE_CHECK, singleChecked);
            Hawk.put(PreferencesHelper.DOUBLE_CHECK, doubleChecked);
            Hawk.put(PreferencesHelper.ALL_CHECK, allChecked);
            dismiss();
        });

        configureSelected();

        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setOnKeyListener((dialog, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK &&
                            event.getAction() != KeyEvent.ACTION_DOWN) {
                        dismiss();
                        return true;
                    } else {
                        return false;
                    }
                })
                .setView(binding.getRoot())
                .create();
    }

    private void configureSelected() {
        if (Hawk.get(PreferencesHelper.SINGLE_CHECK)) {
            fadeIn(binding.ivSingleConfig);
        } else if (Hawk.get(PreferencesHelper.DOUBLE_CHECK)) {
            fadeIn(binding.ivDoubleConfig);
        } else if (Hawk.get(PreferencesHelper.ALL_CHECK)) {
            fadeIn(binding.ivAllConfig);
        }

        binding.llSingleConfig.setOnClickListener(v -> {
            if (!(boolean) Hawk.get(PreferencesHelper.SINGLE_CHECK)) {
                fadeOut();
                fadeIn(binding.ivSingleConfig);
                singleChecked = true;
            }
        });

        binding.llDoubleConfig.setOnClickListener(v -> {
            if (!(boolean) Hawk.get(PreferencesHelper.DOUBLE_CHECK)) {
                fadeOut();
                fadeIn(binding.ivDoubleConfig);
                doubleChecked = true;
            }
        });

        binding.llAllConfig.setOnClickListener(v -> {
            if (!(boolean) Hawk.get(PreferencesHelper.ALL_CHECK)) {
                fadeOut();
                fadeIn(binding.ivAllConfig);
                allChecked = true;
            }
        });
    }

    private void fadeIn(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(200)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(view);
    }

    private void fadeOut() {
        View view = null;
        if (Hawk.get(PreferencesHelper.SINGLE_CHECK)) {
            view = binding.ivSingleConfig;
            singleChecked = false;
        } else if (Hawk.get(PreferencesHelper.DOUBLE_CHECK)) {
            view = binding.ivDoubleConfig;
            doubleChecked = false;
        } else if (Hawk.get(PreferencesHelper.ALL_CHECK)) {
            view = binding.ivAllConfig;
            allChecked = false;
        }

        YoYo.with(Techniques.FadeOut)
                .duration(200)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(view);
    }

    public void show(FragmentManager manager) {
        if (show || (getDialog() != null && getDialog().isShowing())) return;

        try {
            show = true;
            super.show(manager, TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        try {
            show = false;
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
