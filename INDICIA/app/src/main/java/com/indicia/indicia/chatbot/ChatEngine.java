package com.indicia.indicia.chatbot;

import java.util.ArrayList;

public class ChatEngine {

    private static ArrayList<ChatQuestionAnswer> chatQuestionAnswers;

    public ChatEngine() {
        if (chatQuestionAnswers == null) {
            chatQuestionAnswers = ChatEngineLoader.load();
        }
    }

    public String getReplay(ChatMessageModel chatMessageModel) {
        String message = chatMessageModel.getMessage();
        String time = chatMessageModel.getTime();
        for (int i = 0; i < chatQuestionAnswers.size(); i++) {
            if (message.matches(chatQuestionAnswers.get(i).getQuestion())) {
                return chatQuestionAnswers.get(i).getAnswer();
            }
        }
        return "দুঃখিত। এইটা আমার জ্ঞান এর বাইরে। আনুগ্রহ পুরবক অন্য কিছু জিজ্ঞেস করুন।";
    }
}
