package cn.workcenter.service;

import java.util.List;

import cn.workcenter.model.User;

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

	Object doLogin(String username, String password);

	Object doLogout(String sid);

	String getUsername();

	Object getSid();

	List<User> getFlowRelatedUsers(Long processinstance_id);

	List<User> getNodeRelatedUsers(Long processinstance_id, Long node_id);

	String getUserRealnameByUserid(Long waitAssessmentPersonId);

	/**
	 * get current login userid
	 * @return
	 */
	Long getUserId();

	User getUser();

	User getUserTeamLeader(Long id);

	List<User> getAllUsers();

	Object addUser(User user);

	Object editUser(User user);

	Object getUserByUserid(Long userid);

	Object deleteUsers(String userids);

	Object forbiddenUsers(String userids);

	List<User> queryUsers(User user);

	List<User> getUsersByGroupid(Long id);

}
