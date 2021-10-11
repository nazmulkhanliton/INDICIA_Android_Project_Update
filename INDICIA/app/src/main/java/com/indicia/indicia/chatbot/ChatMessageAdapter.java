package com.indicia.indicia.chatbot;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indicia.indicia.R;

import java.util.ArrayList;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessageModel> {

    private final Context context;
    private final int resource;

    public ChatMessageAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ChatMessageModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(resource, parent, false);
        }
        ChatMessageModel chatMessageModel = getItem(position);
        if (chatMessageModel != null) {
            TextView textViewMessage = view.findViewById(R.id.textview_chat_message_text);
            TextView textViewTime = view.findViewById(R.id.textview_chat_message_time);
            View listViewChatObject = view.findViewById(R.id.listview_chat_object);
            if (position % 2 == 1) {
                listViewChatObject.setBackgroundResource(R.drawable.custom_view_chat_send_shape);
                // textViewMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                // textViewTime.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                textViewMessage.setTextColor(Color.BLACK);
                textViewTime.setTextColor(Color.BLACK);
            } else {
                listViewChatObject.setBackgroundResource(R.drawable.custom_view_chat_received_shape);
            }
            textViewMessage.setText(chatMessageModel.getMessage());
            textViewTime.setText(chatMessageModel.getTime());
        }
        return view;
    }
}
