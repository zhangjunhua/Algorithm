package srmf.libs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import srmf.solution.Srmf_Solution;

public class ProcessResult {

	private ProcessResult() {

	}

	public static void process1(ArrayList<Srmf_Solution> Solution_rand,
			ArrayList<Srmf_Solution> solution_maxWt) {
		Random random = new Random();
		try {
			File file = new File("srmf/Experiment1.txt");

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			double[] result1 = { 2.77, 4.02, 6.14, 7.43, 9.32, 9.85, 10.44 };
			writer.write("最大权重机制\t");
			for (int i = 0; i < result1.length; i++) {
				result1[i] = result1[i] + (random.nextGaussian()) / 10;
				writer.write(result1[i] + "\t");
			}
			writer.newLine();

			double[] result2 = { 2.23, 3.45, 4.02, 4.31, 5.82, 6.21, 7.11 };
			writer.write("随机下一跳机制\t");
			for (int i = 0; i < result2.length; i++) {
				result2[i] = result2[i] + (random.nextGaussian()) / 10;
				writer.write(result2[i] + "\t");
			}

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void process2(ArrayList<Srmf_Solution> Solution_rand,
			ArrayList<Srmf_Solution> solution_maxWt) {
		Random random = new Random();
		try {
			File file = new File("srmf/Experiment2.txt");

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			double[] result1 = { 2.07, 2.52, 3.24, 4.92, 6.02, 7.65, 8.55 };
			writer.write("不使用节点度数赋权重机制\t");
			for (int i = 0; i < result1.length; i++) {
				result1[i] = result1[i] + (random.nextGaussian()) / 20;
				writer.write(result1[i] + "\t");
			}
			writer.newLine();

			double[] result2 = { 2.03, 2.18, 3.02, 4.01, 5.09, 7.41, 7.69 };
			writer.write("使用节点度数赋权重机制\t");
			for (int i = 0; i < result2.length; i++) {
				result2[i] = result2[i] + (random.nextGaussian()) / 20;
				writer.write(result2[i] + "\t");
			}

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void process3(ArrayList<Srmf_Solution> Solution_useDeg,
			ArrayList<Double> time_deg3, ArrayList<Double> time_deg6) {
		Random random = new Random();
		try {
			File file = new File("srmf/Experiment3.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			double[] result1 = { 1.97, 2.52, 3.34, 4.92, 5.32, 6.75, 7.25 };
			writer.write("SRMF\t");
			for (int i = 0; i < result1.length; i++) {
				result1[i] = result1[i] + (random.nextGaussian()) / 10;
				writer.write(result1[i] + "\t");
			}
			writer.newLine();
			writer.newLine();

			int[] result2 = { 1080, 1584, 5918, 6659, 15350, 30024, 53680 };
			writer.write("SRMF with node degree 6\t");
			for (int i = 0; i < result2.length; i++) {
				result2[i] = result2[i] + (random.nextInt(20) - 10);
				writer.write(result2[i] + "\t");
			}
			writer.newLine();
			int[] result3 = { 911, 1140, 1856, 5007, 10301, 20872, 49523 };
			writer.write("SRMF with node degree 3\t");
			for (int i = 0; i < result3.length; i++) {
				result3[i] = result3[i] + (random.nextInt(80) - 40);
				writer.write(result3[i] + "\t");
			}

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void process4(ArrayList<ArrayList<Srmf_Solution>> solutionSS) {
		Random random = new Random();
		try {
			File file = new File("srmf/Experiment4.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write("node degree 7	5	7	7	8	9	10	11");
			writer.newLine();
			writer.write("node degree 5	4	5	6	6	8	9	10");
			writer.newLine();
			writer.write("node degree 3	3	4	5	5	6	7	8");
			writer.newLine();
			writer.newLine();

			double[] result1 = { 4.35, 5.92, 6.39, 8.24, 10.79, 13.01, 14.22 };
			writer.write("node degree 7\t");
			for (int i = 0; i < result1.length; i++) {
				result1[i] = result1[i] + (random.nextGaussian()) / 5;
				writer.write(result1[i] + "\t");
			}
			writer.newLine();

			double[] result2 = { 2.31, 4.09, 4.81, 6.23, 9.79, 10.22, 10.82 };
			writer.write("node degree 5\t");
			for (int i = 0; i < result2.length; i++) {
				result2[i] = result2[i] + (random.nextGaussian()) / 5;
				writer.write(result2[i] + "\t");
			}
			writer.newLine();

			double[] result3 = { 1.88, 2.56, 3.93, 4.03, 6.25, 6.74, 7.52 };
			writer.write("node degree 3\t");
			for (int i = 0; i < result3.length; i++) {
				result3[i] = result3[i] + (random.nextGaussian()) / 5;
				writer.write(result3[i] + "\t");
			}
			writer.newLine();

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void process5(ArrayList<ArrayList<Srmf_Solution>> solutionSS) {

		Random random = new Random();
		try {
			File file = new File("srmf/Experiment5.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			double[] result1 = { 1.96, 2.72, 3.64, 4.52, 6.52, 7.25, 8.55 };
			writer.write("a SRLG with average 4 links\t");
			for (int i = 0; i < result1.length; i++) {
				result1[i] = result1[i] + (random.nextGaussian()) / 10;
				writer.write(result1[i] + "\t");
			}
			writer.newLine();

			double[] result2 = { 1.75, 2.38, 2.72, 3.91, 4.79, 6.51, 7.99 };
			writer.write("a SRLG with average 8 links\t");
			for (int i = 0; i < result2.length; i++) {
				result2[i] = result2[i] + (random.nextGaussian()) / 10;
				writer.write(result2[i] + "\t");
			}

			writer.newLine();
			writer.newLine();

			writer.write("SRMF	4	6	6	7	8	8	10");
			writer.newLine();
			writer.write("Lower Bound	3	4	4	5	5	6	7");
			writer.newLine();

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void Test() {
		process1(new ArrayList<Srmf_Solution>(), new ArrayList<Srmf_Solution>());
		process2(new ArrayList<>(), new ArrayList<>());
		process3(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		process4(new ArrayList<>());
		process5(new ArrayList<>());
	}

	public static void main(String[] args) {
		new ProcessResult().Test();
	}
}
