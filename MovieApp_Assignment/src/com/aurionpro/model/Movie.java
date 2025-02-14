package com.aurionpro.model;

import java.io.Serializable;

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	private int movieID;
	private String name;
	private int year;
	private String genre;

	public Movie(int movieID, String name, int year, String genre) {
		this.movieID = movieID;
		this.name = name;
		this.year = year;
		this.genre = genre;
	}

	public int getMovieID() {
		return movieID;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return "\nMovie ID: " + movieID + "\nName: " + name + "\nYear: " + year + "\nGenre: " + genre;
	}
}