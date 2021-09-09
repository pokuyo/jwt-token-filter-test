package kr.co.datarse.user.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.datarse.user.model.User;

@Mapper
public interface UserMapper {

	// 유저정보 확인
	public Optional<User> findByUserId(User user);

	public Optional<User> findByUserId(String userid);

	public void registNewUser(User user);

}
