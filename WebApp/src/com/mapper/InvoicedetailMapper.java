package com.mapper;

import java.util.ArrayList;
import com.vo.InvoicedetailVO;

public interface InvoicedetailMapper {
	public void insert(InvoicedetailVO vo);
	public void delete(InvoicedetailVO vo);
	public void update(InvoicedetailVO vo);
	public InvoicedetailVO select(InvoicedetailVO vo);
	public ArrayList<InvoicedetailVO> selectAll(InvoicedetailVO vo);
}
