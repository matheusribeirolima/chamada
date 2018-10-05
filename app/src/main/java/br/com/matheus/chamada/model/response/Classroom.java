package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Classroom implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("code")
    private String code;

    @SerializedName("semester")
    private Semester semester;

    @SerializedName("blockClass")
    private String blockClass;

    @SerializedName("theme")
    private Theme theme;

    @SerializedName("weekdays")
    private List<Weekday> weekdays;

    @SerializedName("students")
    private List<Student> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(String blockClass) {
        this.blockClass = blockClass;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Weekday> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<Weekday> weekdays) {
        this.weekdays = weekdays;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
