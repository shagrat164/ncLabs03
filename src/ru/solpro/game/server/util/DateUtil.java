/*
 * @(#)DateUtil.java 1.0 11.01.2017
 */

package ru.solpro.game.server.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class DateUtil {

    /**
     * Шаблон даты, используемый для преобразования.
     */
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    /**
     * Шаблон времени, используемый для преобразования.
     */
    private static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * Форматировщик даты.
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Форматировщик времени.
     */
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern(TIME_PATTERN);

    private DateUtil() {}

    /**
     * Возвращает полученную дату в виде хорошо отформатированной строки.
     * Используется определённый выше {@link DateUtil#DATE_PATTERN}.
     *
     * @param date - дата, которая будет возвращена в виде строки
     * @return отформатированную строку
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Возвращает полученное время в виде хорошо отформатированной строки.
     * Используется определённый выше {@link DateUtil#TIME_PATTERN}.
     *
     * @param time - время, которое будет возвращено в виде строки
     * @return отформатированную строку
     */
    public static String formatTime(LocalTime time) {
        if (time == null) {
            return null;
        }
        return TIME_FORMATTER.format(time);
    }

    /**
     * Преобразует строку, которая отформатирована по правилам
     * шаблона {@link DateUtil#DATE_PATTERN} в объект {@link LocalDate}.
     *
     * Возвращает null, если строка не может быть преобразована.
     *
     * @param dateString - дата в виде String
     * @return объект даты или null, если строка не может быть преобразована
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Преобразует строку, которая отформатирована по правилам
     * шаблона {@link DateUtil#TIME_PATTERN} в объект {@link LocalTime}.
     *
     * Возвращает null, если строка не может быть преобразована.
     *
     * @param timeString - дата в виде String
     * @return объект даты или null, если строка не может быть преобразована
     */
    public static LocalTime parseTime(String timeString) {
        try {
            return TIME_FORMATTER.parse(timeString, LocalTime::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Проверяет, является ли строка корректной датой.
     *
     * @param dateString
     * @return true, если строка является корректной датой
     */
    public static boolean validDate(String dateString) {
        // Пытаемся разобрать строку.
        return DateUtil.parseDate(dateString) != null;
    }

    /**
     * Проверяет, является ли строка корректным временем.
     *
     * @param timeString строка времени.
     * @return true, если строка является корректной датой.
     */
    public static boolean validTime(String timeString) {
        // Пытаемся разобрать строку.
        return DateUtil.parseTime(timeString) != null;
    }

    /**
     * Возвращает текущее дату и время по правилам
     * шаблона {@link DateUtil#DATE_FORMATTER} и {@link DateUtil#TIME_PATTERN}
     * @return строка. Текущее дата и время.
     */
    public static String getCurrentDateTime() {
        String currentDate = formatDate(LocalDate.now());
        String currentTime = formatTime(LocalTime.now());
        return currentDate + " " + currentTime;
    }
}
