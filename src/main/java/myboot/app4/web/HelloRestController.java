package myboot.app4.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloRestController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping(value="/list")
    public List<Integer> list(){
        return Arrays.asList(10,20,30);
    }

    @GetMapping("/hello/{message}")
    public String helloWithMessage(@PathVariable String message) {
        return "Hello " + message;
    }

    @GetMapping(value = "/hello2")
    public ResponseEntity<String> hello2() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping(value = "/notFound")
    public ResponseEntity<String> notFound() {
        HttpHeaders res  = new HttpHeaders();
        res.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Erreur 404", res, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/noContent")
    public ResponseEntity<String> noContent() {
        HttpHeaders res  = new HttpHeaders();
        res.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Erreur 204", res, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/headers")
    public ResponseEntity<String> headers(@RequestHeader String myHeader) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("resultHeader", myHeader.toUpperCase());
        var res = ResponseEntity.ok()//
                .headers(responseHeaders)//
                .header("xx", "yy")//
                .body("HEADER " + myHeader);
        return res;
    }


}
