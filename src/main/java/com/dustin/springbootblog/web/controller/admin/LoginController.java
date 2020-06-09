package com.dustin.springbootblog.web.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dustin.springbootblog.model.User;
import com.dustin.springbootblog.web.service.UserService;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String loginPage() {
		return "admin/login";
	}
	
	@GetMapping("/index")
	public String adminIndexPage() {
		return "admin/dashboard";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username,
						@RequestParam String password,
						HttpSession session,
						RedirectAttributes attributes) {
		User user = userService.checkUser(username,password);
		System.out.println(user);
		if(user != null) {
			user.setPassword(null);
			session.setAttribute("user", user);
			return "redirect:/admin/index";
		} else {
			attributes.addFlashAttribute("message", "用戶名和密碼錯誤");
			return "redirect:/admin";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
//		session.removeAttribute("user");
		session.invalidate();
		return "redirect:/admin";
	}
	
}
