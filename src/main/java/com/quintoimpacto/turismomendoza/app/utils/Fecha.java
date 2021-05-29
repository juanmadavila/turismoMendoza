package com.quintoimpacto.turismomendoza.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fecha {

    public static SimpleDateFormat FECHA = new SimpleDateFormat("yyyy-mm-dd", Locale.forLanguageTag("es"));

    public static Date parseFechaGuiones(String f) throws java.text.ParseException {

        int anio = Integer.valueOf(f.split("-")[0]);
        int mes = Integer.valueOf(f.split("-")[1]);
        int dia = Integer.valueOf(f.split("-")[2]);
        
        return new Date(anio-1900, mes-1, dia);
    }
    
    public static String format(Date f) {
        try {
            return FECHA.format(f);
        } catch (Exception e) {
            return null;
        }
    }
}

