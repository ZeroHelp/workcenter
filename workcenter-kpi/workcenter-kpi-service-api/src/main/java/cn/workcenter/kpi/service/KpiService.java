package cn.workcenter.kpi.service;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.FlowTaskinstance;

public interface KpiService {

	List<Map<String, Object>> getSelfAttributes(Long processinstance_id);

	List<Map<String, Object>> getCulturalAttributes(Long processinstance_id);

	Map<String, Object> getMain(Long processinstance_id);

	Object doFlowGet(String method, Long main_id, Long taskinstance_id);
	
	Object doFlowPost(String method, Long main_id, Long taskinstance_id);

	List<Map<String, Object>> getAssosiateKpis();

	boolean saveSelfList(List<Map<String, Object>> parameterSelfMapList);

	boolean saveCulturalList(List<Map<String, Object>> parameterSelfMapList);

	Object findTaskinstance(Long processinstance_id, Long id);

	void updateTaskinstance(FlowTaskinstance taskinstance);

}
