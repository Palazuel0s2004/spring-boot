package com.snpp.agenda_pediatrica.domain;

import java.util.List;

/**
 * Catálogo del esquema regular de vacunación (PAI - MSPBS Paraguay).
 * Fuente de referencia: https://pai.mspbs.gov.py/esquema-de-vacunacion-2-2/
 */
public final class EsquemaVacunacionPAI {

    private EsquemaVacunacionPAI() {
    }

    public static final List<EsquemaDosis> ESQUEMA = List.of(
        new EsquemaDosis("BCG", "Protección contra la tuberculosis", 0),
        new EsquemaDosis("Hepatitis B pediátrica", "Protección contra el virus de la hepatitis B", 0),

        new EsquemaDosis("Rotavirus (1ra dosis)", "Protección contra gastroenteritis por rotavirus", 60),
        new EsquemaDosis("IPV (1ra dosis)", "Polio inactivada", 60),
        new EsquemaDosis("Pentavalente (1ra dosis)", "Difteria, tos convulsa, tétanos, hepatitis B, Hib", 60),
        new EsquemaDosis("Neumococo 13v (1ra dosis)", "Protección contra neumonía, meningitis y otitis", 60),

        new EsquemaDosis("Rotavirus (2da dosis)", "Protección contra gastroenteritis por rotavirus", 120),
        new EsquemaDosis("IPV (2da dosis)", "Polio inactivada", 120),
        new EsquemaDosis("Pentavalente (2da dosis)", "Difteria, tos convulsa, tétanos, hepatitis B, Hib", 120),
        new EsquemaDosis("Neumococo 13v (2da dosis)", "Protección contra neumonía, meningitis y otitis", 120),

        new EsquemaDosis("Pentavalente (3ra dosis)", "Difteria, tos convulsa, tétanos, hepatitis B, Hib", 180),
        new EsquemaDosis("IPV (3ra dosis)", "Polio inactivada", 180),
        new EsquemaDosis("Influenza estacional", "Cuadros graves y complicaciones por influenza", 180),

        new EsquemaDosis("Neumococo 13v (refuerzo)", "Protección contra neumonía, meningitis y otitis", 365),
        new EsquemaDosis("SPR (1ra dosis)", "Sarampión, paperas y rubéola", 365),
        new EsquemaDosis("Fiebre amarilla", "Dosis única contra fiebre amarilla", 365),

        new EsquemaDosis("Varicela (1ra dosis)", "Cuadros graves y complicaciones por varicela", 450),
        new EsquemaDosis("Hepatitis A", "Dosis única contra hepatitis A", 450),

        new EsquemaDosis("Hexavalente (refuerzo)", "Difteria, tétanos, tos convulsa, hepatitis B, polio, Hib", 540),
        new EsquemaDosis("SPR (2da dosis)", "Sarampión, paperas y rubéola", 540),

        new EsquemaDosis("DPT (refuerzo)", "Difteria, tos convulsa, tétanos", 1460),
        new EsquemaDosis("bOPV (refuerzo)", "Polio bivalente 1 y 3", 1460),

        new EsquemaDosis("Varicela (2da dosis)", "Segunda dosis de refuerzo", 1825)
    );
}