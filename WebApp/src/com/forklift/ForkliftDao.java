package com.forklift;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.ForkliftMapper;
import com.vo.ForkliftVO;

@Repository("fldao")
public class ForkliftDao implements Dao<ForkliftVO> {

	@Autowired
	ForkliftMapper flmapper;
	
	@Override
	public void insert(ForkliftVO v) throws Exception {
		flmapper.insert(v);
	}

	@Override
	public void delete(ForkliftVO v) throws Exception {
		flmapper.delete(v);
	}

	@Override
	public void update(ForkliftVO v) throws Exception {
		flmapper.update(v);
	}

	@Override
	public ForkliftVO select(ForkliftVO v) throws Exception {
		return flmapper.select(v);
	}

	@Override
	public ArrayList<ForkliftVO> selectAll(ForkliftVO v) throws Exception {
		ArrayList<ForkliftVO> test = new ArrayList<ForkliftVO>();
		test = flmapper.selectAll(v);
		System.out.println("====" + test);
		return flmapper.selectAll(v);
	}

}
