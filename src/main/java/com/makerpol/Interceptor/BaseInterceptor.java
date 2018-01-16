package com.makerpol.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.makerpol.entity.User;


/**
 * 拦截器
 * @author user
 *
 */
public class BaseInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		String url = req.getRequestURI();
		System.out.println("BaseInterceptor:  "+url);
		if(url.indexOf("login")>0||url.indexOf("userLogin")>0){
			System.out.println(url);
			return true;
		}
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(user!=null) {
			return true;
		}
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);;
		return false;
	}
}
