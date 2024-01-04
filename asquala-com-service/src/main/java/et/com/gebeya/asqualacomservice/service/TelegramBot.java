package et.com.gebeya.asqualacomservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${BOT_TOKEN}")
    private String botToken;
    @Value("${BOT_USERNAME}")
    private String botUserName;
    private Long chatId;

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        // Process incoming messages
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
             chatId = update.getMessage().getChatId();
            try {
                execute(new SendMessage(chatId.toString(), messageText));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessage(String messageText) {

        try {

            SendMessage message= new SendMessage();

            message.setChatId(chatId.toString());

            message.setText(messageText);

            execute(message);


        }catch (TelegramApiException e) {

            e.printStackTrace();

        }

 }
}







