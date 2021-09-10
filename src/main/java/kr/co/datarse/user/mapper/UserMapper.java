package kr.co.datarse.user.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.datarse.user.model.User;
import kr.co.datarse.user.model.UserModel;

@Mapper
public interface UserMapper {

	// 유저정보 확인
	public Optional<UserModel> findByUserId(UserModel user);

	public Optional<UserModel> findByUserId(String userid);

	public void registNewUser(UserModel user);

}
