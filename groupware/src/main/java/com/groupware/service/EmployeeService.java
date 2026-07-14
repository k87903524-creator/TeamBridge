package com.groupware.service;

import org.springframework.stereotype.Service;

import com.groupware.dto.EmployeeDTO;
import com.groupware.mapper.EmployeeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeMapper employeeMapper;
	
	// roleText는 저장 안 하고 매번 계산 (POSITION 바뀌면 어긋날 수 있어서)
	public EmployeeDTO getMyPageInfo(int employeeId) {
		EmployeeDTO employeeDTO = employeeMapper.findMyPageInfo(employeeId);
		
		// ADMIN 먼저 체크 - positionRank가 null→0이라 안 그러면 "일반 임직원"으로 잘못 나옴
        if ("ADMIN".equals(employeeDTO.getEmployeeRole())) {
            employeeDTO.setRoleText("관리자");
        } else if (employeeDTO.getPositionRank() == 1) {
            employeeDTO.setRoleText("부서장");
        } else if (employeeDTO.getPositionRank() == 2) {
            employeeDTO.setRoleText("팀장");
        } else {
            employeeDTO.setRoleText("일반 임직원");
        }

        return employeeDTO;
    }
}