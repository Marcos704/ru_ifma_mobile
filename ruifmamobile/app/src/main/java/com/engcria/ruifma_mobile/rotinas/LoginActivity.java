
package com.engcria.ruifma_mobile.rotinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.engcria.ruifma_mobile.R;
import com.engcria.ruifma_mobile.functions.UIfunctions;
import com.engcria.ruifma_mobile.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {
    private int theme;
    private LinearLayout layoutlogin;
    private TextView rotinaCadastro;
    private MaterialButton btLogin;
    private TextInputEditText inputEditTextEmail, inputEditTextPassword;
    private TextInputLayout layout_email, layout_password;
    private ProgressDialog progressDialog;

    private RequestQueue request;
    private JsonObjectRequest getRequest;
    User user = new User();

    private String emailUser, passWordUser, salt, passwordHahs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        navBarColor(R.color.layout);
        getSupportActionBar().hide();
        cacheItems();

        rotinaCadastro();

    }

    public void logarTest(View view){

        if(inputEditTextEmail.getText().equals("") && inputEditTextPassword.getText().equals("")){
            layout_email.setError("Digite o seu email");
            layout_password.setError("Digite a sua senha");
        }else{

        }


        verificarLogin();
    }


    public void verificarLogin(){
            emailUser = inputEditTextEmail.getText().toString();
            passWordUser = inputEditTextPassword.getText().toString();
            passwordHahs = user.getPassword();
            if(emailUser.isEmpty() || passWordUser.isEmpty()){
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            }else{
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Carregado...");
                    progressDialog.show();
                    request = Volley.newRequestQueue(getApplicationContext());
                    String ulr = "http://192.168.1.55/webservices/login_api.php?email="+emailUser+"&password="+passWordUser+"";
                    ulr.replace(" ", "%20");
                    getRequest = new JsonObjectRequest(Request.Method.GET, ulr, null,
                            response -> {
                                progressDialog.dismiss();
                                menuScreen();
                            },
                            error -> {
                                progressDialog.dismiss();
                                Toast.makeText(this, "Login is not sucess", Toast.LENGTH_SHORT).show();
                                if (error instanceof ParseError){
                                    Toast.makeText(this, "Erro de user: ", Toast.LENGTH_SHORT).show();
                                }else if(error instanceof NetworkError){
                                    Toast.makeText(this, "Network Err", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    request.add(getRequest);

            }
    }

    public void menuScreen(){
        Intent menu = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(menu);
        finish();
    }

    public void cacheItems(){
        layoutlogin     = findViewById(R.id.cardLayoutLogin);
        rotinaCadastro  = findViewById(R.id.rotinaCadastro);
        btLogin         = findViewById(R.id.buttonLogin);
        inputEditTextEmail = findViewById(R.id.inputTextEmail);
        inputEditTextPassword = findViewById(R.id.inputTextPassword);
        layout_email    = findViewById(R.id.layoutLoginEmail);
        layout_password = findViewById(R.id.layoutLoginPassword);
        //Cach strings

    }
    @Override
    public void onBackPressed() {
        finish();
    }
    public void rotinaCadastro(){
        rotinaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(cadastrar);
                finish();
            }
        });
    }
    public void cardChage(){
        UIfunctions ifunctions = new UIfunctions(getApplicationContext());
        theme = ifunctions.currentTheme();

        if(theme == 1){
            layoutlogin.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.background_layout_login_withe));
        }else{
            layoutlogin.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.background_layout_login_dark));
        }
    }
    public void navBarColor(int color){
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, color));
    }

}