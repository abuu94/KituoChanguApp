package com.example.kituochanguapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataActivity extends AppCompatActivity {
    EditText edt_sheha,edt_station,edt_Xcordina,edt_Ycordina;
    Button btn_save,btn_edit,btn_view,btn_delete;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        edt_sheha=findViewById(R.id.edt_sheha);
        edt_station=findViewById(R.id.edt_kituo);
        edt_Xcordina=findViewById(R.id.edt_Xcordina);
        edt_Ycordina=findViewById(R.id.edt_Ycordina);
        btn_save=findViewById(R.id.btn_save);
        btn_edit=findViewById(R.id.btn_edit);
        btn_view=findViewById(R.id.btn_view);
        btn_delete=findViewById(R.id.btn_delete);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Hifadhi");
        dialog.setMessage("Tafadhali Subiri...");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sheha,station,Xcord,Ycord;
                sheha=edt_sheha.getText().toString();
                station=edt_station.getText().toString();
                Xcord=edt_Xcordina.getText().toString();
                Ycord=edt_Ycordina.getText().toString();

                if (sheha.isEmpty()){
                    edt_sheha.setError("Tafadhali ingiza jina");
                }else if(station.isEmpty()){
                    edt_station.setError("Tafadhali ingiza Kituo");
                }else if(Xcord.isEmpty()){
                    edt_Xcordina.setError("Tafadhali ingiza Latitude");
                }else if(Ycord.isEmpty()){
                    edt_Ycordina.setError("Tafadhali ingiza Longitude");
                }else{
                    long time =System.currentTimeMillis();
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Station/"+time);
                    Data dt=new Data(sheha,station,Xcord,Ycord,String.valueOf(time));
                    dialog.show();
                    ref.setValue(dt).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()){
                                message("VEMA","Umefanikiwa Kuhifadhi data");
                                clear();
                            }else{
                                message("IMEFELI","Data haijahifadhiwa");
                            }
                        }
                    });

                }

            }

        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewdataActivity.class));
            }
        });
    }

    public void message(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void clear(){
        edt_sheha.setText("");
        edt_station.setText("");
        edt_Xcordina.setText("");
        edt_Ycordina.setText("");
    }


}