package et.com.gebeya.asqualacomservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.toggle.AbilityToggle;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramBot;

@Component
public class BotConfig extends AbilityBot {
    @Value("${spring.telegram_bot.token}")
    private String bot_Token;
    @Autowired
    private TelegramBot telegramBot;

    public BotConfig(Environment environment){
        super(environment.getProperty("${BOT_TOKEN}"),"bootUsername");
    }
    @Override
    public long creatorId() {
        return 0;
    }
}
