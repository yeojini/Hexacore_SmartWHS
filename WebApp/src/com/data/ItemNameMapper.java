package com.data;

import java.util.HashMap;

public class ItemNameMapper {
	private static HashMap<String, String> itNameMapper;
	
	public ItemNameMapper() {
		itNameMapper = new HashMap<String, String>();	// Key : ��ǥ �̸�(ex. A10) | Value : ���� ��ǥ(ex. 2_2)
		
		/* â�� ��ǥ �̸� �� ��ǥ ��Ī*/
		// ex) A10 : 2_2, A09 : 3_2.....
		char col = 'A'-1;
		for(int i = 2; i <= 23; i++) {
			if(i%4 == 2 || i%4 == 3) {
				col++;
				int temp = 2;
				for(int j = 1; j <= 10; j++) {
					String pname = null;
					if(j == 10) pname = col+""+j;
					else pname =col+"0"+j;
					
					String itPoint = temp+"_"+i;
					temp++;
					itNameMapper.put(pname, itPoint);
					
					//System.out.println(pname + ":" +itPoint);
				}
			}
			
		}
	}
	
	/* ��ǥ �������� */
	public String getItemPoint(String pname) {
		String itPoint = itNameMapper.get(pname);
		
		return itPoint;
	}
}
