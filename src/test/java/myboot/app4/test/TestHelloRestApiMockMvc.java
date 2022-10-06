package myboot.app4.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestHelloRestApiMockMvc {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHello() throws Exception {
        mvc.perform(get("/api/hello")).andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(content().string("Hello"));
    }

    @Test
    public void testHelloJohn() throws Exception {
        mvc.perform(get("/api/hello/john")).andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(content().string("Hello john"));
    }

    @Test
    public void testList() throws Exception {
        mvc.perform(get("/api/list")).andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(content().json("[10,20,30]"));
    }

}