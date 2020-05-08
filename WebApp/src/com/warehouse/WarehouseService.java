package com.warehouse;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.WarehouseVO;



@Service("whservice")
public class WarehouseService implements com.frame.Service<WarehouseVO> {

	@Resource(name="whdao")
	Dao<WarehouseVO> dao;
	
	@Transactional
	@Override
	public void insert(WarehouseVO v) throws Exception {
		dao.insert(v);
	}

	@Transactional
	@Override
	public void delete(WarehouseVO v) throws Exception {
		dao.delete(v);
	}

	@Transactional
	@Override
	public void update(WarehouseVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public WarehouseVO select(WarehouseVO v) throws Exception {
		return dao.select(v);
	}

	@Override
	public ArrayList<WarehouseVO> selectAll(WarehouseVO v) throws Exception {
		return dao.selectAll(v);
	}

}
