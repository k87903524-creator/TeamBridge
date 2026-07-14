package com.groupware.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupware.security.CustomUserDetails;
import com.groupware.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	
	private final EmployeeService employeeService;
	
	// @AuthenticationPrincipal: 로그인 시 세션에 저장해둔 CustomUserDetails를
	// 직접 안 꺼내고 파라미터로 바로 받는 문법. principal = 지금 로그인한 사용자
	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
		int employeeId = principal.getEmployeeDTO().getEmployeeId();
		model.addAttribute("employee", employeeService.getMyPageInfo(employeeId));
		return "mypage/mypage";
	}
}
