package br.com.matheus.chamada.service;

import com.google.gson.Gson;

import br.com.matheus.chamada.model.response.ErrorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(ErrorResponse error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            try {
                onError(new Gson().fromJson(response.errorBody().string(), ErrorResponse.class));
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(e.getMessage());
                onError(errorResponse);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Falha na conexão");
        onError(errorResponse);
    }
}
