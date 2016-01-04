package cn.workcenter.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.exception.ParameterEmptyException;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;

public class WorkcenterExceptionFilter implements Filter, WebConstant {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch(cn.workcenter.common.exception.SecurityException authenEx) {
			((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8"); 
			System.out.println(WorkcenterResponseBodyJson.custom().setAll(authenEx).build().toString());
			((HttpServletResponse)response).sendRedirect(BASE_PATH + LOGIN_PATH);
			return;
		} catch(ParameterEmptyException paramEx) {
			((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8"); 
			System.out.println(WorkcenterResponseBodyJson.custom().setAll(paramEx).build().toString());
			((HttpServletResponse)response).getWriter().write(WorkcenterResponseBodyJson.custom().setAll(paramEx).build().toString());
			return;
		} catch(RuntimeException runEx) {
			((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8"); 
			runEx.printStackTrace();
			((HttpServletResponse)response).getWriter().write(WorkcenterResponseBodyJson.custom().setAll(runEx).build().toString());
			return;
		} catch(Exception ex) {
			((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8"); 
			ex.printStackTrace();
			((HttpServletResponse)response).getWriter().write(WorkcenterResponseBodyJson.custom().setAll(ex).build().toString());
			return;
		} catch(Throwable tEx) {
			((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8");  
			tEx.printStackTrace();
			((HttpServletResponse)response).getWriter().write(WorkcenterResponseBodyJson.custom().setAll(tEx).build().toString());
			return;
		} 
	}
	
	@Override
	public void destroy() {
		
	}
}
