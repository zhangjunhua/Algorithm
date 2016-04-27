package srmf.global;

public class Constant {
	public static class GraphProperty {
		/*
		 * G=<V,E> n=|V|,m=|E| avg_deg=|E| / ( 2 * |V| )
		 */
		public static int n;
		public static int avg_deg;

		 public static int m;
		public static int getN() {
			return n;
		}

		public static void setN(int n) {
			GraphProperty.n = n;
		}

		public static int getAvg_deg() {
			return avg_deg;
		}

		public static void setAvg_deg(int avg_deg) {
			GraphProperty.avg_deg = avg_deg;
		}

		public static int getM() {
			return m;
		}

		public static void setM(int m) {
			GraphProperty.m = m;
		}
		
	}

	public static class SrlgProperty {
		/*
		 * avg_link: the average number of links each SRGL contains
		 */
		public static int avg_link;
		/*
		 * number of SRGLs
		 * 
		 * srgl_num
		 */
		public static int srlg_num;

		public static int getAvg_link() {
			return avg_link;
		}

		public static void setAvg_link(int avg_link) {
			SrlgProperty.avg_link = avg_link;
		}

		public static int getSrlg_num() {
			return srlg_num;
		}

		public static void setSrlg_num(int srlg_num) {
			SrlgProperty.srlg_num = srlg_num;
		}


	}

	public static class Cost {
		/*
		 * y:cost ratio
		 */
		public static double y;
		/*
		 * cost_of_detector
		 */
//		public static double cost_of_detector;
		
		/*
		 * cost of the link bandwidth
		 */
		
		public static double avg_cost_of_link_bandwidth;
		public static double varition_cost_of_link_bandwidth;
		public static double minimum_cost;
		public static double getMinimum_cost() {
			return minimum_cost;
		}

		public static void setMinimum_cost(double minimum_cost) {
			Cost.minimum_cost = minimum_cost;
		}

		public static double getY() {
			return y;
		}

		public static void setY(double y) {
			Cost.y = y;
		}

//		public static double getCost_of_detector() {
//			return cost_of_detector;
//		}

//		public static void setCost_of_detector(double cost_of_detector) {
//			Cost.cost_of_detector = cost_of_detector;
//		}

		public static double getAvg_cost_of_link_bandwidth() {
			return avg_cost_of_link_bandwidth;
		}

		public static void setAvg_cost_of_link_bandwidth(
				double avg_cost_of_link_bandwidth) {
			Cost.avg_cost_of_link_bandwidth = avg_cost_of_link_bandwidth;
		}

		public static double getVarition_cost_of_link_bandwidth() {
			return varition_cost_of_link_bandwidth;
		}

		public static void setVarition_cost_of_link_bandwidth(
				double varition_cost_of_link_bandwidth) {
			Cost.varition_cost_of_link_bandwidth = varition_cost_of_link_bandwidth;
		}
	}

	public static class Strategy {
		/*
		 * iteration times:N
		 */
		public static int N;
		/*
		 * C1,C2,C3
		 * 赋权重时需要的常量
		 */
		public static int C1,C2,C3;
		/**
		 * 连接各个part时，最长的路径
		 * @author Admin
		 *
		 */
		public static int C;
		
		/*
		 * Experiment 1
		 */
		public static enum ExtendStrategy {
			random, maxWeight
		}

		public static ExtendStrategy extendStrategy;

		/*
		 * Experiment 2
		 */
		public static enum WeightStrategy {
			useDeg, noDeg
		}

		public static WeightStrategy weightStrategy;

		public static ExtendStrategy getExtendStrategy() {
			return extendStrategy;
		}

		public static void setExtendStrategy(ExtendStrategy extendStrategy) {
			Strategy.extendStrategy = extendStrategy;
		}

		public static WeightStrategy getWeightStrategy() {
			return weightStrategy;
		}

		public static int getC() {
			return C;
		}

		public static void setC(int c) {
			C = c;
		}

		public static void setWeightStrategy(WeightStrategy weightStrategy) {
			Strategy.weightStrategy = weightStrategy;
		}

		public static int getN() {
			return N;
		}

		public static void setN(int n) {
			N = n;
		}

		public static int getC1() {
			return C1;
		}

		public static void setC1(int c1) {
			C1 = c1;
		}

		public static int getC2() {
			return C2;
		}

		public static void setC2(int c2) {
			C2 = c2;
		}

		public static int getC3() {
			return C3;
		}

		public static void setC3(int c3) {
			C3 = c3;
		}
	}
}