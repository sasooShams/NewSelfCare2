package com.example.selfcare.selfcare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by sahar fathy on 3/13/2016.
 */
public class Doc_Patient_Profile extends Activity {
    public static final int Image_Gallery_REQUEST = 2 ;
    EditText name ,Email,phone,gen,weight ,height;
    ImageView imageView;
    SharedPreferences sharedpreferences;
    String email;
    Button records;
    Get_sever_data_for_doctor getdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profile);
        getdata=new Get_sever_data_for_doctor(this);
        sharedpreferences =getSharedPreferences("MyPREFERENCES", 0);
        email = sharedpreferences.getString("profemail", "null");

        imageView = (ImageView)findViewById(R.id.imageViewprofilepa);
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
                SharedPreferences ss= getSharedPreferences("PREFERENCES",0);
                SharedPreferences.Editor editor = ss.edit();
                editor.putString( "pemails",email);
                editor.commit();
Intent i =new Intent(Doc_Patient_Profile.this,Doc_patient_record.class);
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


    public void onImageGalleryClicked (View v){

        Intent photoPickerTintent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerTintent.setDataAndType(data,"image/*");
        startActivityForResult(photoPickerTintent,Image_Gallery_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Gallery_REQUEST){
            Uri imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap image = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to find the image try again later.", Toast.LENGTH_LONG).show();
            }
        }


    }
}

