package myboot.app4.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestHelloRestController {
    String url = "http://localhost:8081/api";

    @Test
    void testHello(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url+"/hello", String.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals("Hello", responseEntity.getBody());
    }

    @Test
    void testList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(url + "/list", List.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Arrays.asList(10, 20, 30),response.getBody());
    }

    @Test
    void testHelloWithMessage() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/hello/world", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals("Hello world",response.getBody());
    }

    @Test
    void testHello2() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/hello", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals("Hello",response.getBody());

    }

    @Test
    void testNotFound() {
        RestTemplate restTemplate = new RestTemplate();
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restTemplate.getForEntity(url + "/notFound", String.class);});
        //ResponseEntity<String> response = restTemplate.getForEntity(url + "/noContent", String.class);
        //Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void testNoContent() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/noContent", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void testHeaders() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("myHeader", "myHeaderValue");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(headers, entity.getHeaders());
        ResponseEntity<String> request = restTemplate.exchange(url,HttpMethod.POST, entity, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}
