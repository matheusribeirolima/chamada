package br.com.matheus.chamada.view.main;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.DialogFragmentSettingsBinding;

/**
 * Created by mathe on 17/03/2018.
 */

public class SettingsDialogFragment extends DialogFragment {

    private static final String TAG = "tag_settings_dialog_fragment";
    private static boolean show = false;
    private DialogFragmentSettingsBinding binding;

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
