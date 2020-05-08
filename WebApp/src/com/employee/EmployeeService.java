package com.employee;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.EmployeeVO;

@Service("empservice")
public class EmployeeService implements com.frame.Service<EmployeeVO> {

	@Resource(name="empdao")
	Dao<EmployeeVO> dao;
	
	@Transactional
	@Override
	public void insert(EmployeeVO v) throws Exception {
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(com.controller.ItemController.wareNameList[index]);
		dao.insert(v);
	}

	@Transactional
	@Override
	public void delete(EmployeeVO v) throws Exception {
		dao.delete(v);
	}

	@Transactional
	@Override
	public void update(EmployeeVO v) throws Exception {
		//warename 정리
		int index = Integer.parseInt(v.getWarename().substring(4,6));
		v.setWarename(com.controller.ItemController.wareNameList[index]);
		
		//password 세팅
		EmployeeVO temp = dao.select(v);
		v.setEmppw(temp.getEmppw());
		dao.update(v);
	}

	@Override
	public EmployeeVO select(EmployeeVO v) throws Exception {
		return dao.select(v);
	}

	@Override
	public ArrayList<EmployeeVO> selectAll(EmployeeVO v) throws Exception {
		return dao.selectAll(v);
	}

}
