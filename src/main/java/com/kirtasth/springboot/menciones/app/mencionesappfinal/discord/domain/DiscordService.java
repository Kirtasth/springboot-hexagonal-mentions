package com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.domain;

public interface DiscordService {

    void sendMentionMessage() throws InterruptedException;

    void setDiscordIds() throws InterruptedException;
}
