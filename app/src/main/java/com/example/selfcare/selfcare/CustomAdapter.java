package com.example.selfcare.selfcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by h on 28/04/2016.
 */
public class CustomAdapter extends BaseAdapter {

    String [] result;
    String [] name;
    Context context;
    int [] imageId;

    private static LayoutInflater inflater=null;
    public CustomAdapter(Listview mainActivity, String[] prgmNameList, String[] names) {
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

            }
        });
        return rowView;
    }

}