package com.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.EmployeeVO;
import com.vo.ForkliftVO;


@Controller
public class MainController {

	@Resource(name = "flservice")
	Service<ForkliftVO> flservice;
	
	@Resource(name = "empservice")
	Service<EmployeeVO> empservice;
	
	
	@RequestMapping("/loginpage.pc") 
	public ModelAndView loginpage(ModelAndView mv, HttpServletRequest request) {
		
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping("/login.pc") 
	public ModelAndView login(ModelAndView mv, EmployeeVO emp, HttpServletRequest request) {//���߿� VO�� �ٲٱ�
		System.out.println(emp.getEmpno());
		//String empno = request.getParameter("empno");
		//String emppwd = request.getParameter("emppwd");
		EmployeeVO empCheck = null;
		try {
			empCheck = empservice.select(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// emp1010 101010
		
		if(empCheck == null){
			mv.addObject("error", "���̵� ��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� �Է��� �ּ���.");
			mv.setViewName("login");
		}else if(emp.getEmpno().equals(empCheck.getEmpno()) && emp.getEmppw().equals(empCheck.getEmppw())) {	//  DB���� ���� �����ͼ� ��
			//empname ���� �Ѱ���
			mv.setViewName("redirect:main.pc");
			HttpSession session = request.getSession();
			session.setAttribute("empno", empCheck.getEmpno());
			session.setAttribute("empname", empCheck.getEmpname());
			session.setAttribute("empjob", empCheck.getEmpjob());
			session.setAttribute("wareid", empCheck.getWareid());
		}else {
			System.out.println("���� ������ �̵�");
		}
		return mv;
	}
	
	@RequestMapping("/main.pc")
	public ModelAndView main(ModelAndView mv, HttpServletRequest request) {	
//		if(request.getParameter("id") != null) {
//			String empno = request.getParameter("id");
//			String empname = request.getParameter("name");
//			mv.addObject("id", empno);
//			mv.addObject("name", empname);
//			System.out.println("here");
//		}
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/itpage.pc")
	public ModelAndView itpage(ModelAndView mv) {

		mv.addObject("center", "itpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/flpage.pc")
	public ModelAndView flpage(ModelAndView mv) {
		
//		try {
//			ForkliftVO fl = flservice.select(new ForkliftVO("Forklift01", null, null, null, null, 0));
//			System.out.println("**" + fl);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		
		/* Forklift list ���� */
		ArrayList<ForkliftVO> fllist = null;
		try {
			fllist = flservice.selectAll(new ForkliftVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(fllist.toString());
		mv.addObject("fllist", fllist);
		
		/* center �κп� flpage ���� */
		mv.addObject("center", "flpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("/solpage.pc")
	public ModelAndView sltpage(ModelAndView mv) {

		mv.addObject("center", "solpage");
		mv.setViewName("main");

		return mv;
	}
	
	@RequestMapping("emppage.pc")
	public ModelAndView emppage(ModelAndView mv) {
		
//		ArrayList<EmployeeVO> emplist = null;
//		try {
//			emplist = empservice.selectAll(new EmployeeVO());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("emp : " + emplist.toString());
		
		
		mv.addObject("center", "emppage");
		mv.setViewName("main");

		return mv;
	}
	
}
