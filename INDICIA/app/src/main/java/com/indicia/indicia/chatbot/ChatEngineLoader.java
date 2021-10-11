package com.indicia.indicia.chatbot;

import android.content.Context;

import com.indicia.indicia.R;

import java.util.ArrayList;

public class ChatEngineLoader {

    private static Context context = null;

    public ChatEngineLoader(Context context) {
        this.context = context;
    }

    public static ArrayList<ChatQuestionAnswer> load() {
        ArrayList<ChatQuestionAnswer> chatQuestionAnswers = new ArrayList<>();
        if (context != null) {
            int[] ids = {R.array.chatbot_replay_common_question};
            int count = 0;
            for (int i = 0; i < ids.length; i++) {
                String[] chat = context.getResources().getStringArray(ids[i]);
                count = count + appendToChat(chat, chatQuestionAnswers, count);
            }
        }
        return chatQuestionAnswers;
    }

    public static int appendToChat(String[] arrayList, ArrayList<ChatQuestionAnswer> chatQuestionAnswers, int count) {
        for (int i = 0; i < arrayList.length; i++) {
            String[] chat = arrayList[i].split("###");
            chatQuestionAnswers.add(new ChatQuestionAnswer(String.valueOf(i + 1), chat[0], chat[1]));
            count++;
        }
        return count;
    }
}
