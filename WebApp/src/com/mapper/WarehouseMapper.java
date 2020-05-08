package com.mapper;

import java.util.ArrayList;

import com.vo.WarehouseVO;

public interface WarehouseMapper {
	public void insert(WarehouseVO vo);
	public void delete(WarehouseVO vo);
	public void update(WarehouseVO vo);
	public WarehouseVO select(WarehouseVO vo);
	public ArrayList<WarehouseVO> selectAll(WarehouseVO vo);
}
