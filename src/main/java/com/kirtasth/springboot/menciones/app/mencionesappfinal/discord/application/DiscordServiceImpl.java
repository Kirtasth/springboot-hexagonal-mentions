package com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.application;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.domain.DiscordService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.infrastructure.BotConfig;
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
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {

    private final BotConfig botConfig;

    private final ProfessorService professorService;

    private final WordDistance wordDistance;

    @Override
    public void sendMentionMessage() throws InterruptedException {
        JDA jda = JDABuilder.createDefault(botConfig.getTOKEN())
                .build();
        jda.awaitReady();

        TextChannel textChannel = jda.getTextChannelById(botConfig.getGUILD_CHANNEL());
        Guild guild = jda.getGuildById(botConfig.getGUILD_ID());

        if (textChannel != null){
            textChannel.sendMessage("Hola desde java!!").queue();
        }
    }

    @Override
    public void setDiscordIds() throws InterruptedException {
        JDA jda = JDABuilder.createDefault(botConfig.getTOKEN())
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();
        jda.awaitReady();

        Guild guild = jda.getGuildById(botConfig.getGUILD_ID());

        if (guild != null) {
            List<String> names = new java.util.ArrayList<>();
            guild.loadMembers().onSuccess(members ->
                            names.addAll(members.stream().map(member -> member.getEffectiveName().isBlank() ?
                                    member.getNickname() : member.getEffectiveName()).toList())
                    )
                    .onError(throwable ->
                            System.err.println("Error loading members " + throwable.getMessage()));
            names.removeAll(Collections.singleton(null));
            List<Professor> professors = professorService.findAll();

            professors.forEach(professor -> {
                if (!Objects.equals(professor.getDiscordName(), "Discord")) {
                    String discordName = wordDistance.getMostLikelyOfList(professor.getName(), names);
                    List<Member> members = guild.getMembersByEffectiveName(discordName, false);
                    if (members.isEmpty()) {
                        members = guild.getMembersByNickname(discordName, false);
                    }
                    if (!members.isEmpty()) {
                        professor.setDiscordName(members.getFirst().getId());
                    }
                    professorService.save(professor);
                }
            });
        }
    }
}
