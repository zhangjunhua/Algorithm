package srmf.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import srmf.network.Network;
import srmf.network.SRLG;
import srmf.utils.CostComputation;

public class Srmf_Solution {
	public ArrayList<M_trail> mTrails = new ArrayList<>();
	public HashMap<String, HashSet<SRLG>> act = new HashMap<String, HashSet<SRLG>>();
	public Network network;
	public double cost;

	public static Srmf_Solution get_initial_solution(Network network) {
		Srmf_Solution solution = new Srmf_Solution(Double.MAX_VALUE);
		solution.setNetwork(network);
		solution.mTrails.clear();
		HashSet<SRLG> value = new HashSet<>();
		for (int i = 0; i < network.getSrlgs().length; i++) {
			value.add(network.getSrlgs()[i]);
		}
		solution.act.put("", value);
		return solution;
	}

	private Srmf_Solution() {
		// TODO Auto-generated constructor stub
	}

	private Srmf_Solution(double cost) {
		this.cost = cost;
	}

	/*
	 * 算法： 首先找出m_trail所经过的所有SRLG的id，放到hashSet里面 然后对act进行更新
	 */
	public void insert_Mtrail(M_trail m_trail) {
		// 取出所有的SRLG的id
		HashSet<Integer> srlg_ids = new HashSet<Integer>();
		for (int i = 0; i < m_trail.getLinks().size(); i++) {
			for (int j = 0; j < m_trail.getLinks().get(i).getSrlgs().size(); j++) {
				srlg_ids.add(m_trail.getLinks().get(i).getSrlgs().get(j)
						.getSrlgID());
			}
		}

		// update ACT
		HashMap<String, HashSet<SRLG>> newact = new HashMap<>();

		for (Entry<String, HashSet<SRLG>> entry : act.entrySet()) {
			String key = entry.getKey();
			HashSet<SRLG> values_srlg = entry.getValue();
			// 集合里面有元素时才做更新，没元素就略过
			if (values_srlg.size() > 0) {
				// partition key
				String key0 = key + "0";
				String key1 = key + "1";
				// partition value
				HashSet<SRLG> values_srlg0 = new HashSet<>();
				HashSet<SRLG> values_srlg1 = new HashSet<>();
				for (SRLG srlg : values_srlg) {
					if (srlg_ids.contains(srlg.getSrlgID())) {
						// m-trail经过srlg,它的编码应该为0
						values_srlg0.add(srlg);
					} else {
						// m-trail不经过srlg，它的编码应该为1
						values_srlg1.add(srlg);
					}
				}
				// add to hashMap
				newact.put(key0, values_srlg0);
				newact.put(key1, values_srlg1);
			}
		}
		mTrails.add(m_trail);
		act = newact;
	}

	/**
	 * @return
	 */
	public boolean is_a_complete_solution() {
		for (Entry<String, HashSet<SRLG>> entry : act.entrySet()) {
			String key = entry.getKey();
			HashSet<SRLG> value = entry.getValue();

			if (!key.contains("0"))
				return false;
			if (value.size() > 1)
				return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	public ArrayList<M_trail> getmTrails() {
		return mTrails;
	}

	/**
	 * @param mTrails
	 */
	public void setmTrails(ArrayList<M_trail> mTrails) {
		this.mTrails = mTrails;
	}

	/**
	 * @return
	 */
	public HashMap<String, HashSet<SRLG>> getAct() {
		return act;
	}

	/**
	 * @return
	 */
	public Network getNetwork() {
		return network;
	}

	/**
	 * @param network
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}

	/**
	 * @param act
	 */
	public void setAct(HashMap<String, HashSet<SRLG>> act) {
		this.act = act;
	}

	/**
	 * @return
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @param network
	 */
	public void updataCost(Network network) {
		if (!is_a_complete_solution())
			throw new RuntimeException(
					"solution is not completed, but the updataCost is called!");
		cost = CostComputation.MonitoringCost(mTrails, network);
	}

}
