package com.example.dhemi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;
    Button bt1,bt2;
    TextView t1;
    Person p1=new Person();
    Person p2=new Person();
    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference databaseReference =firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        bt1=findViewById(R.id.bt1);
        t1=findViewById(R.id.t1);
        bt2=findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1.setName(String.valueOf(et1.getText()));
                p1.setAge(Integer.parseInt(String.valueOf(et2.getText())));
                Random r=new Random();
                int n=r.nextInt();

                databaseReference.child(String.valueOf(n)).setValue(p1);

            }


        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchId= et3.getText().toString();
                databaseReference.child(searchId);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        p2 = dataSnapshot.getValue(Person.class);
                        if (p2 != null) {
                            t1.setText(p2.getName());
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"No Person Found",Toast.LENGTH_SHORT).show();

                        }




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }




}

