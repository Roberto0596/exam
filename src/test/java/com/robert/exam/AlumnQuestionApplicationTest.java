package com.robert.exam;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class AlumnQuestionApplicationTest {
    @Autowired
    MockMvc mock;

    @Test
    @Order(0)
    void getAlumnQuestionTest() {
        try {
            mock.perform(get("/prueba/v1/answer-alumn/get")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void getAlumnQuestionByIdTest() {
        try {
            mock.perform(get("/prueba/v1/answer-alumn/get/1")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void saveAlumnQuestionTest() {
        try {
            mock.perform(post("/prueba/v1/answer-alumn/save").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                    "    \"name\": \"America/Lima\",\n" +
                    "    \"created_at\": null\n" +
                    "}")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void deleteAlumnQuestionTest() {
        try {
            mock.perform(delete("/prueba/v1/answer-alumn/delete/5")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void evaluateTest() {
        try {
            mock.perform(post("/prueba/v1/answer-alumn/evaluate?alumnId=1&testId=1")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
