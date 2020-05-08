package com.mapper;

import java.util.ArrayList;

import com.vo.ItemVO;


public interface ItemMapper {
	public void insert(ItemVO vo);
	public void delete(ItemVO vo);
	public void update(ItemVO vo);
	public ItemVO select(ItemVO vo);
	public ArrayList<ItemVO> selectAll(ItemVO vo);
}
