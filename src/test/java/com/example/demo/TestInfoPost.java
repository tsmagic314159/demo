package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestInfoPost {

	@Autowired
	private MockMvc mockMvc;

	private MockHttpSession session;

	//String firstName, String name, String gender, String nickName, 
	//String phoneNumber, String email, String text
	@ParameterizedTest
	@ValueSource(strings = { "", "k" })
	void testInfoPostFirst(String firstName) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/infoPost")
				.session(session)
				.param("firstName", firstName)
				.param("name", "yuyang")
				.param("gender", "male")
				.param("nickName", "sssbbb")
				.param("phoneNumber", "12345678999")
				.param("email", "tsmagic@sdf.com");
		MvcResult result = mockMvc.perform(req).andReturn();
		assertTrue(result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "", "k" })
	void testInfoPostName(String name) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/infoPost")
				.session(session)
				.param("firstName", "kang")
				.param("name", name)
				.param("gender", "male")
				.param("nickName", "sssbbb")
				.param("phoneNumber", "12345678999")
				.param("email", "tsmagic@sdf.com");
		MvcResult result = mockMvc.perform(req).andReturn();
		assertTrue(result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "", "male","female" })
	void testInfoPostGender(String gender) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/infoPost")
				.session(session)
				.param("firstName", "kang")
				.param("name", "yuyang")
				.param("gender", gender)
				.param("nickName", "sssbbb")
				.param("phoneNumber", "12345678999")
				.param("email", "tsmagic@sdf.com");
		MvcResult result = mockMvc.perform(req).andReturn();
		assertTrue(result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "", "k" })
	void testInfoPostNickName(String nickName) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/infoPost")
				.session(session)
				.param("firstName", "kang")
				.param("name", "yuyang")
				.param("gender", "male")
				.param("nickName", nickName)
				.param("phoneNumber", "12345678999")
				.param("email", "tsmagic@sdf.com");
		MvcResult result = mockMvc.perform(req).andReturn();
		assertTrue(result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "24242454242", "1e111245421", "1", "17321542464" })
	void testInfoPostPhoneNumber(String phoneNumber) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/infoPost")
				.session(session)
				.param("firstName", "kang")
				.param("name", "yuyang")
				.param("gender", "male")
				.param("nickName", "sssbbb")
				.param("phoneNumber", phoneNumber)
				.param("email", "tsmagic@sdf.com");
		MvcResult result = mockMvc.perform(req).andReturn();
		System.out.println(result.getResponse()
	    		.getContentAsString().substring(11, 15));
		assertTrue(result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "df.c", "ab@s.com" })
	void testInfoPostEmail(String email) throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/infoPost")
				.session(session)
				.param("firstName", "kang")
				.param("name", "yuyang")
				.param("gender", "gender")
				.param("nickName", "sssbbb")
				.param("phoneNumber", "12345678999")
				.param("email", email);
		MvcResult result = mockMvc.perform(req).andReturn();
		assertTrue(result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}
}
