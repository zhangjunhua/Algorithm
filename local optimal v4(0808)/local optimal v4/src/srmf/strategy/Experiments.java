package srmf.strategy;

import srmf.global.Constant;
import srmf.network.Network;
import srmf.network.SRLG;
import srmf.network.generator.NetworkGenerator;

public class Experiments {

	public static void main(String[] args) {
		Experiments1_Maximum_vs_Random();
	}

	/**
	 * 实验一，最大权重和随机选择下一跳对比
	 */
	public static void Experiments1_Maximum_vs_Random() {

		
		
		
		
		Constant.GraphProperty.n = 5;
		Constant.GraphProperty.avg_deg = 2;
		Constant.SrlgProperty.avg_link = 2;
		Constant.SrlgProperty.srlg_num = 3;
		Network network = NetworkGenerator.generateNetwork();
		System.out.println(network);
		SRLG[] srlgs = network.getSrlgs();
		for (int i = 0; i < srlgs.length; i++)
			System.out.println(srlgs[i]);

	}

}
