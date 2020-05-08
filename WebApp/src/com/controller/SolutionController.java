package com.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SolutionController {
	static double efficiency  = 0;
	
	
	@RequestMapping("/solutionsearch.pc") 
	public ModelAndView solutionsearch(ModelAndView mv) throws Exception {
		
		
		mv.addObject("center", "solpage");
		mv.addObject("solChartInfo", "taskComplete");
		mv.setViewName("main");
		return mv;
	}

	
	@RequestMapping("/rchartdata.pc")
	@ResponseBody 
	public void rchartdata(HttpServletResponse res) throws Exception {	//현재 이동거리, 예상 이동거리, 효율성
		/*
		RConnection con = new RConnection();
		con.eval("source('C:/R/workspace/day04/remote.R',encoding='UTF-8')");
		//여진언니한테 확인받기
		REXP rexp = con.eval("c()");
		RList rlist = rexp.asList();
		double[] datas  = rlist.at("data").asDoubles();
		JSONArray ja = new JSONArray();
		for(int i = 0; i < 3; i++) {	// Before 
			ja.put(datas[i]);
		}
		 
		 */
		
		/////////test//////
		double[] datas = {30.0, 50.0, 68.0};
		JSONArray ja = new JSONArray();
		for(int i = 0; i < 3; i++) {	
			ja.put(datas[i]);
		}

		////////////////////
		
		res.setContentType("text/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(ja.toString());
		out.close();
		
	}
	
	
}
