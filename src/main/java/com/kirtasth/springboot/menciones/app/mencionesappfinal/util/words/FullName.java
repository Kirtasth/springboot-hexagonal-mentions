package com.kirtasth.springboot.menciones.app.mencionesappfinal.util.words;


public class FullName {
    String firstName;
    String[] surnames;

    public FullName(String fullName) {
        String[] parts = fullName.trim().split("\\s+");
        this.firstName = parts[0];
        this.surnames = new String[parts.length - 1];
        System.arraycopy(parts, 1, this.surnames, 0, parts.length - 1);
    }
}