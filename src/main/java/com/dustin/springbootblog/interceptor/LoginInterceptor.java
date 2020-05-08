package com.dustin.springbootblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getSession().getAttribute("user") == null) {
//			response.sendRedirect("/admin");
			request.setAttribute("message", "請先登入");
			request.getRequestDispatcher("/admin").forward(request, response);
			return false;
		}
		return true;
	}

}
