package com.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.EmployeeMapper;
import com.vo.EmployeeVO;

@Repository("empdao")
public class EmployeeDao implements Dao<EmployeeVO> {

	@Autowired
	EmployeeMapper empmapper;
	
	@Override
	public void insert(EmployeeVO v) throws Exception {
		empmapper.insert(v);
	}

	@Override
	public void delete(EmployeeVO v) throws Exception {
		empmapper.delete(v);
	}

	@Override
	public void update(EmployeeVO v) throws Exception {
		empmapper.update(v);
	}

	@Override
	public EmployeeVO select(EmployeeVO v) throws Exception {
		return empmapper.select(v);
	}

	@Override
	public ArrayList<EmployeeVO> selectAll(EmployeeVO v) throws Exception {
		return empmapper.selectAll(v);
	}

}
