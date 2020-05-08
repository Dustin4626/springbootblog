package com.dustin.springbootblog.web.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dustin.springbootblog.model.Type;
import com.dustin.springbootblog.web.service.TypeService;

@Controller
@RequestMapping("/admin")
public class TypeController {
	
	public static final String REDIRECT_TYPE_INDEX = "redirect:/admin/types";  

	@Autowired
	private TypeService service;
	
	@GetMapping("/types")
	public String adminTypePage(Model model) {
		model.addAttribute("types", service.findAll());
		return "admin/type";
	}
	
	@GetMapping("/types/input")
	public String adminTypeAddPage(Model model) {
		model.addAttribute("types", new Type());
		return "admin/type-input";
	}
	
	@GetMapping("/types/{id}/input")
	public String adminTypeInputPage(@PathVariable BigDecimal id, Model model) {
		Optional<Type> opt = service.findById(id);
		if(!opt.isPresent()) {
			return REDIRECT_TYPE_INDEX;
		}
		model.addAttribute("types",opt.get());
		return "admin/type-input";
	}
	
	@PostMapping("/types")
	public String saveOrUpdateType(
			@RequestParam BigDecimal id, 
			@RequestParam String name,
			RedirectAttributes attributes) {
		service.saveOrUpdateType(id, name);
		attributes.addFlashAttribute("message", "操作成功");
		return REDIRECT_TYPE_INDEX;
	}
	
	@GetMapping("/types/{id}/delete")
	public String deleteType(@PathVariable BigDecimal id,RedirectAttributes attributes) {
		service.deleteById(id);
		attributes.addFlashAttribute("message", "操作成功");
		return REDIRECT_TYPE_INDEX;
	}
}
