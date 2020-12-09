package com.engcria.ruifma_mobile.rotinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.engcria.ruifma_mobile.R;
import com.engcria.ruifma_mobile.functions.BCrypt;
import com.engcria.ruifma_mobile.functions.UIfunctions;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CadastroActivity extends AppCompatActivity{
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<CharSequence> adapter;
    private LinearLayout layoutCadastro;
    private TextInputEditText firstName,
            lastName,
            email,
            password,
            birthDay;
    private AutoCompleteTextView roles;
    private String  currentDate, //-> UpdatedAt and CreatedAt
            firstNameUser,
            lastNameUser,
            emailUser,
            passwordUser,
            birthDayUser,
            rolesUser,
            salt,
            passwordHash;
    private RequestQueue request;
    private JsonObjectRequest getRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        cachItems();
        cachString();
        theme();

    }

    public void cachItems(){
        autoCompleteTextView = findViewById(R.id.roles);
        layoutCadastro = findViewById(R.id.cardLayoutCadastro);
        firstName       = findViewById(R.id.inputTextNome);
        lastName        = findViewById(R.id.inputTextSobreNome);
        email           = findViewById(R.id.inputTextEmailCad);
        password        = findViewById(R.id.inputTextSenhaCad);
        roles            = findViewById(R.id.roles);
        birthDay        = findViewById(R.id.inputTextAniversario);

        SimpleMaskFormatter fm = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher ms = new MaskTextWatcher(birthDay, fm);
        birthDay.addTextChangedListener(ms);

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd%20HH:mm:ss",
                Locale.getDefault());
        currentDate = dateFormat.format(new Date());
    }
    public void theme(){
        UIfunctions uIfunctions = new UIfunctions(getApplicationContext());
        int i = uIfunctions.currentTheme();
        if(i == 1){
            layoutCadastro.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.background_layout_login_withe));
        }else{
            layoutCadastro.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.background_layout_login_dark));
        }
    }
    public void armazenarStrings(){
        firstNameUser   = firstName.getText().toString();
        lastNameUser    = lastName.getText().toString();
        emailUser       = email.getText().toString();
        passwordUser    = password.getText().toString();
        birthDayUser    = birthDay.getText().toString();
        rolesUser       = roles.getText().toString();

        salt    = BCrypt.gensalt(13);
        passwordHash = BCrypt.hashpw(passwordUser, salt);
    }
    public void cachString(){
        adapter = new ArrayAdapter<CharSequence>(
                CadastroActivity.this,
                R.layout.autocomplete_layout_cadastro,
                getResources().getStringArray(R.array.role)
        );
        autoCompleteTextView.setAdapter(adapter);
    }
    public void insertInto(View v){
        armazenarStrings();
        request = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.1.55/webservices/cadastro_api.php?firstName="+firstNameUser+"&lastName="+lastNameUser+"&email="+emailUser+"&password="+passwordHash+"&role=1&birthday="+birthDayUser+"&createdAt="+currentDate+"&updatedAt="+currentDate+"";
        url.replace(" ", "%20");

       getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
               response -> {
                   // display response
                   Toast.makeText(CadastroActivity.this, birthDayUser, Toast.LENGTH_SHORT).show();
               },
               error -> Toast.makeText(CadastroActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
       );
       request.add(getRequest);
    }

    @Override
    public void onBackPressed() {
        Intent backSceen = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(backSceen);
        finish();
    }
}