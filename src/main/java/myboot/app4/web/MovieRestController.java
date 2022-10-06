package myboot.app4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import myboot.app1.dao.MovieRepository;
import myboot.app1.model.Movie;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    MovieRepository repo;

    @GetMapping("/movies")
    public Iterable<Movie> getMovies() {
        return repo.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable int id) {
        return repo.findById(id).get();
    }

    @RequestMapping("/movies/{id}")
    public void deleteMovie(@PathVariable int id) {
        repo.deleteById(id);
    }


}