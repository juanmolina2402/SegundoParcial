package com.example.segundoparcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.segundoparcial.daoMC.ContactsMCDao;
import com.example.segundoparcial.daoMC.ContactsMCDaoImpRoom;
import com.example.segundoparcial.modelMC.ContactsMC;

public class AddContactMCActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private EditText edtContact, edtNumber, edtProprietary;
    private Button btnRegresar, btnGuardar;
    private ContactsMCDao dao;
    private ContactsMC contacts;
    private int estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_mcactivity);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        estado=0;
        Intent intent =new Intent(this,MainMCActivity.class);
        dao = new ContactsMCDaoImpRoom(getApplicationContext());
        this.edtContact = findViewById(R.id.edtNewContact);
        this.edtNumber = findViewById(R.id.edtNumber);
        this.edtProprietary = findViewById(R.id.edtProprietary);
        this.btnRegresar = findViewById(R.id.btnRegresar);
        this.btnGuardar = findViewById(R.id.btnGuardar);

        sharedPreferences = getSharedPreferences("proprietary", MODE_PRIVATE);
        edtProprietary.setText(sharedPreferences.getString("NAME", ""));
        load();

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtContact.getText().toString().isEmpty() && !edtNumber.getText().toString().isEmpty() && !edtProprietary.getText().toString().isEmpty()){
                    if(estado == 0){
                        save();
                    }else{
                        update();
                    }
                    reset();
                    startActivity(intent);
                }else{
                    Toast.makeText(AddContactMCActivity.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void save(){
        contacts = new ContactsMC();
        contacts.setNombre(edtContact.getText().toString());
        contacts.setNumero(edtNumber.getText().toString());
        contacts.setPropietario(edtProprietary.getText().toString());
        dao.save(contacts);
    }

    private void update(){
        contacts.setNombre(edtContact.getText().toString());
        contacts.setNumero(edtNumber.getText().toString());
        contacts.setPropietario(edtProprietary.getText().toString());
        dao.update(contacts);
    }

    private void  reset(){
        edtContact.setText("");
        edtNumber.setText("");
        edtProprietary.setText("");
        btnGuardar.setText("Guardar");
    }

    private void load(){
        Intent intent;
        try {
            intent = getIntent();
            contacts = (ContactsMC) intent.getSerializableExtra("contacto");
            edtContact.setText(contacts.getNombre());
            contacts = (ContactsMC) intent.getSerializableExtra("numero");
            edtNumber.setText(contacts.getNumero());
            contacts = (ContactsMC) intent.getSerializableExtra("propietario");
            edtProprietary.setText(contacts.getPropietario());
            estado = intent.getIntExtra("estado",0);
            btnGuardar.setText("Actualizar");
        }catch (Exception e){
            estado=0;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}