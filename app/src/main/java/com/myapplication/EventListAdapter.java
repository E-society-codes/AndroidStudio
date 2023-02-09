package com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class EventListAdapter extends BaseAdapter {
    Context context;
    ArrayList<EventLangModel> eventLangModelArrayList;

    public EventListAdapter(Context context, ArrayList<EventLangModel> arrayList) {
        this.context = context;
        this.eventLangModelArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return eventLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.raw_list, null);

        TextView TvData = view.findViewById(R.id.tv_data);


        TvData.setText(eventLangModelArrayList.get(position).get_id() + ""
                + eventLangModelArrayList.get(position).getDate() + ""
                + eventLangModelArrayList.get(position).getEventDate() + ""
                + eventLangModelArrayList.get(position).getEventDetails() + "" + eventLangModelArrayList.get(position).getRent());

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = eventLangModelArrayList.get(position).get_id();
                Log.e("id in edit :", id);

                Intent intent = new Intent(context, eventUpdateActivity.class);
                intent.putExtra("EVENT_ID", id);
                intent.putExtra("EVENT DATE", eventLangModelArrayList.get(position).getDate());
                intent.putExtra("EVENT END DATE", eventLangModelArrayList.get(position).getEventDate());
                intent.putExtra("EVENT DETAILS", eventLangModelArrayList.get(position).getEventDetails());

                context.startActivity(intent);
            }
        });

        return (view);


    }
}

