package com.warehouse;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.WarehouseMapper;
import com.vo.WarehouseVO;

@Repository("whdao")
public class WarehouseDao implements Dao<WarehouseVO> {

	@Autowired
	WarehouseMapper whmapper;
	
	@Override
	public void insert(WarehouseVO v) throws Exception {
		whmapper.insert(v);
	}

	@Override
	public void delete(WarehouseVO v) throws Exception {
		whmapper.delete(v);
	}

	@Override
	public void update(WarehouseVO v) throws Exception {
		whmapper.update(v);
	}

	@Override
	public WarehouseVO select(WarehouseVO v) throws Exception {
		return whmapper.select(v);
	}

	@Override
	public ArrayList<WarehouseVO> selectAll(WarehouseVO v) throws Exception {
		return whmapper.selectAll(v);
	}

}
