package com.devanshu.compiler.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.devanshu.compiler.service.LinkedListService;


@Controller
@RequestMapping(value = "/*")
public class LinkedListController{

	private static Logger logger = LoggerFactory.getLogger(LinkedListController.class);
	
	@Resource
	private LinkedListService linkedListService;
	
	@RequestMapping(value={"/", "/linkedlistcompiler",}, method = RequestMethod.GET)
	public ModelAndView getHomePage(HttpServletRequest request, HttpServletResponse httpServletResponse){
		
		logger.info("creating home page");
		String codeTemplate = linkedListService.getLinkedListCodeTemplate();
		String sampleLLTemplate = linkedListService.getSampleLinkedListTemplate();
		String nodeTemplate = linkedListService.getSampleNodeTemplate();
		ModelAndView mav = new ModelAndView("homePage");
		mav.addObject("codeTemplate", codeTemplate);
		mav.addObject("sampleLLTemplate", sampleLLTemplate);
		mav.addObject("nodeTemplate", nodeTemplate);
		return mav;
	}
	
	@RequestMapping(value={"/submitCode","/linkedlistcompiler/submitCode"}, method= RequestMethod.POST, consumes={"application/x-www-form-urlencoded"})
	@ResponseBody
	public Map<String, String> submitCode(HttpServletRequest request, @RequestBody String code) throws Exception{
		String userCode = java.net.URLDecoder.decode(code, "UTF-8");
		int index = userCode.lastIndexOf("\n");
		String linkedListValues = userCode.substring(index+1);
		userCode=userCode.substring(0,index);
		String result = linkedListService.runUserCode(userCode, linkedListValues);
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
}
