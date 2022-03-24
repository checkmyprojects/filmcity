package org.factoriaf5.filmcity.controllers;

import org.factoriaf5.filmcity.domain.Movie;
import org.factoriaf5.filmcity.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesController {

    private final MovieRepository movieRepository;

    @Autowired
    MoviesController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie findMovie(@PathVariable Long id) {
        return movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @DeleteMapping("/movies/{id}")
    public Movie deleteMovieById(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
        movieRepository.deleteById(id);
        return movie;
    }
    @PutMapping("/movies")
    public Movie updateMovieById(@RequestBody Movie movie) {
        movieRepository.findById(movie.getId()).orElseThrow(MoviesNotFoundException::new);
        return movieRepository.save(movie);
    }
    /*@PutMapping("/movies/{id}/book?renter={renter}")
    public Movie updateMovieById(@PathVariable Long id, @PathVariable String renter) {
        Movie movie = movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
        movie.setRented(renter);
        movie.setBooked(true);
        return movieRepository.save(movie);

    }*/
    /*@PutMapping("/movies/{id}/{booked}?renter={renter}")
    public Movie updateMovieRented(@PathVariable Long id, @PathVariable String renter) {
        Movie movie = movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
        movie.setRented(renter);
        movie.setBooked(true);
        return movieRepository.save(movie);

    }*/
    /* test
    @PutMapping("/movies/{id}/booked?renter={renter}")
    public Movie updateMovieRented(@PathVariable Long id, @RequestParam String renter) {
        Movie movie = movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
        movie.setRented(renter);
        movie.setBooked(true);
        return movieRepository.save(movie);

    }*/
    @PutMapping("/movies/{id}/book")
    public Movie updateMovieRented(@PathVariable Long id, @RequestParam (value = "renter")String renter) {
        Movie movie = movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
        movie.setRented(renter);
        movie.setBooked(true);
        return movieRepository.save(movie);

    }
    @PutMapping("/movies/{id}/return")
    public Movie clearMovieRented(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(MoviesNotFoundException::new);
        movie.setRented(null);
        movie.setBooked(false);
        return movieRepository.save(movie);

    }
//git checkout main => git merge jesus
}