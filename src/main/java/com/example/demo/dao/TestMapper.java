package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.user;

@Mapper
public interface TestMapper {

	@Select("select * from users")
	List<user> select();

	@Insert("insert into informations values(#{account}, #{firstName}"
			+ ", #{name}, #{gender}, #{nickName}, #{phoneNumber}" + ", #{email}, #{text})")
	int insert(@Param("account") String account, @Param("firstName") String firstName, @Param("name") String name,
			@Param("gender") String gender, @Param("nickName") String nickName,
			@Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("text") String text);

	@Update("update informations set firstName = #{firstName}"
			+ ", name = #{name}, gender = #{gender}, nickName = #{nickName},phoneNumber = #{phoneNumber}"
			+ ", email = #{email}, text = #{text} where account = #{account}")
	int update(@Param("account") String account, @Param("firstName") String firstName, @Param("name") String name,
			@Param("gender") String gender, @Param("nickName") String nickName,
			@Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("text") String text);
}
