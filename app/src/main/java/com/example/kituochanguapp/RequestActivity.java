package com.example.kituochanguapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RequestActivity extends AppCompatActivity {
    EditText fname,sname,lname,sheha,address,phone;
    Button btn_request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.edt_ShehaAutoComplete);
        fname=findViewById(R.id.edt_fname);
        sname=findViewById(R.id.edt_sname);
        lname=findViewById(R.id.edt_lname);
        phone=findViewById(R.id.edt_phone);
        //sheha=findViewById(R.id.edt_ShehaAutoComplete);
        address=findViewById(R.id.edt_Address);
        btn_request=findViewById(R.id.btn_request);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.sheha_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname= fname.getText().toString();
                String secondname=sname.getText().toString();
                String lastname=lname.getText().toString();
                String phonenumber =phone.getText().toString();
                String leader=textView.getText().toString();
                String place=address.getText().toString();

                if (firstname.isEmpty()){
                    fname.setError("Tafadhali Ingiza Jina Lako");
                }else if(secondname.isEmpty()){
                    sname.setError("Tafadhali Ingiza Jina Pili");
                }else if(lastname.isEmpty()){
                    sname.setError("Tafadhali Ingiza Jina Tatu");
                }else if(phonenumber.isEmpty()){
                    phone.setError("Tafadhali Ingiza Namba ya simu");
                }else if(leader.isEmpty()){
                    textView.setError("Tafadhali Ingiza Jina la Sheha wako");
                }else if(place.isEmpty()){
                    address.setError("Tafadhali Ingiza Jina  la Mtaa/Eneo Unaloishi");
                }else{
                    Toast.makeText(RequestActivity.this, "Ombi lako linashughulikiwa," +
                            "Utapokea Ujumbe katika Simu na Ramani ya Kituo Chako", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RequestActivity.this,MapsActivity.class));

                }
            }
        });

    }
}