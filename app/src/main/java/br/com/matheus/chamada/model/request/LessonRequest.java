package br.com.matheus.chamada.model.request;

import com.google.gson.annotations.SerializedName;

import br.com.matheus.chamada.model.response.Weekday;

public class LessonRequest {

    @SerializedName("professorCode")
    private String professorCode;

    @SerializedName("weekday")
    private Weekday weekday;

    @SerializedName("timestamp")
    private long timestamp;

    public LessonRequest(String professorCode, Weekday weekday, long timestamp) {
        this.professorCode = professorCode;
        this.weekday = weekday;
        this.timestamp = timestamp;
    }

    public String getProfessorCode() {
        return professorCode;
    }

    public void setProfessorCode(String professorCode) {
        this.professorCode = professorCode;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
