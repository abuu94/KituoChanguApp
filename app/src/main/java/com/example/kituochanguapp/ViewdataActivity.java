package com.example.kituochanguapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewdataActivity extends AppCompatActivity {
    ArrayList<Data>  datum;
    RecyclerView mRecycleView;
    CustomAdapter adapter;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Inaleta..");
        dialog.setMessage("Tafadhali Subiri");

        datum=new ArrayList();
        mRecycleView=findViewById(R.id.mrecyclerView);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new CustomAdapter(this,datum);
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Station/"+time);
       // long time =System.currentTimeMillis();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Station");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datum.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Data data = snap.getValue(Data.class);
                    datum.add(data);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                message("DATABASE LOCKED","Sorry, we couldn't access the DB. Contact you app developer for assistance");
            }
        });
        mRecycleView.setAdapter(adapter);


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
}