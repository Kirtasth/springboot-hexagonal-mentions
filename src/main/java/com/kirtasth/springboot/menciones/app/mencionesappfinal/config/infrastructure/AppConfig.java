package com.kirtasth.springboot.menciones.app.mencionesappfinal.config.infrastructure;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:.env")
public class AppConfig {
}
