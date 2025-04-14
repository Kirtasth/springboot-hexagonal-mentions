package com.kirtasth.springboot.menciones.app.mencionesappfinal.discord.infrastructure.controller;

import org.springframework.http.ResponseEntity;

public interface DiscordController {

    ResponseEntity<?> message() throws InterruptedException;

    ResponseEntity<?> setNames() throws InterruptedException;
}
