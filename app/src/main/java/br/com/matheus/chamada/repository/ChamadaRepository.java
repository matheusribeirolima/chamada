package br.com.matheus.chamada.repository;

import android.support.v4.app.FragmentManager;

import com.orhanobut.hawk.Hawk;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.matheus.chamada.ChamadaApplication;
import br.com.matheus.chamada.helper.PreferencesHelper;
import br.com.matheus.chamada.helper.Utils;
import br.com.matheus.chamada.model.request.CallRequest;
import br.com.matheus.chamada.model.request.LessonRequest;
import br.com.matheus.chamada.model.request.LoginRequest;
import br.com.matheus.chamada.model.response.ErrorResponse;
import br.com.matheus.chamada.model.response.LessonResponse;
import br.com.matheus.chamada.model.response.LoginResponse;
import br.com.matheus.chamada.model.response.Student;
import br.com.matheus.chamada.model.response.Weekday;
import br.com.matheus.chamada.service.BaseCallback;
import br.com.matheus.chamada.service.ChamadaResponse;
import br.com.matheus.chamada.service.ChamadaService;
import br.com.matheus.chamada.view.LoadingDialogFragment;

public class ChamadaRepository {

    private static ChamadaService chamadaService = ChamadaApplication.getService();

    public static void login(FragmentManager fragmentManager,
                             String login,
                             String password,
                             ChamadaResponse<LoginResponse> listener) {
        LoadingDialogFragment.getInstance().show(fragmentManager);

        chamadaService.login(new LoginRequest(login, new String(Hex.encodeHex(DigestUtils.sha256(password)))))
                .enqueue(new BaseCallback<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        Hawk.put(PreferencesHelper.ACCESS_TOKEN, response.getAccessToken());
                        Hawk.put(PreferencesHelper.CURRENT_USER, response.getUser());
                        LoadingDialogFragment.getInstance().dismiss();
                        listener.onResponseSuccess(response);
                    }

                    @Override
                    public void onError(ErrorResponse error) {
                        LoadingDialogFragment.getInstance().dismiss();
                        listener.onResponseError(error);
                    }
                });
    }

    public static void findLesson(FragmentManager fragmentManager,
                                  ChamadaResponse<LessonResponse> listener) {
        LoadingDialogFragment.getInstance().show(fragmentManager);

        Calendar calendar = Calendar.getInstance();
        chamadaService.findLesson(new LessonRequest(Utils.getCurrentUser().getCode(),
                Weekday.valueOf(calendar.getDisplayName(
                        Calendar.DAY_OF_WEEK,
                        Calendar.LONG,
                        Locale.ENGLISH).toUpperCase()),
                calendar.getTimeInMillis()))
                .enqueue(new BaseCallback<LessonResponse>() {
                    @Override
                    public void onSuccess(LessonResponse response) {
                        LoadingDialogFragment.getInstance().dismiss();
                        listener.onResponseSuccess(response);
                    }

                    @Override
                    public void onError(ErrorResponse error) {
                        LoadingDialogFragment.getInstance().dismiss();
                        listener.onResponseError(error);
                    }
                });
    }

    public static void call(FragmentManager fragmentManager,
                            String lessonId,
                            List<Student> students,
                            Date date,
                            ChamadaResponse<Void> listener) {
        LoadingDialogFragment.getInstance().show(fragmentManager);

        chamadaService.call(new CallRequest(lessonId, students, date.getTime()))
                .enqueue(new BaseCallback<Void>() {
                    @Override
                    public void onSuccess(Void response) {
                        LoadingDialogFragment.getInstance().dismiss();
                        listener.onResponseSuccess(response);
                    }

                    @Override
                    public void onError(ErrorResponse error) {
                        LoadingDialogFragment.getInstance().dismiss();
                        listener.onResponseError(error);
                    }
                });
    }


}
