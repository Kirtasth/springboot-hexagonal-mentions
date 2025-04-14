package com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.infrastructure.controller;

import com.google.api.client.http.HttpStatusCodes;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.domain.DiscordService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.infrastructure.BotConfig;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.GivenClassService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.util.words.WordDistance;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/discord")
@RequiredArgsConstructor
public class DiscordControllerImpl implements DiscordController{

    private final BotConfig botConfig;

    private final DiscordService discordService;

    @Override
    @GetMapping("/send_message")
    public ResponseEntity<?> message() throws InterruptedException {
        discordService.sendMentionMessage();
        return ResponseEntity.ok().body("Discord message sent");

    }

    @Override
    @GetMapping("set_names")
    public ResponseEntity<?> setNames() throws InterruptedException {
        discordService.setDiscordIds();
        return ResponseEntity.ok().body("Discord names updated.");
    }
}
