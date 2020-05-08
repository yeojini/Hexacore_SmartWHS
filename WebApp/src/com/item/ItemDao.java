package com.item;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.ItemMapper;
import com.vo.ItemVO;

@Repository("itdao")
public class ItemDao implements Dao<ItemVO> {

	@Autowired
	ItemMapper itmapper;
	
	@Override
	public void insert(ItemVO v) throws Exception {
		itmapper.insert(v);
	}

	@Override
	public void delete(ItemVO v) throws Exception {
		itmapper.delete(v);
	}

	@Override
	public void update(ItemVO v) throws Exception {
		itmapper.update(v);
	}

	@Override
	public ItemVO select(ItemVO v) throws Exception {
		return itmapper.select(v);
	}

	@Override
	public ArrayList<ItemVO> selectAll(ItemVO v) throws Exception {
		return itmapper.selectAll(v);
	}

}
