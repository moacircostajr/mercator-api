package br.com.mercator.api.arquitetura;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static Date localDateToDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) return null;
        return new Date(date.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}