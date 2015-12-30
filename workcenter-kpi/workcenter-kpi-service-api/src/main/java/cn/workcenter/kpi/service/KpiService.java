package cn.workcenter.kpi.service;

import java.util.List;
import java.util.Map;

import cn.workcenter.kpi.model.Main;
import cn.workcenter.model.FlowTaskinstance;

public interface KpiService {

	List<Map<String, Object>> getSelfAttributes(Long processinstance_id);

	List<Map<String, Object>> getCulturalAttributes(Long processinstance_id);

	Map<String, Object> getMain(Long processinstance_id);

	Object doFlowGet(String method, Long main_id, Long taskinstance_id);
	
	Object doFlowPost(String method, Long main_id, Long taskinstance_id);

	List<Map<String, Object>> getAssosiateKpis();

	boolean saveSelfList(List<Map<String, Object>> parameterSelfMapList, Long main_id);

	boolean saveCulturalList(List<Map<String, Object>> parameterSelfMapList, Long main_id);

	Object findTaskinstance(Long processinstance_id, Long id);

	void doCalculateLogic(Long processinstance_id);

	Main getMainByProcessinstanceid(Long processinstance_id);

	void doNextTaskinstancePrepare(FlowTaskinstance currentTaskinstance, FlowTaskinstance nextTaskinstance);

	void doNextEndTaskinstancePrepare(FlowTaskinstance currentTaskinstance);

	void doPreTaskinstancePrepare(FlowTaskinstance preTaskinstance, FlowTaskinstance currentTaskinstance);

	void doPreStartTaskinstancePrepare(FlowTaskinstance currentTaskinstance);

	void doNextMainPrepare(Long processinstance_id);

}
