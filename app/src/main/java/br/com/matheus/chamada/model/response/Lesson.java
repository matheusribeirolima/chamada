package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Lesson implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("date")
    private Date date;

    @SerializedName("schedules")
    private List<Schedule> schedules;

    @SerializedName("classroom")
    private Classroom classroom;

    @SerializedName("alreadyMadeCall")
    private boolean alreadyMadeCall;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public boolean isAlreadyMadeCall() {
        return alreadyMadeCall;
    }

    public void setAlreadyMadeCall(boolean alreadyMadeCall) {
        this.alreadyMadeCall = alreadyMadeCall;
    }
}
