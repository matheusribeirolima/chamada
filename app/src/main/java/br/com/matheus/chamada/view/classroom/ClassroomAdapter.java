package br.com.matheus.chamada.view.classroom;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.ItemClassBinding;
import br.com.matheus.chamada.model.response.Lesson;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.MyViewHolder> {

    private List<Lesson> aulas;
    private Context context;

    public ClassroomAdapter(List<Lesson> aulas, Context context) {
        this.aulas = aulas;
        this.context = context;
    }

    public void setAulas(List<Lesson> aulas) {
        this.aulas = aulas;
        notifyDataSetChanged();
    }

    public void clear() {
        aulas.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassroomAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Lesson aula = aulas.get(position);
//        holder.binding.civColorClass.setBackgroundTintList(ColorStateList.valueOf(aula.getTurma().getColor()));
//        holder.binding.tvCodeClass.setText("Classroom: " + aula.getTurma().getCodigo());
//        holder.binding.tvNameClass.setText(aula.getTurma().getDisciplina().getNome());
//        String startTime = aula.getHorarios().get(0).getId();
//        holder.binding.tvStartTime.setText(startTime.substring(0, startTime.indexOf(" ")));
//        String endTime = aula.getHorarios().get(aula.getHorarios().size() - 1).getId();
//        holder.binding.tvEndTime.setText(endTime.substring(endTime.indexOf("- ") + 2));
    }

    @Override
    public int getItemCount() {
        return aulas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ItemClassBinding binding;

        MyViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ClassroomActivity.class);
                context.startActivity(intent);
            });
        }
    }
}
