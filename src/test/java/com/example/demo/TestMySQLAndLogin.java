package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.service.MySQLTestService;
import com.example.model.user;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TestMySQLAndLogin {
	

	
	@Autowired
	private MySQLTestService mySQLTestService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private MockHttpSession session;
	
	@Test
	void MySQLConnectTest() throws SQLException {
		List<user> userList = mySQLTestService.select();
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	void loginWeb() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/login");
		MvcResult result = mockMvc.perform(req).andReturn();
		int httpStatus = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		System.out.println("Response: HttpStatus="+httpStatus+",Content="+content);
	    assertTrue(httpStatus==HttpStatus.OK.value());
	}
	
	@Test
	void loginAccount() throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.post("/loginPost")
				.session(session)
				.param("account", "kangyuyang")
				.param("password", "123456");
		
		MvcResult result = mockMvc.perform(req).andReturn();
		int httpStatus = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		System.out.println("Response: HttpStatus="+httpStatus+",Content="+content);
	    assertTrue(httpStatus==HttpStatus.OK.value() && result.getResponse()
	    		.getContentAsString().substring(11, 15).equals("true"));
	}
	
	@Test
	void loginOut() throws Exception {
		session = new MockHttpSession();
		session.setAttribute("user", "kangyuyang");
		RequestBuilder req = MockMvcRequestBuilders.get("/logout")
				.session(session);
		MvcResult result = mockMvc.perform(req).andReturn();
		String reDirectedUrl = result.getResponse().getRedirectedUrl();
		assertTrue(reDirectedUrl.equals("/login") && session.getAttribute("user") == null);
	}
}
