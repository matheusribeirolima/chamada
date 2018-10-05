package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

public class LessonResponse {

    @SerializedName("hasLesson")
    private boolean hasLesson;

    @SerializedName("lesson")
    private Lesson lesson;

    @SerializedName("message")
    private String message;

    public boolean isHasLesson() {
        return hasLesson;
    }

    public void setHasLesson(boolean hasLesson) {
        this.hasLesson = hasLesson;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
