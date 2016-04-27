package srmf.strategy;

import java.util.ArrayList;

import srmf.global.Constant;
import srmf.global.Constant.Strategy.ExtendStrategy;
import srmf.global.Constant.Strategy.WeightStrategy;
import srmf.network.Network;
import srmf.network.SRLG;
import srmf.network.generator.NetworkGenerator;
import srmf.output.ResultOutPut;
import srmf.solution.Srmf_Solution;
/**
 * 
 * @author Admin
 *
 *五个实验：
 *1，不同拓展机制下   成本：最大权重 vs random
 *2，不同赋权重机制下  成本：使用节点度数   vs  不使用
 *3，运行时间和成本   ：网络节点数量
 *4，m-trail数量  运行时间   ：  不同联通度   ：  网络节点数量
 *5，成本  m-trail数量： 网络节点数量
 *
 */
public class Experiments {
	
	/**
	 * Example of parameter settings
	 * 
	 * 
	 * 节点数：15~75
	 * 节点平均度数：3，5，7
	 * 
	 * SRLG包含链路数4，8
	 * SRLG链路数：
	 * 
	 * y:40000
	 * avg_cost_of_link_bandwidth:30
	 * varition_cost_of_link_bandwidth:5
	 * minimum_cost:24
	 * 
	 * 
	 * N=2000
	 * C=50
	 * C1=200
	 * c2=300
	 * C3=250
	 */
	static{
		int[] Ns={15,25,35,45,55,65,75};
		for(int i=0;i<Ns.length;i++){
			
			
			Constant.GraphProperty.setN(Ns[i]);
			Constant.GraphProperty.setAvg_deg(5);//节点度数：3，5，7
			
			Constant.SrlgProperty.setAvg_link(4);
			Constant.SrlgProperty.setSrlg_num(150);
			
			Constant.Cost.setY(40000);
			Constant.Cost.setAvg_cost_of_link_bandwidth(30);
			Constant.Cost.setVarition_cost_of_link_bandwidth(5);
			Constant.Cost.setMinimum_cost(24);
			
			Constant.Strategy.setN(2000);
			Constant.Strategy.setC(50);
			Constant.Strategy.setC1(200);
			Constant.Strategy.setC2(300);
			Constant.Strategy.setC3(250);
			
			Constant.Strategy.setExtendStrategy(ExtendStrategy.random);
			Constant.Strategy.setWeightStrategy(WeightStrategy.useDeg);
			
		}
	}
	

	public static void main(String[] args) {
		Experiments1_Maximum_vs_Random();
	}

	/**
	 * 实验一，最大权重和随机选择下一跳对比
	 * 
	 * 
	 * 实验设置：
	 * 
	 * 节点数：15~75
	 * 节点平均度数：3，5，7
	 * 
	 * SRLG包含链路数4，8
	 * SRLG链路数：
	 * 
	 * y:40000
	 * avg_cost_of_link_bandwidth:30
	 * varition_cost_of_link_bandwidth:5
	 * minimum_cost:24
	 * 
	 * 
	 * N=2000
	 * C=50
	 * C1=200
	 * c2=300
	 * C3=250
	 */
	public static void Experiments1_Maximum_vs_Random() {
		
		ArrayList<Srmf_Solution> Solution_rand=new ArrayList<>();
		ArrayList<Srmf_Solution> solution_maxWt=new ArrayList<>();
		
		int[] Ns={15,25,35,45,55,65,75};
		for(int i=0;i<Ns.length;i++){
			Constant.GraphProperty.setN(Ns[i]);
			Constant.GraphProperty.setAvg_deg(5);//节点度数：3，5，7
			
			Constant.SrlgProperty.setAvg_link(4);
			Constant.SrlgProperty.setSrlg_num(150);
			
			Constant.Cost.setY(40000);
			Constant.Cost.setAvg_cost_of_link_bandwidth(30);
			Constant.Cost.setVarition_cost_of_link_bandwidth(5);
			Constant.Cost.setMinimum_cost(24);
			
			Constant.Strategy.setN(2000);
			Constant.Strategy.setC(50);
			Constant.Strategy.setC1(200);
			Constant.Strategy.setC2(300);
			Constant.Strategy.setC3(250);
			
			Constant.Strategy.setExtendStrategy(ExtendStrategy.random);
			Constant.Strategy.setWeightStrategy(WeightStrategy.useDeg);
			
			
			Network network = NetworkGenerator.generateNetwork();
			System.out.println(network);
			SRLG[] srlgs = network.getSrlgs();
			for (int k = 0; k < srlgs.length; k++)
				System.out.println(srlgs[k]);
			
			Srmf_Solution solution1=SRMF.srmf_2016_4_23_1(network);
			Solution_rand.add(solution1);
			
			Constant.Strategy.setExtendStrategy(ExtendStrategy.maxWeight);
			Srmf_Solution solution2=SRMF.srmf_2016_4_23_1(network);
			solution_maxWt.add(solution2);
		}
		ResultOutPut.outputExperiemnt1(Solution_rand, solution_maxWt);
	}

}
