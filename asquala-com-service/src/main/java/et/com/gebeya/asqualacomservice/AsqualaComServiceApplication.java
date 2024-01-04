package et.com.gebeya.asqualacomservice;

import et.com.gebeya.asqualacomservice.config.BotConfig;
import et.com.gebeya.asqualacomservice.service.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@EnableDiscoveryClient
@SpringBootApplication
@Component
public class AsqualaComServiceApplication {
    // ApplicationContext ctx = new AnnotationConfigApplicationContext(BotConfig.class);

    public static void main(String[] args) throws TelegramApiException {

        SpringApplication.run(AsqualaComServiceApplication.class, args);
        TelegramBot telegramBot = new TelegramBot();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

}
