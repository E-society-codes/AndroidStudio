package com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedbackListAdapter extends BaseAdapter {

    Context context;
    ArrayList<FeedbackLangModel> feedbackLangModelArrayList;

    public FeedbackListAdapter(Context context, ArrayList<FeedbackLangModel> arrayList) {
        this.context = context;
        this.feedbackLangModelArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return feedbackLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbackLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.raw_list, null);

        TextView tvData = view.findViewById(R.id.tv_data);
        tvData.setText(feedbackLangModelArrayList.get(position).get_id() + ""
                + feedbackLangModelArrayList.get(position).getHouseId()
                + feedbackLangModelArrayList.get(position).getDate() + ""
                + feedbackLangModelArrayList.get(position).getFeedback() + ""
                + feedbackLangModelArrayList.get(position).getAcknowledgement());

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = feedbackLangModelArrayList.get(position).get_id();

                Log.e("id in edit :",id);


                Intent intent = new Intent(context , FeedbackUpdate.class);

                intent.putExtra("_id",id);
                intent.putExtra("HOUSE_ID",feedbackLangModelArrayList.get(position).getHouseId());
                intent.putExtra("DATE", feedbackLangModelArrayList.get(position).getDate());
                intent.putExtra("FEEDBACK", feedbackLangModelArrayList.get(position).getFeedback());
                intent.putExtra("ACKNOWLEDGEMENT", feedbackLangModelArrayList.get(position).getAcknowledgement());

                context.startActivity(intent);
            }

        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1 = feedbackLangModelArrayList.get(position).get_id();
                Log.e("id in edit: ", id1);


                Intent intent = new Intent(context, FeedbackUpdate.class);
                intent.putExtra("FEEDBACK_ID", id1);
                intent.putExtra("FEEDBACK_HOUSE", feedbackLangModelArrayList.get(position).getHouseId());
                intent.putExtra("DATE", feedbackLangModelArrayList.get(position).getDate());
                intent.putExtra("FEEDBACK", feedbackLangModelArrayList.get(position).getFeedback());
                intent.putExtra("ACKNOWLEDGEMENT", feedbackLangModelArrayList.get(position).getAcknowledgement());

                context.startActivity(intent);
            }
        });

        return (view);

    }
}