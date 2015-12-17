package cn.workcenter.service;

import cn.workcenter.common.WorkcenterResult;

public interface UserService {

	/**
	 * 1.  authentication
	 * 1.5 authorization
	 * 2.  init session and threadLocals 
	 * @param pathInfo
	 * @return
	 */
	boolean authAndInitSession(String pathInfo);

	/**
	 * destroy threadlocals
	 */
	void destroyThreadLocal();

	WorkcenterResult doLogin(String username, String password);

	WorkcenterResult doLogout(String sid);

}
