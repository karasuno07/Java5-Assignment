package com.karasuno.spring.controller.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	    @RequestMapping(value = "/error")
	    public String handleError(HttpServletRequest request) {
	    	
	    	Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    	
	    	if (statusCode != null) {
	    		if (statusCode == HttpStatus.NOT_FOUND.value()) {
	    			return "error/404";
	    		} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	    			return "error/500";
	    		}
	    	}
	    	
	        return "Error handling";
	    }

		@GetMapping(value = "/access-denied")
		public String handleForbiddenError() {
			return "error/403";
		}
	    
}
