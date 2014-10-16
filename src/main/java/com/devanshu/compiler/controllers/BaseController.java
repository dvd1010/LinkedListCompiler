package com.devanshu.compiler.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParameterException(Exception ex, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ModelMap map = new ModelMap();
        logger.error("MissingServletRequestParameterException Occurred ", ex);
        logger.error("Request URL: " + request.getRequestURL());

        return getModelAndView("@serverError", map);
    }
    
    private ModelAndView handleNonAjaxException(ModelMap map) {
        return getModelAndView("@serverError", map);
    }

    private ModelAndView handleAjaxException(Exception ex, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String message = ex.getMessage();
        map.put("error", message);
        return getModelAndView("@serverError", map);
    }

    protected ModelAndView getModelAndView(String viewName) {
        return getModelAndView(viewName, new ModelMap());
    }

    protected ModelAndView getModelAndView(String viewName, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView(viewName);
        mav.addAllObjects(modelMap);
        return mav;
    }

    protected ModelAndView getModelAndViewForForward(String action, ModelMap modelMap) {
        action = "forward:" + action;
        ModelAndView mav = new ModelAndView(action, modelMap);
        mav.addAllObjects(modelMap);
        return mav;
    }

    protected ModelAndView getModelAndViewRedirect(String action) {
        action = "redirect:" + action;
        ModelAndView mav = new ModelAndView(action);
        return mav;
    }
}
