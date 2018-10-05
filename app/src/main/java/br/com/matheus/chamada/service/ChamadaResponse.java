package br.com.matheus.chamada.service;

import br.com.matheus.chamada.model.response.ErrorResponse;

public interface ChamadaResponse<T> {

    void onResponseSuccess(T response);

    void onResponseError(ErrorResponse error);
}
