package com.snpp.agenda_pediatrica.domain;

public record EsquemaDosis(
        String nombre,
        String descripcion,
        int diasDesdeNacimiento
) {
}