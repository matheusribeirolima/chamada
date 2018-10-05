package br.com.matheus.chamada.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mathe on 31/12/2017.
 */

public class User {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String login;

    @SerializedName("photo")
    private String photo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
