package com.example.selfcare.selfcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listview extends Activity {

    ListView lv;
    Context context;

    ArrayList paient;
    ArrayList name;
    // public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    public static String[] prgmNameList = {"Let Us C", "c++", "JAVA", "Jsp", "Microsoft .Net", "Android", "PHP", "Jquery", "JavaScript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        context = this;
        paient=new ArrayList();
        name=new ArrayList();

        SharedPreferences sharedpreferences = getSharedPreferences("PREFERENCES", 0);
        String emails = sharedpreferences.getString("dremail", "null");
        Get_sever_data_for_doctor getdata=new Get_sever_data_for_doctor(this);
        paient= getdata.allEmail(emails);
        name= getdata.allfname(emails);
        lv = (ListView) findViewById(R.id.listView);
        if (paient!=null) {
            String[] st=new String[paient.size()];
            String[] stnam=new String[paient.size()];
            for (int i=0;i<paient.size();i++)
            {  st[i]=paient.get(i).toString();
                stnam[i]=name.get(i).toString();

            }
            lv.setAdapter(new Custom(this, st,stnam));
        }
    }

  class Custom extends BaseAdapter {

        String [] result;
        String [] name;
        Context context;
        int [] imageId;

        private LayoutInflater inflater=null;
        public Custom(Listview mainActivity, String[] prgmNameList, String[] names) {
            // TODO Auto-generated constructor stub
            result=prgmNameList;
            name=names;
            context=mainActivity;
            // imageId=prgmImages;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView tv;
            TextView small;
            ImageView img;
        }
        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            // TODO Auto-generated method stub
            Get_sever_data_for_doctor getdata=new Get_sever_data_for_doctor(context);
            Holder holder=new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.activity_dynamic_demo, null);
            holder.tv=(TextView) rowView.findViewById(R.id.textView1);
            holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
            holder.tv.setText(name[position]);
            holder.small=(TextView) rowView.findViewById(R.id.textView4);
            holder.small.setText(result[position]);
            holder.img.setImageResource(R.drawable.smallsgs);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
                    String email=result[position];
                    SharedPreferences sharedpreferences= getSharedPreferences("MyPREFERENCES",0);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString( "profemail",email);
                    editor.commit();
                    Intent launchHome = new Intent(context, Doc_Patient_Profile.class);
                    startActivity(launchHome);
                }
            });
            return rowView;
        }

    }
}