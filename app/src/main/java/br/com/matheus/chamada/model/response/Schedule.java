package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum Schedule implements Serializable {

    @SerializedName("A")
    A("07h10 - 08h00"),

    @SerializedName("B")
    B("08h00 - 08h50"),

    @SerializedName("C")
    C("08h50 - 09h40"),

    @SerializedName("D")
    D("09h50 - 10h40"),

    @SerializedName("E")
    E("10h40 - 11h30"),

    @SerializedName("Q")
    Q("11h30 - 12h20"),

    @SerializedName("F")
    F("13h10 - 14h00"),

    @SerializedName("G")
    G("14h00 - 14h50"),

    @SerializedName("H")
    H("14h50 - 15h40"),

    @SerializedName("I")
    I("16h00 - 16h50"),

    @SerializedName("J")
    J("16h50 - 17h40"),

    @SerializedName("K")
    K("17h40 - 18h30"),

    @SerializedName("M")
    M("19h00 - 19h50"),

    @SerializedName("N")
    N("19h50 - 20h40"),

    @SerializedName("O")
    O("20h40 - 21h30"),

    @SerializedName("P")
    P("21h40 - 22h30");

    private String id;

    Schedule(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
