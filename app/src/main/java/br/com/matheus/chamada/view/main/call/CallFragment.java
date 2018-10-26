package br.com.matheus.chamada.view.main.call;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Date;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.FragmentCallBinding;
import br.com.matheus.chamada.model.response.ErrorResponse;
import br.com.matheus.chamada.model.response.LessonResponse;
import br.com.matheus.chamada.repository.ChamadaRepository;
import br.com.matheus.chamada.service.ChamadaResponse;
import br.com.matheus.chamada.view.main.FabClickListener;

/**
 * Created by mathe on 05/11/2017.
 */

public class CallFragment extends Fragment implements FabClickListener {

    private FragmentCallBinding binding;
    private CallAdapter callAdapter;
    private String lessonId;

    private static CallFragment instance;

    public static synchronized CallFragment getInstance() {
        if (instance == null) {
            instance = new CallFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_call,
                container,
                false);

        configureRecycler();

        ChamadaRepository.findLesson(getFragmentManager(),
                new ChamadaResponse<LessonResponse>() {
                    @Override
                    public void onResponseSuccess(LessonResponse response) {
                        if (response.isHasLesson()) {
                            binding.tvNoCall.setVisibility(View.GONE);
                            binding.rvCall.setVisibility(View.VISIBLE);

                            binding.rvCall.setItemViewCacheSize(response
                                    .getLesson()
                                    .getClassroom()
                                    .getStudents()
                                    .size());

                            callAdapter.setData(response
                                            .getLesson()
                                            .getClassroom()
                                            .getStudents(),
                                    response.getLesson());

                            lessonId = response.getLesson().getId();
                        } else {
                            binding.rvCall.setVisibility(View.GONE);
                            binding.tvNoCall.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onResponseError(ErrorResponse error) {
                        Toast.makeText(getActivity(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return binding.getRoot();
    }

    private void configureRecycler() {
        callAdapter = new CallAdapter();
        binding.rvCall.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        binding.rvCall.setHasFixedSize(true);
        binding.rvCall.setAdapter(callAdapter);
    }

    @Override
    public void onClickListener() {
        ChamadaRepository.call(getFragmentManager(),
                lessonId,
                callAdapter.getStudents(),
                new Date(),
                new ChamadaResponse<Void>() {
                    @Override
                    public void onResponseSuccess(Void response) {
                        Toast.makeText(getContext(),
                                "Chamada realizada",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseError(ErrorResponse error) {
                        Toast.makeText(getContext(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
