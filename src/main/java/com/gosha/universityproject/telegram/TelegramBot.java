//package com.gosha.universityproject.telegram;
//
//import com.gosha.universityproject.service.IssService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.net.URISyntaxException;
//
//
//@Component
//@RequiredArgsConstructor
//public class TelegramBot extends TelegramLongPollingBot {
//
//    private final IssService issService;
//
//    @Value("${telegram.bot.username}")
//    private String username;
//    @Value("${telegram.bot.token}")
//    private String token;
//
//    @Override
//    public String getBotUsername() {
//        return username;
//    }
//
//    @Override
//    public String getBotToken() {
//        return token;
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            if (message.hasText()) {
//                String text = message.getText();
//                if (text.equals("/position")){
//                    try {
//                        SendMessage sm = new SendMessage();
//                        sm.setText(issService.getIssPosition().toString());
//                        sm.setChatId(String.valueOf(message.getChatId()));
//                        try {
//                            execute(sm);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    } catch (URISyntaxException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }
//}
