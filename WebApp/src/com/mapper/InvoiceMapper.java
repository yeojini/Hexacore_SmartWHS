package com.mapper;

import java.util.ArrayList;

import com.vo.InvoiceVO;

public interface InvoiceMapper {
	public void insert(InvoiceVO vo);
	public void delete(InvoiceVO vo);
	public void update(InvoiceVO vo);
	public InvoiceVO select(InvoiceVO vo);
	public ArrayList<InvoiceVO> selectAll(InvoiceVO vo);
}
