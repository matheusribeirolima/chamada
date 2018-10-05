package br.com.matheus.chamada.service;

import br.com.matheus.chamada.model.request.CallRequest;
import br.com.matheus.chamada.model.request.LessonRequest;
import br.com.matheus.chamada.model.request.LoginRequest;
import br.com.matheus.chamada.model.response.LessonResponse;
import br.com.matheus.chamada.model.response.LoginResponse;
import br.com.matheus.chamada.model.response.Weekday;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mathe on 04/11/2017.
 */

public interface ChamadaService {

    @POST("5b9d5a283200006000db93a8")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("5b9daccc3200006c00db93e0")
    Call<LessonResponse> findLesson(@Body LessonRequest lessonRequest);

    @POST("asd")
    Call<Void> call(@Body CallRequest callRequest);


}
