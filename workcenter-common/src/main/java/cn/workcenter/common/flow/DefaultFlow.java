package cn.workcenter.common.flow;

import java.util.Map;

import cn.workcenter.model.FlowNode;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.FlowVariableinstance;


public class DefaultFlow extends AbstractFlow {

	
	@Override
	public Object enter(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object save(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object doNext(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object reject(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object view(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

}
