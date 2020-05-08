package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.Service;
import com.vo.ForkliftVO;


@Controller
public class ForkliftController {
	
	@Resource(name = "flservice")
	Service<ForkliftVO> flservice;
	
	static HashMap<String, Object> flJsonList = new HashMap<>();
	static JSONObject jo = new JSONObject();
	static HashMap<String, Integer> fldist = new HashMap<>();
	
	
	@ResponseBody
	@RequestMapping("/distance.pc")
	public void distancefl(HttpServletResponse res) throws IOException {
		setDrivenDist();
		//�ؿ��� ���ð� JSON ���¾ȿ� JSON�� �Ǵ�Ƽ� id�� distance�� ���� �ϳ��� �� �� �ְԲ� ����
		JSONObject distInput = new JSONObject();
		distInput.put("forklift1", "[7425, 4344, 6231, 5890, 7235, 6342, 8324]");
		distInput.put("forklift2", "[8315, 7647, 3245, 7533, 6842, 4420, 6923]");
		distInput.put("forklift3", "[7362, 6725, 7389, 8235, 5217, 7237, 4924]");
		distInput.put("forklift4", "[6231, 7223, 6752, 4561, 7302, 7356, 9823]");

		
		PrintWriter out = res.getWriter();
		System.out.println("distInput is " +distInput.toJSONString());
	    out.print(distInput.toJSONString());
	    out.close();
	    
	}
	
	
	@ResponseBody //input �� Requestbody �����ش�
	@RequestMapping("/sendfl.pc")
	public void sendfl(HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
	    out.print(jo.toJSONString());
	    out.close();
	}
	
	@RequestMapping("/getDist.pc")
	public @ResponseBody HashMap<String, Integer> getDist() throws IOException {

		return fldist;
	}
	
	

//	@ResponseBody //input �� Requestbody �����ش�
	@RequestMapping("/receivefl.pc")
	public void receivefl(HttpServletResponse res, @RequestBody String input) throws IOException {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(input);
			System.out.println("ojb : "+obj.toJSONString());
			
			String id = (String)obj.get("forkliftid");
			//long dist = (long) obj.get("distanceDriven");
			int dist = Integer.parseInt((String) obj.get("distanceDriven"));;
			jo.put(id, input);
			ForkliftVO newfl = new ForkliftVO();
			newfl.setForkid(id);
			newfl.setForkdist(dist);
			try {
				flservice.update(newfl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//System.out.println("dist : " + dist);
			if(fldist.containsKey(id)) {
				int newdist = fldist.get(id)+dist;
				System.out.println(fldist.get(id) + " + " + dist + " = " + newdist);
				fldist.put(id, newdist);

			}else {
				fldist.put(id, dist);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return;

	}
	
	public void setDrivenDist() throws IOException {
		ArrayList<ForkliftVO> fllist = null;
		
		try {
			fllist = flservice.selectAll(new ForkliftVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ForkliftVO fl : fllist) {
			fldist.put(fl.getForkid(), (int) fl.getForkdist());
		}
		
	}
	
}
