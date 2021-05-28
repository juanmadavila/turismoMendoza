package com.quintoimpacto.turismomendoza.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fecha {

    public static SimpleDateFormat FECHA = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("es"));

    public static Date parseFechaGuiones(String f) throws java.text.ParseException {

        try {
            return FECHA.parse(f);
        } catch (java.text.ParseException ex) {
            return null;
        }
    }

    public static String format(Date f) {
        try {
            return FECHA.format(f);
        } catch (Exception e) {
            return null;
        }
    }
}

