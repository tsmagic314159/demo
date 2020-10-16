package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.service.MySQLTestService;
import com.example.model.user;

@Controller
public class MainController {	
	
	@Autowired
    private MySQLTestService mySQLTestService;
	
	@GetMapping("/JL")
	public String JL() {
		return "../static/JLTamplate";
	}
	
	@GetMapping("/login")
	public String login() {
		return "../static/login";
	}
	
	@GetMapping("/background")
	public String background() {
		return "../static/background";
	}
	
	@GetMapping("/YourHero")
	public String YourHero(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String us) {
		return "../static/YourHero";
	}
	
	@GetMapping("/heroes")
	public String heros() {
		return "../static/heroes";
	}
	
	@GetMapping("/Batman")
	public String Batman() {
		return "../static/Batman";
	}
	
	@GetMapping("/wonderwoman")
	public String wonderwoman() {
		return "../static/wonderwoman";
	}
	
	@GetMapping("/joker")
	public String joker() {
		return "../static/joker";
	}
	
	@GetMapping("/darkseid")
	public String darksid() {
		return "../static/darkseid";
	}
	
	@PostMapping("/loginPost")
	public @ResponseBody Map<String, Object> loginPost(String account, String password, HttpSession session) throws Exception{
		List<user> user = mySQLTestService.select();
		Map<String, Object> map = new HashMap<>();
		boolean correct = false;
		for (int i = 0; i < user.size(); i++) {
			if(user.get(i).getAccount().equals(account)&&user.get(i).getPassword().equals(password)) {
				correct = true;
				break;
			}
		}
		if(correct == false) {
			map.put("success", false);
			map.put("message", "账号或密码错误");
			return map;
		}
		
		String userAccount = account;
		
		session.setAttribute(WebSecurityConfig.SESSION_KEY, userAccount);
		
		map.put("success", true);
		map.put("message", "登陆成功");
		return map;
	}
	
	@PostMapping("/infoPost")
	public @ResponseBody Map<String, Object> infoPost(String firstName, String name, String gender, String nickName, 
			String phoneNumber, String email, String text, @SessionAttribute(WebSecurityConfig.SESSION_KEY) String account){
		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String regEx2 = "^1\\d{10}$";
		Pattern p = Pattern.compile(regEx1);
		Pattern p2 = Pattern.compile(regEx2);
		Matcher m = p.matcher(email);
		Matcher m2 = p2.matcher(phoneNumber);
		Map<String, Object> map = new HashMap<>();
		System.out.println("first name: "+ firstName + " name: " + name + " gender: " + gender +
				" nick name: " + nickName + " phone number: " + phoneNumber + " email: " + email + " text: " + text);
		if(!(m2.matches())||!(m.matches())||(firstName.equals("")||name.equals("")
				||gender.equals("")||nickName.equals(""))) {
			map.put("success", false);
			map.put("message", "格式错误");
			return map;
		}
		try {
			mySQLTestService.insert(account, firstName, name, gender, nickName, phoneNumber, email, text);
		} catch (Exception e) {
			mySQLTestService.update(account, firstName, name, gender, nickName, phoneNumber, email, text);
		}
		map.put("success", true);
		map.put("message", "保存成功");
		return map;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "redirect:/login";
	}
}
