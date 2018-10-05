package br.com.matheus.chamada.model.response;

import com.google.gson.annotations.SerializedName;

import br.com.matheus.chamada.model.User;

public class LoginResponse {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("user")
    private User user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
