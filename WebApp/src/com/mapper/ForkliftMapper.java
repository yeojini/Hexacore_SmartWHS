package com.mapper;

import java.util.ArrayList;

import com.vo.ForkliftVO;

public interface ForkliftMapper {
	public void insert(ForkliftVO vo);
	public void delete(ForkliftVO vo);
	public void update(ForkliftVO vo);
	public ForkliftVO select(ForkliftVO vo);
	public ArrayList<ForkliftVO> selectAll(ForkliftVO vo);
}
