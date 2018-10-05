package br.com.matheus.chamada.view.main.call;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import br.com.matheus.chamada.databinding.ItemCallBinding;
import br.com.matheus.chamada.model.response.Student;

/**
 * Created by mathe on 02/01/2018.
 */

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.MyViewHolder> {

    private List<Student> students = new ArrayList<>();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public List<Student> getStudents() {
        return students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemCallBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Student student = students.get(position);

        imageLoader.displayImage(student.getPhoto(), holder.binding.civStudent);
        holder.binding.tvStudentName.setText(student.getName());
        holder.binding.tvStudentName.setSelected(true);
        holder.binding.tvNote.setText("Nota: " + student.getScore());
        holder.binding.tvFault.setText("Faltas: " + student.getFaults());

        holder.binding.rlFault.cbFault1.setChecked(holder.binding.rlFault.cbFault1.isChecked());
        holder.binding.rlFault.cbFault2.setChecked(holder.binding.rlFault.cbFault2.isChecked());
        holder.binding.rlFault.cbFault3.setChecked(holder.binding.rlFault.cbFault3.isChecked());
        holder.binding.rlFault.cbFault4.setChecked(holder.binding.rlFault.cbFault4.isChecked());

        if ((position + 1) < students.size()) {
            holder.binding.vDivider.setVisibility(View.VISIBLE);
        } else {
            holder.binding.vDivider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ItemCallBinding binding;

        MyViewHolder(ItemCallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.rlFault.cbFault1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int faults = students.get(getAdapterPosition()).getFaults();
                if (isChecked) {
                    students.get(getAdapterPosition()).setFaults(faults + 1);
                } else {
                    students.get(getAdapterPosition()).setFaults(faults - 1);
                }
            });
        }
    }
}
