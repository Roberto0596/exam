package com.robert.exam;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
class ExamApplicationTests {

	@Autowired
	MockMvc mock;

	@Test
	@Order(0)
	void getAlumnsTest() {
		try {
			mock.perform(get("/prueba/v1/student/get")).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(1)
	void getAlumnsByIdTest() {
		try {
			mock.perform(get("/prueba/v1/student/get/1")).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void saveAlumnTest() {
		try {
			mock.perform(post("/prueba/v1/student/save").contentType(MediaType.APPLICATION_JSON).content("{\n" +
					"    \"name\": \"roberto manuel\",\n" +
					"    \"lastname\": \"cordero\",\n" +
					"    \"age\": \"25\",\n" +
					"    \"email\": \"sonico242503@gmail.com\",\n" +
					"    \"timeZoneId\": 1\n" +
					"}")).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	void deleteAlumnTest() {
		try {
			mock.perform(delete("/prueba/v1/student/delete/15")).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
