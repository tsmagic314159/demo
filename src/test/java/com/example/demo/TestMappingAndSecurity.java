package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestMappingAndSecurity {

	@Autowired
	private MockMvc mockMvc;

	private MockHttpSession session;

	@ParameterizedTest
	@ValueSource(strings = { "/JL", "/background", "/heroes", "/joker", "/YourHero", "/darkseid", "/Batman",
			"/wonderwoman" })
	void MappingWithOutLogin(String url) throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get(url);
		MvcResult result = mockMvc.perform(req).andReturn();
		int httpStatus = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		System.out.println("Response: HttpStatus=" + httpStatus + ",Content=" + content);
		assertFalse(httpStatus == HttpStatus.OK.value());
	}

	@ParameterizedTest
	@ValueSource(strings = { "/JL", "/background", "/heroes", "/joker", "/YourHero", "/darkseid", "/Batman",
			"/wonderwoman" })
	void MappingJLWithLogin(String url) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.get(url).session(session);
		MvcResult result = mockMvc.perform(req).andReturn();
		int httpStatus = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		System.out.println("Response: HttpStatus=" + httpStatus + ",Content=" + content);
		assertTrue(httpStatus == HttpStatus.OK.value());
	}

}
