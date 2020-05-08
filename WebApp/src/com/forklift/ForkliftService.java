package com.forklift;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;

import com.vo.ForkliftVO;

@Service("flservice")
public class ForkliftService implements com.frame.Service<ForkliftVO> {

	@Resource(name="fldao")
	Dao<ForkliftVO> dao;
	
	@Transactional
	@Override
	public void insert(ForkliftVO v) throws Exception {
		dao.insert(v);
	}

	@Transactional
	@Override
	public void delete(ForkliftVO v) throws Exception {
		dao.delete(v);
	}

	@Transactional
	@Override
	public void update(ForkliftVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public ForkliftVO select(ForkliftVO v) throws Exception {
		return dao.select(v);
	}

	@Override
	public ArrayList<ForkliftVO> selectAll(ForkliftVO v) throws Exception {
		return dao.selectAll(v);
	}

}
