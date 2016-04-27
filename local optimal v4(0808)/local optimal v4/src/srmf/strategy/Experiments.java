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
 *���ʵ�飺
 *1����ͬ��չ������   �ɱ������Ȩ�� vs random
 *2����ͬ��Ȩ�ػ�����  �ɱ���ʹ�ýڵ����   vs  ��ʹ��
 *3������ʱ��ͳɱ�   ������ڵ�����
 *4��m-trail����  ����ʱ��   ��  ��ͬ��ͨ��   ��  ����ڵ�����
 *5���ɱ�  m-trail������ ����ڵ�����
 *
 */
public class Experiments {
	
	/**
	 * Example of parameter settings
	 * 
	 * 
	 * �ڵ�����15~75
	 * �ڵ�ƽ��������3��5��7
	 * 
	 * SRLG������·��4��8
	 * SRLG��·����
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
			Constant.GraphProperty.setAvg_deg(5);//�ڵ������3��5��7
			
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
	 * ʵ��һ�����Ȩ�غ����ѡ����һ���Ա�
	 * 
	 * 
	 * ʵ�����ã�
	 * 
	 * �ڵ�����15~75
	 * �ڵ�ƽ��������3��5��7
	 * 
	 * SRLG������·��4��8
	 * SRLG��·����
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
			Constant.GraphProperty.setAvg_deg(5);//�ڵ������3��5��7
			
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
