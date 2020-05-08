package com.mapper;

import java.util.ArrayList;

import com.vo.EmployeeVO;

public interface EmployeeMapper {
	public void insert(EmployeeVO vo);
	public void delete(EmployeeVO vo);
	public void update(EmployeeVO vo);
	public EmployeeVO select(EmployeeVO vo);
	public ArrayList<EmployeeVO> selectAll(EmployeeVO vo);
}
