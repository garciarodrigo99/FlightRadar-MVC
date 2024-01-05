package es.ull.patrones.practica7;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    public static String getFormatedDate(Integer hora){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(hora), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
