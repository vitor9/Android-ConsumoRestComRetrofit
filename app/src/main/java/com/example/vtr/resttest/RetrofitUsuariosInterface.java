package com.example.vtr.resttest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitUsuariosInterface {
    @GET("listar")
    Call<List<Usuario>> getUsuarios();

    @POST("cadastro")
    Call<Usuario> createUser(@Body Usuario usuario);
}
