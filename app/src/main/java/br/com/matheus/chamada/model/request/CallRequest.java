package br.com.matheus.chamada.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.matheus.chamada.model.response.Student;

public class CallRequest {

    @SerializedName("lessonId")
    private String lessonId;

    @SerializedName("students")
    private List<Student> students;

    @SerializedName("timestamp")
    private long timestamp;

    public CallRequest(String lessonId, List<Student> students, long timestamp) {
        this.lessonId = lessonId;
        this.students = students;
        this.timestamp = timestamp;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

