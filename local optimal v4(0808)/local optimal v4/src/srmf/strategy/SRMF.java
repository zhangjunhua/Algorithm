package srmf.strategy;

import srmf.global.Constant;
import srmf.network.Network;
import srmf.solution.M_trail;
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
	 */

	public static M_trail nextTrail(Srmf_Solution solution) {
		
		
		
		
		return new M_trail();
	}

}
