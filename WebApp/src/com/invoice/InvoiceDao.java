package com.invoice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.InvoiceMapper;
import com.vo.InvoiceVO;

@Repository("invdao")
public class InvoiceDao implements Dao<InvoiceVO> {
	
	@Autowired
	InvoiceMapper invmapper;
	
	@Override
	public void insert(InvoiceVO v) throws Exception {
		invmapper.insert(v);
	}

	@Override
	public void delete(InvoiceVO v) throws Exception {
		invmapper.delete(v);
	}

	@Override
	public void update(InvoiceVO v) throws Exception {
		invmapper.update(v);
	}

	@Override
	public InvoiceVO select(InvoiceVO v) throws Exception {
		return invmapper.select(v);
	}

	@Override
	public ArrayList<InvoiceVO> selectAll(InvoiceVO v) throws Exception {
		return invmapper.selectAll(v);
	}

}
