package cn.workcenter.kpi.service;

import java.util.List;
import java.util.Map;

public interface KpiService {

	List<Map<String, String>> getSelfAttributes();

	List<Map<String, String>> getCultureAttributes();

}
