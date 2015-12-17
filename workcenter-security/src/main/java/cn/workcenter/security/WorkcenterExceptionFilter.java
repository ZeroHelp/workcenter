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
		} catch(SecurityException authenEx) {
			((HttpServletResponse)response).sendRedirect(BASE_PATH+"/workcenter/index");
			return;
		} catch(ParameterEmptyException paramEx) {
			((HttpServletResponse)response).getWriter().write(WorkcenterResponseBodyJson.custom().setAll(paramEx).build().toString());
			return;
		}
	}
	
	@Override
	public void destroy() {
		
	}
}
