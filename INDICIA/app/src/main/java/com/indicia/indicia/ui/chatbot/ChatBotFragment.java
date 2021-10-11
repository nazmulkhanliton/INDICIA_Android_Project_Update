package com.indicia.indicia.ui.chatbot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.indicia.indicia.R;
import com.indicia.indicia.chatbot.ChatMessageAdapter;
import com.indicia.indicia.chatbot.ChatMessageModel;
import com.indicia.indicia.lib.DateTime;

import java.util.ArrayList;
import java.util.Objects;

public class ChatBotFragment extends Fragment {

    private ChatBotViewModel chatBotViewModel;
    private ChatMessageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<ChatMessageModel> messages = new ArrayList<>();
        adapter = new ChatMessageAdapter(Objects.requireNonNull(getContext()), R.layout.custom_view_listview_object_chat, messages);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatBotViewModel = ViewModelProviders.of(this).get(ChatBotViewModel.class);
        chatBotViewModel.getMessages().observe(this, new Observer<ArrayList<ChatMessageModel>>() {
            @Override
            public void onChanged(ArrayList<ChatMessageModel> chatMessageModels) {
                adapter.clear();
                adapter.addAll(chatMessageModels);
                adapter.notifyDataSetChanged();
            }
        });
        View root = inflater.inflate(R.layout.fragment_chatbot, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editTextChatMessage = view.findViewById(R.id.edittext_chat_message_input);
        final ListView listViewChatMessage = view.findViewById(R.id.listview_chat);
        listViewChatMessage.setAdapter(adapter);
        Button buttonSendMessage = view.findViewById(R.id.button_send_message);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextChatMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    chatBotViewModel.addMessage(new ChatMessageModel(message, DateTime.getDateTimeForChatBot()));
                }
                editTextChatMessage.setText("");
            }
        });

    }
}