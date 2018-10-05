package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

public enum Weekday {

    @SerializedName("SUNDAY")
    SUNDAY("DOMINGO"),

    @SerializedName("MONDAY")
    MONDAY("SEGUNDA"),

    @SerializedName("TUESDAY")
    TUESDAY("TERÇA"),

    @SerializedName("WEDNESDAY")
    WEDNESDAY("QUARTA"),

    @SerializedName("THURSDAY")
    THURSDAY("QUINTA"),

    @SerializedName("FRIDAY")
    FRIDAY("SEXTA"),

    @SerializedName("SATURDAY")
    SATURDAY("SÁBADO");

    private String id;

    Weekday(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
