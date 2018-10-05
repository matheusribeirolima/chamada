package br.com.matheus.chamada.view.main.call;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Date;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.FragmentCallBinding;
import br.com.matheus.chamada.model.response.ErrorResponse;
import br.com.matheus.chamada.model.response.Lesson;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_call, container, false);

        configureRecycler();

        ChamadaRepository.findLesson(getFragmentManager(), new ChamadaResponse<LessonResponse>() {
            @Override
            public void onResponseSuccess(LessonResponse response) {
                if (response.isHasLesson()) {
                    binding.tvNoCall.setVisibility(View.GONE);
                    binding.llHasCall.setVisibility(View.VISIBLE);

                    configureTexts(response.getLesson());

                    binding.rvCall.setItemViewCacheSize(response.getLesson().getClassroom().getStudents().size());
                    callAdapter.setStudents(response.getLesson().getClassroom().getStudents());

                    lessonId = response.getLesson().getId();
                } else {
                    binding.llHasCall.setVisibility(View.GONE);
                    binding.tvNoCall.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onResponseError(ErrorResponse error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void configureTexts(Lesson lesson) {
        String classroom = getResources().getString(R.string.frag_call_classroom);

        SpannableStringBuilder strClassroom = new SpannableStringBuilder(classroom +
                lesson.getClassroom().getCode());
        strClassroom.setSpan(new StyleSpan(Typeface.BOLD),
                0,
                classroom.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvClassroomCall.setText(strClassroom);

        String theme = getResources().getString(R.string.frag_call_theme);

        SpannableStringBuilder strTheme = new SpannableStringBuilder(theme +
                lesson.getClassroom().getTheme().getName());
        strTheme.setSpan(new StyleSpan(Typeface.BOLD),
                0,
                theme.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvThemeCall.setText(strTheme);

        String schedule = getResources().getString(R.string.frag_call_schedule);

        StringBuilder stringSchedule = new StringBuilder();
        for (int i = 0; i < lesson.getSchedules().size(); i++) {
            stringSchedule.append(lesson.getSchedules().get(i).getId());
            if (i < lesson.getSchedules().size() - 1) {
                stringSchedule.append(" / ");
            }
        }

        SpannableStringBuilder strSchedule = new SpannableStringBuilder(schedule +
                stringSchedule);
        strSchedule.setSpan(new StyleSpan(Typeface.BOLD),
                0,
                schedule.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvScheduleCall.setText(strSchedule);

        String blockClass = getResources().getString(R.string.frag_call_block_class);

        SpannableStringBuilder strBlockClass = new SpannableStringBuilder(blockClass +
                lesson.getClassroom().getBlockClass());
        strBlockClass.setSpan(new StyleSpan(Typeface.BOLD),
                0,
                blockClass.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvBlockClassCall.setText(strBlockClass);
    }

    private void configureRecycler() {
        callAdapter = new CallAdapter();
        binding.rvCall.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
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
                        Toast.makeText(getContext(), "Chamada realizada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseError(ErrorResponse error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
