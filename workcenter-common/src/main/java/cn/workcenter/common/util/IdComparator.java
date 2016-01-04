package cn.workcenter.common.util;

import java.util.Comparator;

import cn.workcenter.model.Id;

public class IdComparator implements Comparator<Id>{

	@Override
	public int compare(Id o1, Id o2) {
		Long l1 = o1.getId();
		Long l2 = o2.getId();
		
		return l1>=l2?1:-1;
	}

}
