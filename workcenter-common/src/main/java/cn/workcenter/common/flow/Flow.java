package cn.workcenter.common.flow;

import javax.servlet.http.HttpServletRequest;

public interface Flow {
	
	/**
	 * 进入节点
	 * @param processinstance_id
	 * @param node_id
	 * @return
	 */
	Object enter(Long processinstance_id, Long node_id) ;
	
	/**
	 * 保存草稿
	 * @param processinstance_id
	 * @param node_id
	 * @return
	 */
	Object save(Long processinstance_id, Long node_id) ;
	
	/**
	 * 进入下一节点
	 * @param processinstance_id
	 * @param node_id
	 * @return
	 */
	Object doNext(Long processinstance_id, Long node_id) ;
	
	/**
	 * 返回上一节点(驳回)
	 * @param processinstance_id
	 * @param node_id
	 * @return
	 */
	Object reject(Long processinstance_id, Long node_id) ;
	
}
