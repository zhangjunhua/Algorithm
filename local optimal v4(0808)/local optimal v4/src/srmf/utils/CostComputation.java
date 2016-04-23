package srmf.utils;

import java.util.ArrayList;

import srmf.global.Constant;
import srmf.network.Network;
import srmf.solution.M_trail;

public class CostComputation {
public static double MonitoringCost(ArrayList<M_trail> m_trails,Network network) {
	double bandwidthCost=0;
	for(int i=0;i<m_trails.size();i++){
		for (int j = 0; j < m_trails.get(i).getLinks().size(); j++) {
			bandwidthCost+=	m_trails.get(i).getLinks().get(j).Cost;
		}
	}
	return Constant.Cost.y*m_trails.size()+bandwidthCost;
	
}
}
