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
public class TestAssignationApplicationTest {
    @Autowired
    MockMvc mock;

    @Test
    @Order(0)
    void getTestAssignationTest() {
        try {
            mock.perform(get("/prueba/v1/test-assignation/get")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void getTestAssignationByIdTest() {
        try {
            mock.perform(get("/prueba/v1/test-assignation/get/1")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void saveTestAssignationTest() {
        try {
            mock.perform(post("/prueba/v1/test-assignation/save").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                    "    \"student_id\": 14,\n" +
                    "    \"test_id\": 2,\n" +
                    "    \"general_qualification\": 0,\n" +
                    "    \"zone_id\": 1,\n" +
                    "    \"applicationDate\": \"2023-04-11 23:17:21\"\n" +
                    "}")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void deleteTestAssignation() {
        try {
            mock.perform(delete("/prueba/v1/test-assignation/delete/5")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void getAlumnResultTest() {
        try {
            mock.perform(get("/prueba/v1/test-assignation/alumn-result?alumnId=2&testId=2")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    void getAlumnResultByAlumnTest() {
        try {
            mock.perform(get("/prueba/v1/test-assignation/alumn-result/all?alumnId=2")).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
