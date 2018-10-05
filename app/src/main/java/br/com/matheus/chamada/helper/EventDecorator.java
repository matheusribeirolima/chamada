package br.com.matheus.chamada.helper;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mathe on 29/12/2017.
 */

public class EventDecorator implements DayViewDecorator {

    private final int color1, color2, color3;
    private final HashSet<CalendarDay> dates;

    public EventDecorator(int color1, Collection<CalendarDay> dates) {
        this.color1 = color1;
        this.color2 = 0;
        this.color3 = 0;
        this.dates = new HashSet<>(dates);
    }

    public EventDecorator(int color1, int color2, Collection<CalendarDay> dates) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = 0;
        this.dates = new HashSet<>(dates);
    }

    public EventDecorator(int color1, int color2, int color3, Collection<CalendarDay> dates) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        if (color2 == 0 && color3 == 0){
            view.addSpan(new Dots(color1));
        } else if (color3 == 0){
            view.addSpan(new Dots(color1, color2));
        } else {
            view.addSpan(new Dots(color1, color2, color3));
        }
    }
}
