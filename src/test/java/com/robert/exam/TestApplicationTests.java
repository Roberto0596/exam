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
public class TestApplicationTests {
    @Autowired
    MockMvc mock;

    @Test
    @Order(0)
    void getTestTest() {
        try {
            mock.perform(get("/prueba/v1/test/get")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void getTestByIdTest() {
        try {
            mock.perform(get("/prueba/v1/test/get/1")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void saveTestTest() {
        try {
            mock.perform(post("/prueba/v1/test/save").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                    "    \"test\": {\n" +
                    "        \"name\": \"examen de programacion 3\"\n" +
                    "    },\n" +
                    "    \"testQuestions\": [\n" +
                    "        {\n" +
                    "            \"question\": \"¿cuanto es dos mas dos?\",\n" +
                    "            \"answer1\": \"4\",\n" +
                    "            \"answer2\": \"234\",\n" +
                    "            \"answer3\": \"34\",\n" +
                    "            \"answer4\": \"34\",\n" +
                    "            \"value\": 100.0,\n" +
                    "            \"correctOption\": 1,\n" +
                    "            \"created_at\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"question\": \"¿cuanto es dos mas tres?\",\n" +
                    "            \"answer1\": \"5\",\n" +
                    "            \"answer2\": \"234\",\n" +
                    "            \"answer3\": \"34\",\n" +
                    "            \"answer4\": \"34\",\n" +
                    "            \"value\": 100.0,\n" +
                    "            \"correctOption\": 1,\n" +
                    "            \"created_at\": null\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void deleteTestTest() {
        try {
            mock.perform(delete("/prueba/v1/test/delete/5")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
