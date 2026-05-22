package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

@Controller // 클래스 선언
public class MenuController{
	
	@Autowired  // 의존성 주입
	private MenuMapper menuMapper;
	// 스프링 Container 에 이미 만들어진 MenuMapper 객체를 꺼내서 연결
	// new MenuMapper() 직접 안해도됨
	/*
	 비유하면 냉장고(Container)에서 재료(MenuMapper) 꺼내오는 것 🧊
	 직접 장보러(new) 안 가도 돼요 😄
	*/
	
	@RequestMapping("/Menus/List") // 목록조회
	public String list(Model model) {
		List<MenuDTO> menuList = menuMapper.getMenuList();
		//System.out.println(menuList);
		
		model.addAttribute("menuList",menuList);
		
		return "menus/list";
	}
	
	// DB에서 전체 목록 가져와서 JSP에 전달
	// model.addAttribute = JSP에서 ${menuList} 로 꺼내쓸수있게 담아주는것
	
	@RequestMapping("/Menus/WriteForm") // 등록 폼
	public String writeForm() {
		return "menus/write";
	}
	
	//DB 작업 없이 그냥 폼 페이지만 보여줌 
	
	
	@RequestMapping("Menus/Write") // 등록실행
	public String write(MenuDTO menuDTO, Model model) {
		
		menuMapper.insertMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	//폼에서 입력한 값이 MenuDTO에 자동으로 담겨서 옴
	// DB에 insert 실행
	// 완료 후 목록 페이지로 리다이렉트
	
	
	@RequestMapping("/Menus/Delete") // 삭제
	public String delete(MenuDTO menuDTO) {
		menuMapper.deleteMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	// URL에서 ?menu_id=MENU01 받아서 MenuDTO에 자동 담김
	// DB에서 delete 실행
	// 완료 후 목록으로 리다이렉트
	//http://localhost:8080/Menus/UpdateForm?menu_id=MENU01
	@RequestMapping("/Menus/UpdateForm") // 수정 폼
	public String updateForm(MenuDTO menuDTO, Model model) {
		MenuDTO menu = menuMapper.getMenu(menuDTO);
		model.addAttribute("menu", menu);
		model.addAttribute("조회한 menuDTO : " + menu);
		
		return "menus/update";
	}
	
	// menu_id 로 기존 데이터를 DB에서 조회
	// 조회한 데이터를 폼에 미리 채워서 보여줌
	
	@RequestMapping("/Menus/Update") // 수정실행
	public String update(MenuDTO menuDTO) {
		menuMapper.updateMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	// 수정된 값으로 DB UPDATE 실행
	// 완료 후 목록으로 리다이렉트
	
	@RequestMapping("/Menus/WriteForm2") // 등록2(메뉴이름만)
	public String writeForm2() {
		
		return "menus/write2";
		
	}
	
	@RequestMapping("/Menus/Write2")
	public String write2(MenuDTO menuDTO) {
		menuMapper.insertMenu(menuDTO);
		
		return "redirect:/Menus/List";
		
	}
	
	// 일반등록이랑 같은데 메뉴이름만 입력받는 버전
	
}


/* 
전체흐름 요약

URL 요청
    ↓
@RequestMapping 으로 메서드 찾기
    ↓
MenuMapper (DB 작업)
    ↓
결과를 Model에 담거나
redirect 로 이동
    ↓
JSP 화면에 출력



 */











