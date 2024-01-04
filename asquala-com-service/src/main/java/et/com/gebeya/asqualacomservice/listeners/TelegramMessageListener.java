package et.com.gebeya.asqualacomservice.listeners;
import et.com.gebeya.asqualacomservice.service.TelegramBot;
import et.com.gebeya.asqualacomservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramMessageListener {
    @Autowired
    private TelegramBot telegramBot;

    @KafkaListener(topics ={Constants.EMAIL_STUDENT_TOPIC, Constants.EMAIL_STUDENT_TOPIC} ,
            groupId = "group1",
            containerFactory = "studentListenerFactory")
    public void listen(String message) {
        // Process the incoming message from Kafka
        // Send the message to your Telegram bot using the Telegram Bot API
        telegramBot.sendMessage(message);
    }
}