package com.example.segundoparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class WelcomeMCActivity extends AppCompatActivity {
    private EditText edtNomProp, edtNumProp;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_mcactivity);

        try {
            edtNomProp = findViewById(R.id.edtNomProp);
            edtNumProp = findViewById(R.id.edtNumProp);
            Button btnGuardarProp = findViewById(R.id.btnGuardarProp);
            btnGuardarProp.setOnClickListener(aux -> {
                if(validate()){
                    startActivity(new Intent(WelcomeMCActivity.this, MainMCActivity.class));
                    finish();
                }else{
                    Toast.makeText(WelcomeMCActivity.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(WelcomeMCActivity.this, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        boolean  b= false;
        if(!edtNomProp.getText().toString().isEmpty() && !edtNumProp.getText().toString().isEmpty()){
            addProprietary();
            b = true;
        }
        return b;
    }

    private void addProprietary() {
        sharedPreferences = getSharedPreferences("proprietary", MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
        editorConfig.putString("NAME", edtNomProp.getText().toString());
        editorConfig.putString("NUMBER", edtNumProp.getText().toString());
        editorConfig.commit();
        Toast.makeText(WelcomeMCActivity.this, "El propietario se guardó con éxito", Toast.LENGTH_SHORT).show();
    }
}