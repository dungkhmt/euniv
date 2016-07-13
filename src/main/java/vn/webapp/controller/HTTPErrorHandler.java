package vn.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller("cpError")
public class HTTPErrorHandler {

	String path = "/error";

	@Autowired 
    private HttpServletRequest request;
	
	@RequestMapping(value = "/400")
	public String error400() {
		System.out.println("custom error handler");
		return path + "/400";
	}

	@RequestMapping(value = "/404")
	public String error404(ModelMap map, HttpSession session) {
		System.out.println("custom error handler");
		String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        map.put("errorBaseUrl", baseUrl);
		return "cp.400";
	}

	@RequestMapping(value = "/500")
	public String error500(ModelMap map, HttpSession session) {
		System.out.println("custom error handler");
		String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        map.put("errorBaseUrl", baseUrl);
		return "cp.500";
	}
}