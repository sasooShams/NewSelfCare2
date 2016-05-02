package com.example.selfcare.selfcare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by h on 29/04/2016.
 */
public class Patient_profile extends Activity {

    EditText name ,Email,phone,gen,weight ,height;
    ImageView imageView;
    SharedPreferences sharedpreferences;
    String email;
    Button records;
    GetDataOfPatient getdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profile);

        getdata=new GetDataOfPatient(this);
        /*
        sharedpreferences =getSharedPreferences("PREFERENCES", 0);
        email = sharedpreferences.getString("p_emails", "null");*/
        sharedpreferences = getSharedPreferences("MyPREFERENCES", 0);
        email = sharedpreferences.getString("Paient_email", "null");

        name =(EditText)findViewById(R.id.editText);
        Email =(EditText)findViewById(R.id.editText3);
        phone =(EditText)findViewById(R.id.editText5);
        gen =(EditText)findViewById(R.id.editText4);
        weight =(EditText)findViewById(R.id.editText6);
        height =(EditText)findViewById(R.id.editText7);
        records=(Button)findViewById(R.id.record);

        init();

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(Patient_profile.this,Patient_rec_view.class);
                startActivity(i);

            }
        });
    }
    String fname,lname,names,phones,gend,weights,heights;
    private void init() {
        fname=getdata.Fname(email);
        lname=getdata.Lname(email);
        names=fname+"\t"+"\t"+lname;
        phones=getdata.mobile(email);
        gend =getdata.gendre(email);
        weights=getdata.weight(email);
        heights=getdata.height(email);

        Email.setText(email);
        name.setText(names);
        phone.setText(phones);
        gen.setText(gend);
        weight.setText(weights);
        height.setText(heights);


    }




}

