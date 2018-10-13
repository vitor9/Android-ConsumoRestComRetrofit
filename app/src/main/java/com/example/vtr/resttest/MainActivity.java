package com.example.vtr.resttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://10.0.2.2:8080/OneVita/rest/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitUsuariosInterface api = retrofit.create(RetrofitUsuariosInterface.class);

        api.getUsuarios().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuarios = response.body();
                for(Usuario usuario : usuarios) {
                    Log.d("NOME", usuario.getNome());
                    Log.d("APELIDO", usuario.getNomeUsuario());
                    Log.d("EMAIL: ", usuario.getEmail());
                    Log.d("DATA NASCIMENTO: ", usuario.getDataNascimento());

                    Toast.makeText(MainActivity.this, "NOME DO USUARIO: " + usuario.getNome(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
              Toast.makeText(MainActivity.this, "Ocorre um erro", Toast.LENGTH_SHORT).show();
              Log.d("MeuDebug",t.getMessage());
            }
        });
    }
    public void goToCadastro(View view) {
        Intent it = new Intent(this, Cadastrar.class);
        startActivity(it);
    }
}
