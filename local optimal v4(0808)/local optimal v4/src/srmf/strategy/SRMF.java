package srmf.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;

import srmf.global.Constant;
import srmf.network.Link;
import srmf.network.Network;
import srmf.network.SRLG;
import srmf.solution.M_trail;
import srmf.solution.Part;
import srmf.solution.Part_set;
import srmf.solution.Srmf_Solution;

/**
 * @author Admin
 *
 */
public class SRMF {

	/**
	 * version 1.0 ‎ 2016‎年‎4‎月‎23‎日 22:35:06
	 * 
	 * @param network
	 * @return Srmf_Solution
	 */
	public static Srmf_Solution srmf_2016_4_23_1(Network network) {
		Srmf_Solution optimal_solution = Srmf_Solution
				.get_initial_solution(network);
		for (int n = 0; n < Constant.Strategy.N; n++) {
			Srmf_Solution solution = Srmf_Solution
					.get_initial_solution(network);
			while (!solution.is_a_complete_solution()) {
				M_trail mTrail = nextTrail(solution);
				solution.insert_Mtrail(mTrail);
			}
			solution.updataCost(network);
			optimal_solution = solution.getCost() < optimal_solution.getCost() ? solution
					: optimal_solution;
		}
		return optimal_solution;
	}

	/*
	 * get a new trail
	 * 
	 * 构建m-trail的算法：
	 * 
	 * 1、构建Pj
	 * 
	 * 1.1初始化P_j
	 * 
	 * 1.2寻找ex_node_k
	 * 
	 * 找到所有由和P_j的交集为空的NUSS_C所构成的子图，然后在子图上找一个度数最大的节点
	 * 
	 * 2、连接Pj
	 */

	public static M_trail nextTrail(Srmf_Solution solution) {
		//***************初始化P_j****************
		Part_set part_set = new Part_set();

		// **************构建子图****************
		boolean[] nodes_included = new boolean[solution.getNetwork().getNodes().length];
		
		for (int i = 0; i < nodes_included.length; i++)
			nodes_included[i] = false;
		boolean[] links_included = new boolean[solution.getNetwork().getLinks().length];
		
		for (int i = 0; i < links_included.length; i++)
			links_included[i] = false;
		
		for (Entry<String, HashSet<SRLG>> entry : solution.getAct().entrySet()) {
			String key = entry.getKey();
			HashSet<SRLG> value = entry.getValue();
			if (intersect(value, part_set).size() == 0) {
				// NUSS0||NUSSc
				if (!key.contains("0") || value.size() > 1) {
					for (SRLG srlg : value) {
						for (Link link : srlg.getLinks()) {
							links_included[link.linkID] = true;
							nodes_included[link.getEndNodes()[0].nodeID] = true;
							nodes_included[link.getEndNodes()[1].nodeID] = true;
						}
					}
				}
			}
		}
		
		//******************寻找ex_node*********************
		
		
		
		
		
		return new M_trail();
	}

	/**
	 * 求 NUSS_c和P_j的交集
	 * 
	 * @param srlgs
	 * @param part_set
	 * @return
	 */
	public static ArrayList<Link> intersect(HashSet<SRLG> srlgs,
			Part_set part_set) {
		ArrayList<Link> links = new ArrayList<>();
		HashSet<Link> leftlinks = new HashSet<>();
		for (SRLG srgl : srlgs) {
			for (Link link : srgl.getLinks()) {
				leftlinks.add(link);
			}
		}
		for (Part part : part_set.getParts()) {
			for (Link link : part.getLinks()) {
				if (leftlinks.contains(link))
					links.add(link);
			}
		}
		return links;
	}

}
