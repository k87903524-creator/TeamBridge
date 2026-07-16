package com.groupware.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.groupware.dto.AttendanceDTO;

@Mapper
public interface AttendanceMapper {
    
    // 오늘 근태 기록 조회 (오늘 날짜를 String으로 받음)
    AttendanceDTO selectTodayAttendance(@Param("employeeId") int employeeId, @Param("today") String today);
    
    // 출근 기록 삽입
    void insertCheckIn(@Param("employeeId") int employeeId, 
                       @Param("today") LocalDate today, 
                       @Param("checkInTime") String checkInTime, 
                       @Param("status") String status);
    
    // 퇴근 시간 업데이트 - 수정해야됨
    void updateCheckOut(@Param("employeeId") int employeeId, 
                        @Param("today") LocalDate today, 
                        @Param("checkOutTime") String checkOutTime);
    
    // 관리자 : 출결 관리
    List<AttendanceDTO> selectAttendanceByDate(@Param("data") LocalDate date);
}