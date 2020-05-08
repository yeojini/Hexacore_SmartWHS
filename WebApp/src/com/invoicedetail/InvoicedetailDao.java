package com.invoicedetail;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.InvoicedetailMapper;
import com.vo.InvoicedetailVO;

@Repository("invdtldao")
public class InvoicedetailDao implements Dao<InvoicedetailVO> {

	@Autowired
	InvoicedetailMapper invdtlmapper;
	
	@Override
	public void insert(InvoicedetailVO v) throws Exception {
		invdtlmapper.insert(v);
	}

	@Override
	public void delete(InvoicedetailVO v) throws Exception {
		invdtlmapper.delete(v);
	}

	@Override
	public void update(InvoicedetailVO v) throws Exception {
		invdtlmapper.update(v);
	}

	@Override
	public InvoicedetailVO select(InvoicedetailVO v) throws Exception {
		return invdtlmapper.select(v);
	}

	@Override
	public ArrayList<InvoicedetailVO> selectAll(InvoicedetailVO v) throws Exception {
		return invdtlmapper.selectAll(v);
	}

}
