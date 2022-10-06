package myboot.app4.test;

import myboot.app1.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
public class TestMovieRestController {


    String url = "http://localhost:8081/api";

    @Test
    void testGetMovies(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url+"/movies", List.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testGetMovie(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Movie> responseEntity = restTemplate.getForEntity(url+"/movies/1", Movie.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testGetMovieNoFound(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Movie> responseEntity = restTemplate.getForEntity(url+"/movies/99", Movie.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }








}
