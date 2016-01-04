package cn.workcenter.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.service.SecurityService;
import cn.workcenter.service.UserService;
/**
 * 1.anthentication session-id已登录认证
 * 2.authorization 鉴权
 */
public class SecurityFilter implements Filter ,SecurityConstant {
	
	//no-login user(annonymous) can visit page
	private ApplicationContext applicationContext;
	private FilterConfig config;
	private SecurityService securityService;
	private UserService userService;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		onRefresh();
	}

	public void onRefresh() {
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		securityService = (SecurityService)applicationContext.getBean("securityService");
		userService = (UserService)applicationContext.getBean("userService");
		List<String> escapePageList = securityService.getAuthenEscapePage();
		auth_escapepage.addAll(escapePageList);
		static_page.add("css");
		static_page.add("dist");
		static_page.add("img");
		static_page.add("js");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		// url规范 : /BASE_PATH/{8位sid}/{modelName:workcenter/kpi}/{call-method}
		String requestURI = req.getRequestURI();
		
		boolean authenflag = userService.authAndInitSession(requestURI);
		if(authenflag);
		chain.doFilter(request, response);
		userService.destroyThreadLocal();
	}
	
	@Override
	public void destroy() {
		
	}
	
}
