package cn.workcenter.common.flow;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractFlow implements Flow {
	
	/**
	 * 查看当前详情
	 * @param processinstance_id
	 * @param node_id
	 * @return
	 */
	public abstract Object view(Long processinstance_id, Long node_id) ;
	
	public Object process(Long processinstance_id, FlowEnum typeEnum, Long node_id) {
		Object result = null;
		switch(typeEnum) {
		case enter:
			enter(processinstance_id, node_id);
			break;
		case save:
			save(processinstance_id, node_id);
			break;
		case doNext:
			doNext(processinstance_id, node_id);
			break;
		case reject:
			reject(processinstance_id, node_id);
			break;
		case view: 
			view(processinstance_id, node_id);
			break;
		}
		return result;
	}
	
}
