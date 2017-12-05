package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.User;
import com.example.service.UserService;

@Controller
@RequestMapping("thy")
public class Controller_Test {
	
	@Autowired
	UserService usv;
	List<User> userList;
	Map<Integer, User> mapuser=new HashMap<Integer, User>();
	
	@RequestMapping(value="session")
	public String session(HttpServletRequest req,HttpSession ses) {
		ses.setAttribute("session", "老王");
		return "index";
		
	}
	@RequestMapping("indexweb")
	public String index() {
		return "index";
	}
	@RequestMapping("adduser")
	public String adduser() {
		return "addUser";
	}
	
	@RequestMapping(value="testeach")
	//@ResponseBody
	public String produceUse(HttpSession ses,HttpServletResponse resp,HttpServletRequest req) throws IOException {
		System.out.println("----我是一道美丽的分割线----");
		List<User> users= new ArrayList<User>();
		for(int i=1;i<=10;i++) {
		User user = new User();
		user.setName("Meng"+i);
		user.setAge(18);
		user.setId(i);
		if(i%2==0) {
			user.setSize("男");
		}else {
			user.setSize("女");
		}
		users.add(user);
		}
		//ses.setAttribute("list", users);
		//resp.getWriter().print(users);
		req.setAttribute("userlist", users);
		return "index";
	}
	
	//新增
	@RequestMapping(value="maplist")
	//@ResponseBody
	public String testmap(User user,HttpServletResponse resp,HttpServletRequest req,Model model) throws IOException {
		
		//Map<Integer, User> map=new HashMap<Integer, User>();
		mapuser.put(user.getId(), user);
		User user1 = new User(1, "liu", "男", 22);
		User user2 = new User(2, "wang", "女", 18);
		User user3 = new User(3, "zhang", "太监", 88);
		mapuser.put(1, user1);
		mapuser.put(2, user2);
		mapuser.put(3, user3);
		//resp.getWriter().print(mapuser);
		//req.setAttribute("usermap", mapuser);
		model.addAttribute("usermap", mapuser);
		return "table";
	}
	
	
	//编辑
	@RequestMapping(value="update")
	public String update(int id,Model model) {
		//Map<Integer, User> map=new HashMap<Integer, User>();
		User user = mapuser.get(id);
		model.addAttribute("user", user);
		model.addAttribute("id", id);
		return "maplist";
	}
	
	//增加和编辑
	@RequestMapping(value="change")
	public String changeUser(User user) {
		if(usv.fingOne(user.getId())!=null) {
			userList.remove(user.getId());
			usv.deleteUser(user.getId());
		}
		userList.add(user);
		usv.addUser(user);
		return "table";
		
	}

}
