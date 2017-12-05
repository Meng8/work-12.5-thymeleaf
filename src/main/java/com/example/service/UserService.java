package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.entity.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository urt;
	List<User> liu;
	//查找全部
	public List<User> fingAllUser(){
		return urt.findAll();
	}
	//查找单个
	public User fingOne(int id) {
		return urt.findOne(id);
		
	}
	
	//新增
	public void addUser(User user) {
		urt.saveAndFlush(user);
	}
	//删除
	public void deleteUser(int id) {
		urt.delete(id);
	}

}
