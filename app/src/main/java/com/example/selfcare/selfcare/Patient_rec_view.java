package com.example.selfcare.selfcare;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by h on 29/04/2016.
 */
public class Patient_rec_view extends Activity {
    ListView lv;
    ListView lv2;

    Context context;

    ArrayList temp=new ArrayList();
    ArrayList press=new ArrayList();
    ArrayList hearts=new ArrayList();
    ArrayList time=new ArrayList();
    // public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    public static String[] prgmNameList ;
    public static String[] prgmNameList2 ;
    public static String[] prgmNameList3 ;
    public static String[] prgmNameList4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_patient_record);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", 0);
       String emails = sharedpreferences.getString("Paient_email", "null");
        Toast.makeText(this, emails, Toast.LENGTH_LONG).show();
        GetDataOfPatient getdata=new GetDataOfPatient(this);
        temp=getdata.alltemp(emails);
        hearts=getdata.allhearts(emails);
        press=getdata.allpressur(emails);
        time=getdata.alltimes(emails);

        prgmNameList=new String[hearts.size()];
        prgmNameList2=new String[press.size()];
        prgmNameList3=new String[temp.size()];
        prgmNameList4=new String[time.size()];


        for (int i=0;i<hearts.size();i++)
        {
            prgmNameList[i]=hearts.get(i).toString();

        }
        for (int i=0;i<press.size();i++)
        {

            prgmNameList2[i]=press.get(i).toString();

        }
        for (int i=0;i<temp.size();i++)
        {

            prgmNameList3[i]=temp.get(i).toString();

        }
        for (int i=0;i<time.size();i++)
        {

            prgmNameList4[i]=time.get(i).toString();
        }
        if (temp==null)
            Toast.makeText(this,"temp", Toast.LENGTH_LONG).show();

        if (press==null)
            Toast.makeText(this,"p", Toast.LENGTH_LONG).show();

        if (hearts==null)
            Toast.makeText(this,"hearte", Toast.LENGTH_LONG).show();
        if (time==null)
            Toast.makeText(this,"time", Toast.LENGTH_LONG).show();


        context = this;

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmNameList2, prgmNameList3, prgmNameList4));

    }

    class CustomAdapter extends BaseAdapter {


        String[] result;
        String[] result2;
        String[] result3;
        String[] result4;
        Context context;
        int[] imageId;

        private LayoutInflater inflater = null;

        public CustomAdapter(Patient_rec_view mainActivity, String[] prgmNameList, String[] prgmNameList2, String[] prgmNameList3, String[] prgmNameList4) {
            // TODO Auto-generated constructor stub
            result = prgmNameList;
            result2 = prgmNameList3;
            result3 = prgmNameList2;
            result4=prgmNameList4;
            context = mainActivity;
            // imageId=prgmImages;
            inflater = (LayoutInflater) context.
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

        public class Holder {
            TextView tv;
            TextView tv2;
            TextView tv3;
            TextView tv4;

            ImageView img;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.follow_doc_patient_rec, null);

            holder.tv = (TextView) rowView.findViewById(R.id.textView);
            holder.tv2 = (TextView) rowView.findViewById(R.id.textView2);
            holder.tv3 = (TextView) rowView.findViewById(R.id.textView3);

            holder.tv4 = (TextView) rowView.findViewById(R.id.textView4);
            holder.tv.setText(result[position]);
            holder.tv2.setText(result2[position]);
            holder.tv3.setText(result3[position]);
            holder.tv4.setText(result4[position]);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
                }
            });
            return rowView;
        }


    }}