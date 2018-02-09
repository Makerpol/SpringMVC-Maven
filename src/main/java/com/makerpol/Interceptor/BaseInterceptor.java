package com.makerpol.Interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.makerpol.entity.User;


/**
 * 拦截器
 * @author user
 *
 */
public class BaseInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(BaseInterceptor.class);
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
		if(url.indexOf("login")>0||url.indexOf("userLogin")>0){
			return true;
		}
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("LoginUser");
		
		log.debug("URL : "+ url);
		
		if(user==null) {
			PrintWriter out = resp.getWriter();
			/**
			 * 子页面的操作超时的情况下，直接重定向或者转发，跳转的页面都会显示为子页面，父页面没有跳转。
			 * 一般情况下，我们期望超时后整体返回到登录界面。重定向和转发明显不能实现我们的需求。
			 * 因此，有了下面的解决方案：返回一个HTML，并利用js代码实现登录页面打开。
			 * target=_top意思是打开时忽略所有的框架。
			 */
			out.println("<html>");
			out.println("<script>");
			out.println("window.open('"+req.getContextPath()+"/login.jsp','_top')");
			out.println("</script>");
			out.println("</html>");
			
			log.debug("retuen login page!");
			//req.getRequestDispatcher("/login.jsp").forward(req, resp);	//重定向
			//resp.sendRedirect(req.getContextPath()+"login.jsp");			//转发
			return false;
		}
		return true;
	}
}
