package com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.infrastructure;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {

    private final String TOKEN;
    private final String GUILD_CHANNEL;
    private final String GUILD_ID;

    public BotConfig(){
        Dotenv dotenv = Dotenv.load();
        this.TOKEN = dotenv.get("DISCORD_TOKEN");
        this.GUILD_CHANNEL = dotenv.get("DISCORD_CHANNEL");
        this.GUILD_ID = dotenv.get("DISCORD_GUILD");
    }

}
