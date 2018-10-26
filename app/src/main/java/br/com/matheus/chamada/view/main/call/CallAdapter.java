package br.com.matheus.chamada.view.main.call;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.ItemCallBinding;
import br.com.matheus.chamada.databinding.ItemCallHeaderBinding;
import br.com.matheus.chamada.model.response.Lesson;
import br.com.matheus.chamada.model.response.Student;

/**
 * Created by mathe on 02/01/2018.
 */

public class CallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_HEADER = 1;
    private List<Student> students = new ArrayList<>();
    private Lesson lesson;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    void setData(List<Student> students, Lesson lesson) {
        this.lesson = lesson;
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    List<Student> getStudents() {
        return students;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            return new NormalViewHolder(ItemCallBinding.inflate(LayoutInflater.from(
                    parent.getContext()), parent, false));
        } else {
            return new HeaderViewHolder(ItemCallHeaderBinding.inflate(LayoutInflater.from(
                    parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            final Student student = students.get(position - 1);
            final NormalViewHolder normalViewHolder = (NormalViewHolder) holder;

            normalViewHolder.binding.setStudent(student);

            imageLoader.displayImage(student.getPhoto(), normalViewHolder.binding.civStudent);
            normalViewHolder.binding.tvStudentName.setSelected(true);

//            normalViewHolder.binding.rlFault.cbFault1
//                    .setChecked(normalViewHolder.binding.rlFault.cbFault1.isChecked());
//            normalViewHolder.binding.rlFault.cbFault2
//                    .setChecked(normalViewHolder.binding.rlFault.cbFault2.isChecked());
//            normalViewHolder.binding.rlFault.cbFault3
//                    .setChecked(normalViewHolder.binding.rlFault.cbFault3.isChecked());
//            normalViewHolder.binding.rlFault.cbFault4
//                    .setChecked(normalViewHolder.binding.rlFault.cbFault4.isChecked());

            if ((position + 1) <= students.size()) {
                normalViewHolder.binding.vDivider.setVisibility(View.VISIBLE);
            } else {
                normalViewHolder.binding.vDivider.setVisibility(View.GONE);
            }
        } else if (holder instanceof HeaderViewHolder) {
            final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

            String classroom = headerViewHolder.itemView.getContext()
                    .getResources().getString(R.string.frag_call_classroom);

            SpannableStringBuilder strClassroom = new SpannableStringBuilder(classroom +
                    lesson.getClassroom().getCode());
            strClassroom.setSpan(new StyleSpan(Typeface.BOLD),
                    0,
                    classroom.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            headerViewHolder.binding.tvClassroomCall.setText(strClassroom);

            String theme = headerViewHolder.itemView.getResources().getString(R.string.frag_call_theme);

            SpannableStringBuilder strTheme = new SpannableStringBuilder(theme +
                    lesson.getClassroom().getTheme().getName());
            strTheme.setSpan(new StyleSpan(Typeface.BOLD),
                    0,
                    theme.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            headerViewHolder.binding.tvThemeCall.setText(strTheme);

            String schedule = headerViewHolder.itemView.getResources().getString(R.string.frag_call_schedule);

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

            headerViewHolder.binding.tvScheduleCall.setText(strSchedule);

            String blockClass = headerViewHolder.itemView.getResources().getString(R.string.frag_call_block_class);

            SpannableStringBuilder strBlockClass = new SpannableStringBuilder(blockClass +
                    lesson.getClassroom().getBlockClass());
            strBlockClass.setSpan(new StyleSpan(Typeface.BOLD),
                    0,
                    blockClass.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            headerViewHolder.binding.tvBlockClassCall.setText(strBlockClass);
        }
    }

    @Override
    public int getItemCount() {
        return students.size() > 0 ? students.size() + 1 : 0;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        ItemCallHeaderBinding binding;

        HeaderViewHolder(ItemCallHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        ItemCallBinding binding;

        NormalViewHolder(ItemCallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.setLesson(lesson);
        }
    }
}
