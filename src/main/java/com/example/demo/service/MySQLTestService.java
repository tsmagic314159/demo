package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TestMapper;
import com.example.model.user;

@Service
public class MySQLTestService {
	
	@Autowired
	private TestMapper testMapper;
	
	public List<user> select(){
		return testMapper.select();
	}
	
	public int insert(String account, String firstName, String name, 
			String gender, String nickName, String phoneNumber, String email, String text) {
		return testMapper.insert(account, firstName, name, gender, nickName, phoneNumber, email, text);
	}
	
	public int update(String account, String firstName, String name, 
			String gender, String nickName, String phoneNumber, String email, String text) {
		return testMapper.update(account, firstName, name, gender, nickName, phoneNumber, email, text);
	}
}
