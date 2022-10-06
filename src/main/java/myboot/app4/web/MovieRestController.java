package myboot.app4.web;

import myboot.app1.web.MovieController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import myboot.app1.dao.MovieRepository;
import myboot.app1.model.Movie;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    MovieRepository repo;
    @Autowired
    LocalValidatorFactoryBean validationFactory;
    @Autowired
    MovieController movieController;

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

    @PatchMapping("/movies")
    public void populate() {
        movieController.init();
    }

    public Set<ConstraintViolation<Movie>> validate(Movie movie) {

        Set<ConstraintViolation<Movie>> violations = validationFactory.getValidator().validate(movie);
        return violations;
    }
    @PutMapping("/movies/{id}")
    public Map<String, String> putMovie(@RequestBody Movie m, @PathVariable int id) {

        // validating user movie
        Set<ConstraintViolation<Movie>> violations = validate(m);
        if (violations.isEmpty()) {
            Optional<Movie> movie = repo.findById(id);
            if (movie.isPresent()) {
                repo.save(m);
            } else {

            }
        }

        Map<String, String> errors = new HashMap<>();
        violations.forEach((violation) -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}