package com.invoice;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Dao;
import com.vo.InvoiceVO;
import com.vo.InvoicedetailVO;
import com.vo.ItemVO;

@Service("invservice")
public class InvoiceService implements com.frame.Service<InvoiceVO> {
	
	@Resource(name="itdao")
	Dao<ItemVO> itdao;
	
	@Resource(name="invdao")
	Dao<InvoiceVO> invdao;
	
	@Resource(name="invdtldao")
	Dao<InvoicedetailVO> invdtldao;
	
	@Transactional
	@Override
	public void insert(InvoiceVO v) throws Exception {
		
		/*invoice table 입출고 내역 insert*/
		invdao.insert(v);
		int invId = v.getInvoiceid();
		
		for(InvoicedetailVO iv : v.getDtllist()) {
			
			/* 재고 관리 */
			ItemVO item = itdao.select(new ItemVO(iv.getItemid()));
			int tempstock = item.getItemstock();
			if(iv.getInvoicestat().toUpperCase().trim().equals("RECEIVING")) {
				tempstock += iv.getInvoicedtlqty();
			}else if(iv.getInvoicestat().toUpperCase().trim().equals("SHIPPING")) {
				tempstock -= iv.getInvoicedtlqty();
			}
			item.setItemstock(tempstock);
			itdao.update(item);
			
			/*invoicedatail table 입출고 상세내역 insert*/
			iv.setInvoiceid(invId);
			invdtldao.insert(iv);
		}
		
		
	}

	@Transactional
	@Override
	public void delete(InvoiceVO v) throws Exception {
		invdao.delete(v);
	}
	
	@Transactional
	@Override
	public void update(InvoiceVO v) throws Exception {
		invdao.update(v);
	}

	@Override
	public InvoiceVO select(InvoiceVO v) throws Exception {
		return invdao.select(v);
	}

	@Override
	public ArrayList<InvoiceVO> selectAll(InvoiceVO v) throws Exception {
		
		return invdao.selectAll(v);
	}

}
