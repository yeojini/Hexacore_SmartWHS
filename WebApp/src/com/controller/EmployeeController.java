package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.EmployeeVO;

@Controller
public class EmployeeController {
	
	@Resource(name = "empservice")
	Service<EmployeeVO> empservice;
	
	
	
	@RequestMapping("getemplist.pc")
	public @ResponseBody ArrayList<EmployeeVO> emppage(ModelAndView mv) {
		ArrayList<EmployeeVO> emplist = null;
		try {
			emplist = empservice.selectAll(new EmployeeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("emp : " + emplist.toString());
		
		return emplist;
	}
	
	@RequestMapping("getempnumbywh.pc")
	public @ResponseBody String getempnumbywh(ModelAndView mv) {
		ArrayList<EmployeeVO> emplist = null;
		try {
			emplist = empservice.selectAll(new EmployeeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HashMap<String, Integer> empnum = new HashMap<String, Integer>();
		//JSONArray chartData = new JSONArray();
		//JSONArray labels = new JSONArray();
		//JSONArray data = new JSONArray();
		for(EmployeeVO emp : emplist) {
			String wareid = emp.getWareid();
			if(empnum.containsKey(wareid)) {
				int temp = empnum.get(wareid);
				empnum.put(wareid, ++temp);
			}else {
				empnum.put(wareid, 1);
			}
		}
		
		
		JSONArray data = new JSONArray();
		JSONArray labels = new JSONArray();
		JSONObject ja = new JSONObject();
		for(Map.Entry<String, Integer> entry : empnum.entrySet()) {
			labels.put(entry.getKey());
			data.put(entry.getValue());
		}
		ja.put("data", data);
		ja.put("labels", labels);
		System.out.println(ja.toString());
		return ja.toString();
	}
	
	
	@RequestMapping("empregister.pc")
	public ModelAndView empregister(ModelAndView mv, EmployeeVO emp) {
		//기본 비밀번호 설정
		emp.setEmppw("000000");
		System.out.println(emp.toString());
		try {
			empservice.insert(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("redirect:emppage.pc");
		
		return mv;
	}
	
	@RequestMapping("empmodify.pc")
	public ModelAndView empmodify(ModelAndView mv, EmployeeVO emp) {
		
		try {
			empservice.update(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:emppage.pc");
		
		return mv;
	}
	
	@RequestMapping("empdelete.pc")
	public @ResponseBody void empdelete(ModelAndView mv, HttpServletRequest request) {
		
		
		EmployeeVO temp = new EmployeeVO();
		temp.setEmpno(request.getParameter("empno"));
		
		try {
			empservice.delete(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
}
