package com.example.vtr.resttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastrar extends AppCompatActivity {
    public static final String BASE_URL = "http://10.0.2.2:8080/OneVita/rest/";

    EditText edtNome;
    EditText edtEmail;
    EditText edtNomeUsuario;
    EditText edtPeso;
    EditText edtSenha;
    EditText edtDataNascimento;
    EditText edtAltura;
    RadioButton rdgMasculino;
    RadioButton rdgFeminino;
    RetrofitUsuariosInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtNomeUsuario= findViewById(R.id.edtNomeUsuario);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        edtSenha = findViewById(R.id.edtSenha);
        rdgMasculino = findViewById(R.id.rdgMasculino);
        rdgFeminino = findViewById(R.id.rdgFeminino);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);

        api = APIClient.getClient().create(RetrofitUsuariosInterface.class);

    }

    public double calcularImc(double peso, double altura) {
        double imc = peso *(altura * 2);
        return imc;
    }

    public void cadastrar(View view) {
        Usuario usuario = new Usuario();

        double peso = Double.parseDouble(edtPeso.getText().toString());
        double altura = Double.parseDouble(edtAltura.getText().toString());

        usuario.setNome(edtNome.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        usuario.setNomeUsuario(edtNomeUsuario.getText().toString());
        usuario.setSenha(edtSenha.getText().toString());
        usuario.setAltura(altura);
        usuario.setPeso(peso);
        usuario.setValorImc(calcularImc(peso, altura));
        usuario.setDataNascimento(edtDataNascimento.getText().toString());
//        usuario.setDataNascimento("10-03-1998");
        if (rdgFeminino.isChecked()) {
            usuario.setGenero(Genero.valueOf("FEMININO"));
        }
        else if (rdgMasculino.isChecked()) {
            usuario.setGenero(Genero.valueOf("MASCULINO"));
        }

        Call<Usuario> postCall = api.createUser(usuario);
        postCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario1 = response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void cadastrarChumbado(View view) {
        Usuario usuario = new Usuario();

        double peso = 122;
        double altura = 1.68;

        usuario.setNome("VITOR TESTE");
        usuario.setEmail("XEXA@TESTE.COM");
        usuario.setNomeUsuario("VITAO");
        usuario.setSenha("54321");
        usuario.setDataNascimento("10-03-1998");
        usuario.setAltura(altura);
        usuario.setPeso(peso);
        usuario.setValorImc(calcularImc(peso, altura));
        usuario.setGenero(Genero.valueOf("MASCULINO"));

        Call<Usuario> postCall = api.createUser(usuario);
        postCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario1 = response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
