package br.com.matheus.chamada.view.main.calendar;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.view.classroom.ClassroomAdapter;
import br.com.matheus.chamada.databinding.FragmentCalendarBinding;
import br.com.matheus.chamada.view.main.FabClickListener;
import br.com.matheus.chamada.helper.EventDecorator;

/**
 * Created by mathe on 05/11/2017.
 */

public class CalendarFragment extends Fragment implements FabClickListener {

    private FragmentCalendarBinding binding;
    private LinearLayoutManager linearLayoutManager;
    private ClassroomAdapter classroomAdapter;
    private CalendarListener calendarListener;

    private static CalendarFragment instance;

    public static synchronized CalendarFragment getInstance() {
        if (instance == null) {
            instance = new CalendarFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        calendarListener = (CalendarListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);

        configureCalendar();
        configureCalendarClass();

        binding.calendar.setOnMonthChangedListener((widget, date) -> {
            if (date.getMonth() == binding.calendar.getSelectedDate().getMonth() &&
                    DateUtils.isToday(binding.calendar.getSelectedDate().getDate().getTime())) {
                calendarListener.onHideCalendar();
            } else {
                calendarListener.onShowCalendar();
            }
        });

        binding.calendar.setOnDateChangedListener((widget, date, selected) -> {
            if (DateUtils.isToday(date.getDate().getTime())) {
                calendarListener.onHideCalendar();
            } else {
                calendarListener.onShowCalendar();
            }
        });

        return binding.getRoot();
    }

//    public ArrayList<Lesson> mock() {
//        Theme theme1 = new Theme();
//        theme1.setNome("Introdução à Programação de Computadores");
//
//        Classroom classroom1 = new Classroom();
//        classroom1.setColor(Color.RED);
//        classroom1.setCodigo("ABC");
//        classroom1.setDisciplina(theme1);
//
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(Calendar.DAY_OF_MONTH, 10);
//
//        List<Schedule> schedule1 = new ArrayList<>();
//        schedule1.add(Schedule.A);
//        schedule1.add(Schedule.B);
//
//        Lesson aula1 = new Lesson();
//        aula1.setTurma(classroom1);
//        aula1.setData(calendar1.getTime());
//        aula1.setHorarios(schedule1);
//
//        //----------------------------------------------
//
//        Theme theme2 = new Theme();
//        theme2.setNome("Introdução aos Sistemas de Informação");
//
//        Classroom classroom2 = new Classroom();
//        classroom2.setColor(Color.BLACK);
//        classroom2.setCodigo("DEF");
//        classroom2.setDisciplina(theme2);
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.set(Calendar.DAY_OF_MONTH, 10);
//
//        List<Schedule> schedule2 = new ArrayList<>();
//        schedule2.add(Schedule.M);
//        schedule2.add(Schedule.N);
//
//        Lesson aula2 = new Lesson();
//        aula2.setTurma(classroom2);
//        aula2.setData(calendar2.getTime());
//        aula2.setHorarios(schedule2);
//
//        //----------------------------------------------
//
//        Theme theme3 = new Theme();
//        theme3.setNome("Arquitetura e Organização de Computadores");
//
//        Classroom classroom3 = new Classroom();
//        classroom3.setColor(Color.BLUE);
//        classroom3.setCodigo("GHI");
//        classroom3.setDisciplina(theme3);
//
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.set(Calendar.DAY_OF_MONTH, 10);
//
//        List<Schedule> schedule3 = new ArrayList<>();
//        schedule3.add(Schedule.C);
//        schedule3.add(Schedule.D);
//
//        Lesson aula3 = new Lesson();
//        aula3.setTurma(classroom3);
//        aula3.setData(calendar3.getTime());
//        aula3.setHorarios(schedule3);
//
//        //----------------------------------------------
//
//        Theme theme4 = new Theme();
//        theme4.setNome("Projeto e Desenvolvimento de Sistemas de Informação 1");
//
//        Classroom classroom4 = new Classroom();
//        classroom4.setColor(Color.GREEN);
//        classroom4.setCodigo("JKL");
//        classroom4.setDisciplina(theme4);
//
//        Calendar calendar4 = Calendar.getInstance();
//        calendar4.set(Calendar.DAY_OF_MONTH, 12);
//
//        List<Schedule> schedule4 = new ArrayList<>();
//        schedule4.add(Schedule.G);
//        schedule4.add(Schedule.H);
//
//        Lesson aula4 = new Lesson();
//        aula4.setTurma(classroom4);
//        aula4.setData(calendar4.getTime());
//        aula4.setHorarios(schedule4);
//
//        //----------------------------------------------
//
//        Theme theme5 = new Theme();
//        theme5.setNome("Estatística");
//
//        Classroom classroom5 = new Classroom();
//        classroom5.setColor(Color.YELLOW);
//        classroom5.setCodigo("MNO");
//        classroom5.setDisciplina(theme5);
//
//        Calendar calendar5 = Calendar.getInstance();
//        calendar5.set(Calendar.DAY_OF_MONTH, 20);
//
//        List<Schedule> schedule5 = new ArrayList<>();
//        schedule5.add(Schedule.I);
//        schedule5.add(Schedule.J);
//
//        Lesson aula5 = new Lesson();
//        aula5.setTurma(classroom5);
//        aula5.setData(calendar5.getTime());
//        aula5.setHorarios(schedule5);
//
//        //----------------------------------------------
//
//        Theme theme6 = new Theme();
//        theme6.setNome("Programação Orientada a Objetos 2");
//
//        Classroom classroom6 = new Classroom();
//        classroom6.setColor(Color.MAGENTA);
//        classroom6.setCodigo("PQR");
//        classroom6.setDisciplina(theme6);
//
//        Calendar calendar6 = Calendar.getInstance();
//        calendar6.set(Calendar.DAY_OF_MONTH, 20);
//
//        List<Schedule> schedule6 = new ArrayList<>();
//        schedule6.add(Schedule.K);
//        schedule6.add(Schedule.M);
//
//        Lesson aula6 = new Lesson();
//        aula6.setTurma(classroom6);
//        aula6.setData(calendar6.getTime());
//        aula6.setHorarios(schedule6);
//
//        //----------------------------------------------
//
//        ArrayList<Lesson> aulas = new ArrayList<>();
//        aulas.add(aula1);
//        aulas.add(aula2);
//        aulas.add(aula3);
//        aulas.add(aula4);
//        aulas.add(aula5);
//        aulas.add(aula6);
//
//        return aulas;
//    }

    public void configureCalendarClass() {
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        binding.rvCalendar.setLayoutManager(linearLayoutManager);
        binding.rvCalendar.setHasFixedSize(false);

//        classroomAdapter = new ClassroomAdapter(mock(), getActivity());
//        binding.rvCalendar.setAdapter(classroomAdapter);
    }

    public void configureCalendar() {
        CalendarDay data1 = CalendarDay.from(2018, 3, 1);
        CalendarDay data2 = CalendarDay.from(2018, 3, 17);
        CalendarDay data3 = CalendarDay.from(2018, 3, 23);

        ArrayList<CalendarDay> days1 = new ArrayList<>();
        days1.add(data1);

        ArrayList<CalendarDay> days2 = new ArrayList<>();
        days2.add(data2);

        ArrayList<CalendarDay> days3 = new ArrayList<>();
        days3.add(data3);

        binding.calendar.addDecorators(new EventDecorator(Color.BLACK, days1),
                new EventDecorator(Color.BLACK, Color.RED, days2),
                new EventDecorator(Color.BLACK, Color.RED, Color.BLUE, days3));

        binding.calendar.setSelectedDate(new Date());
    }

    @Override
    public void onClickListener() {
        binding.calendar.setSelectedDate(Calendar.getInstance().getTime());
        binding.calendar.setCurrentDate(Calendar.getInstance().getTime());
        calendarListener.onHideCalendar();
    }
}
