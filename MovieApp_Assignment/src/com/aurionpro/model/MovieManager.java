package com.aurionpro.model;

import java.io.*;
import java.util.*;

public class MovieManager {
	private List<Movie> movies;
	private static final int MAX_MOVIES = 5;
	private static final String FILE_PATH = "C:/Users/prathamesh.tripathi/Desktop/prathamesh_aurionpro/movies.ser";

	public MovieManager() {
		movies = loadMovies();
	}

	public void addMovie(Movie movie) {
		if (movies.size() >= MAX_MOVIES) {
			System.out.println(" Maximum capacity reached (" + MAX_MOVIES + "), can't add more");
			return;
		}

		for (Movie m : movies) {
			if (m.getMovieID() == movie.getMovieID()) {
				System.out.println("Movie with ID " + movie.getMovieID() + " already exists");
				return;
			}
		}

		movies.add(movie);
		saveMovies();
		System.out.println("Movie added successfully");
	}

	public void displayMovies() {
		if (movies.isEmpty()) {
			System.out.println("No movies available.");
		} else {
			System.out.println("\nMovie List:");
			for (Movie movie : movies) {
				System.out.println(movie);
			}
		}
	}

	public void clearMovies() {
		movies.clear();
		saveMovies();
		System.out.println("All movies have been removed.");
	}

	private void saveMovies() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			oos.writeObject(movies);
		} catch (IOException e) {
			System.out.println("Exception saving movies: " + e.getMessage());
		}
	}

	private List<Movie> loadMovies() {
		File file = new File(FILE_PATH);
		if (!file.exists()) {
			return new ArrayList<>();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			return (List<Movie>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Exception loading movies therefor starting with an empty list.");
			return new ArrayList<>();
		}
	}
}