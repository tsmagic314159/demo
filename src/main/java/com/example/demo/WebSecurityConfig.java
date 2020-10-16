package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer{
	
	public final static String SESSION_KEY = "user";
	
	//注入拦截器
	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
		
		//从过滤器中剔除登录界面和相关静态资源
		addInterceptor.excludePathPatterns("/login**");
		addInterceptor.excludePathPatterns("/jl.png");
		addInterceptor.excludePathPatterns("/logojustice.png");
		
		//向过滤器中添加资源
		addInterceptor.addPathPatterns("/**");
	}
	
	//通过继承抽象类实现过滤器
	private class SecurityInterceptor extends HandlerInterceptorAdapter{
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			HttpSession session = request.getSession();
			
			//当session中有数据时不进行过滤，否则进行过滤并重新导向至登录界面
			if (session.getAttribute(SESSION_KEY) != null)
				return true;
			
			String url = "/login";
			response.sendRedirect(url);
			return false;
		}
	}
}
