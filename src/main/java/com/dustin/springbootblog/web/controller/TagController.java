package com.dustin.springbootblog.web.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dustin.springbootblog.model.Tag;
import com.dustin.springbootblog.model.Type;
import com.dustin.springbootblog.web.service.TagService;

@Controller
@RequestMapping("/admin")
public class TagController {
	
	public static final String REDIRECT_TYPE_INDEX = "redirect:/admin/tags";  

	@Autowired
	private TagService service;
	
	@GetMapping("/tags")
	@ResponseBody
	public List<Tag> adminTagPage() {
		return service.findAll();
	}
	
//	@GetMapping("/tags/search")
//	public String tagsearch(
//			@PageableDefault(size = 5, sort = { "id" }, direction = Direction.ASC) Pageable pageable,
//			Model model) {
//		System.out.println(pageable);
//		Page<Type> tags = service.listType(pageable);
//		System.out.println(tags.toList());
//		model.addAttribute("type",tags);
////		model.addAttribute("tags", service.findAll());
//		return "admin/type";
//	}
//	
//	@GetMapping("/tags/input")
//	public String adminTypeAddPage(Model model) {
//		model.addAttribute("type", new Type());
//		return "admin/type-input";
//	}
//	
//	@GetMapping("/tags/{id}/input")
//	public String adminTypeInputPage(@PathVariable BigDecimal id, Model model) {
//		Optional<Type> opt = service.findById(id);
//		if(!opt.isPresent()) {
//			return REDIRECT_TYPE_INDEX;
//		}
//		model.addAttribute("type",opt.get());
//		return "admin/type-input";
//	}
//	
////	@PostMapping("/tags")
////	public String saveOrUpdateType(
////			@RequestParam BigDecimal id, 
////			@RequestParam String name,
////			RedirectAttributes attributes) {
////		if (null == id) {
////			Optional<Type> type = service.findByName(name);
////			if (type.isPresent()) {
////				attributes.addFlashAttribute("message", "標籤已存在");
////				return REDIRECT_TYPE_INDEX;
////			}
////		}
////		service.saveOrUpdateType(id, name);
////		attributes.addFlashAttribute("message", "操作成功");
////		return REDIRECT_TYPE_INDEX;
////	}
//	
//	@PostMapping("/tags")
//	public String post(@Valid Type type,BindingResult result,
//			RedirectAttributes attributes) {
//		if (type.getId() == null) {
//			Optional<Type> type1 = service.findByName(type.getName());
//			if (type1.isPresent()) {
//				result.rejectValue("name", "nameError", "不能添加重複的標籤");
//			}
//		}
//
//		if (result.hasErrors()) {
//			return "admin/type-input";
//		}
//		Type t = service.save(type);
//		if (t == null) {
//			attributes.addFlashAttribute("message", "操作失敗");
//		} else {
//			attributes.addFlashAttribute("message", "新增成功");
//		}
//		return "redirect:/admin/tags";
//	}
//	
//	@GetMapping("/tags/{id}/delete")
//	public String deleteType(@PathVariable BigDecimal id, RedirectAttributes attributes) {
//		service.deleteById(id);
//		attributes.addFlashAttribute("message", "操作成功");
//		return REDIRECT_TYPE_INDEX;
//	}
	
}
