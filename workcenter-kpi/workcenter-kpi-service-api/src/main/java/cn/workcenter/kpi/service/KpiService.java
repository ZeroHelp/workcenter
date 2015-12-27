package cn.workcenter.kpi.service;

import java.util.List;
import java.util.Map;

public interface KpiService {

	List<Map<String, Object>> getSelfAttributes(Long processinstance_id);

	List<Map<String, Object>> getCulturalAttributes(Long processinstance_id);

	Map<String, Object> getMain(Long processinstance_id);

	void doFlowGet(String method, Long main_id, Long taskinstance_id);
	
	Object doFlowPost(String method, Long main_id, Long taskinstance_id);

	List<Map<String, Object>> getAssosiateKpis();

}
