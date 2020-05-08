package com.invoicedetail;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.InvoicedetailVO;

@Service("invdtlservice")
public class InvoicedetailService implements com.frame.Service<InvoicedetailVO> {

	@Resource(name="invdtldao")
	Dao<InvoicedetailVO> invdtldao;
	
	
	@Transactional
	@Override
	public void insert(InvoicedetailVO v) throws Exception {
		invdtldao.insert(v);
	}

	@Transactional
	@Override
	public void delete(InvoicedetailVO v) throws Exception {
		invdtldao.delete(v);
	}

	@Transactional
	@Override
	public void update(InvoicedetailVO v) throws Exception {
		invdtldao.update(v);
	}

	@Override
	public InvoicedetailVO select(InvoicedetailVO v) throws Exception {
		return invdtldao.select(v);
	}

	@Override
	public ArrayList<InvoicedetailVO> selectAll(InvoicedetailVO v) throws Exception {
		return invdtldao.selectAll(v);
	}

}
