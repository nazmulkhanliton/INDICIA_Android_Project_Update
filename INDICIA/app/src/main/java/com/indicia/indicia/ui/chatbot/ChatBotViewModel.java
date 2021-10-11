package com.indicia.indicia.ui.chatbot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.indicia.indicia.chatbot.ChatEngine;
import com.indicia.indicia.chatbot.ChatMessageModel;
import com.indicia.indicia.lib.DateTime;

import java.util.ArrayList;

public class ChatBotViewModel extends ViewModel {
    private static ArrayList<ChatMessageModel> arrayListChatMessage;
    private MutableLiveData<ArrayList<ChatMessageModel>> listMessage;
    private static ChatEngine chatEngine;

    public ChatBotViewModel() {
        if (arrayListChatMessage == null) {
            arrayListChatMessage = new ArrayList<>();
            arrayListChatMessage.add(new ChatMessageModel("আমি চ্যাটবট \nআপনাকে কিভাবে সাহায্য করতে পারি?", DateTime.getDateTimeForChatBot()));
        }
        if (chatEngine == null) {
            chatEngine = new ChatEngine();
        }
        listMessage = new MutableLiveData<>();
        listMessage.postValue(arrayListChatMessage);

    }

    public void addMessage(ChatMessageModel message) {
        arrayListChatMessage.add(message);
        arrayListChatMessage.add(new ChatMessageModel(chatEngine.getReplay(message), DateTime.getDateTimeForChatBot()));
        listMessage.postValue(arrayListChatMessage);
    }

    public LiveData<ArrayList<ChatMessageModel>> getMessages() {
        return listMessage;
    }
}