package org.example;

import java.util.Date;

public class Movie {
    private int id;
    private String title;
    private String description;
    private Date releaseDate;
    private int durationMinutes;
    private double rating;

    public Movie(int id, String title, String description, Date releaseDate, int durationMinutes, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    // Геттеры для доступа к полям класса
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getRating() {
        return rating;
    }

    // Сеттеры для установки значений полей класса (не обязательны для CRUD операций)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
