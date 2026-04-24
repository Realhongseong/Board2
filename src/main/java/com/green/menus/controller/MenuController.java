package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

@Controller
public class MenuController {
	
	@Autowired
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:9090/Menus/List
	@RequestMapping("/Menus/List")   
	public   String   list( Model model  ) {
		// 조회한결과를 ArrayList 로 돌려준다
		List<MenuDTO> menuList = menuMapper.getMenuList(); // ArrayList 결과를받는다
		System.out.println(menuList);
		
		model.addAttribute("msg", "하하");
		model.addAttribute("menuList", menuList);
		
		return "menus/list";   // /WEB-INF/views/menus/list.jsp
	}
	
	// /Menus/WriteForm
	@RequestMapping("/Menus/WriteForm")
	public String writeForm() {
		return "/Menus/WriteForm"; // write.jsp로 이동
	}
	
	// /Menus/WriteForm
	//public String write(String menu_id, String menu_name, int menu_seq) {		
	@RequestMapping("/Menus/Write")
	public String write (MenuDTO menuDTO, Model model) { 
		// 넘어온 값
		System.out.println( "menu_id=" + menuDTO.getMenu_id());
		System.out.println( "menu_name=" + menuDTO.getMenu_name());
		System.out.println( "menu_seq=" + menuDTO.getMenu_seq());
		
		
		// db에 저장
		menuMapper.insertMenu(menuDTO);
		
		// 다시조회
		List<MenuDTO> menuList = menuMapper.getMenuList();		
		model.addAttribute("menuList",menuList);
		
		return "menus/list";
	}
	
}













