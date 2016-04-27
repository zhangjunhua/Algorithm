package srmf.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Map.Entry;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.javafx.geom.transform.CanTransformVec3d;

import srmf.global.Constant;
import srmf.network.Link;
import srmf.network.Network;
import srmf.network.Node;
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
	 * version 1.0 鈥� 2016鈥庡勾鈥�4鈥庢湀鈥�23鈥庢棩 22:35:06
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
	 * 鏋勫缓m-trail鐨勭畻娉曪細
	 * 
	 * 1銆佹瀯寤篜j
	 * 
	 * 1.1鍒濆鍖朠_j
	 * 
	 * 1.2瀵绘壘ex_node_k
	 * 
	 * 鎵惧埌鎵�鏈夌敱鍜孭_j鐨勪氦闆嗕负绌虹殑NUSS_C鎵�鏋勬垚鐨勫瓙鍥撅紝鐒跺悗鍦ㄥ瓙鍥句笂鎵句竴涓害鏁版渶澶х殑鑺傜偣
	 * 
	 * 2銆佽繛鎺j
	 */

	public static M_trail nextTrail(Srmf_Solution solution) {
		// ***************鍒濆鍖朠_j****************
		Part_set part_set = new Part_set();

		// **************鏋勫缓瀛愬浘****************
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

		// ******************瀵绘壘ex_node*********************
		// 璁＄畻degree
		int[] nodes_degrees = new int[solution.getNetwork().getNodes().length];
		for (int i = 0; i < nodes_degrees.length; i++)
			nodes_degrees[i] = 0;
		for (int i = 0; i < nodes_degrees.length; i++) {
			if (nodes_included[i]) {
				for (Link link : solution.getNetwork().getNodes()[i]
						.getAjacent_links()) {
					if (links_included[link.linkID])
						nodes_degrees[i]++;
				}
			}
		}
		// 找到ex_node
		Node ex_node = solution.getNetwork().getNodes()[0];
		for (int i = 0; i < nodes_degrees.length; i++) {
			if (nodes_included[i]) {
				if (nodes_degrees[ex_node.getNodeID()] < nodes_degrees[i])
					ex_node = solution.getNetwork().getNodes()[i];
			}
		}
		// 把ex_node放入Part
		Part part = Part.get_initial_part(ex_node);
		part_set.addPart(part);
		double[] weight = new double[ex_node.getAjacent_links().size()];

		ArrayList<Link> links = new ArrayList<>();

		for (Entry<String, HashSet<SRLG>> entry : solution.getAct().entrySet()) {
			String key = entry.getKey();
			HashSet<SRLG> value = entry.getValue();
			if (value.size() > 1) {
				ArrayList<Link> temp_links = new ArrayList<>();
				for (SRLG srlg : value) {
					for (Link link : srlg.getLinks()) {
						if (!temp_links.contains(link))
							temp_links.add(link);
					}
				}
				Random random = new Random();
				Link link;
				while (true) {
					link = temp_links.get(random.nextInt(temp_links.size()));
					int k = 0;
					for (SRLG srlg : value) {
						if (link.getSrlgs().contains(srlg))
							k++;
					}
					if (k > 0 && k != value.size())
						break;
				}
				links.add(link);
			} else if (value.size() == 1 && !key.contains("0")) {
				for (SRLG srlg : value) {
					links.add(srlg.getLinks().get(0));
				}
			}
		}
		M_trail mTrail = new M_trail();
		mTrail.setLinks(links);
		return mTrail;
	}

	
	/**
	 * @param part_set
	 * @param solution
	 * @return
	 */
	public static M_trail connect_parts(Part_set part_set,Srmf_Solution solution){
		boolean[] edgeFlag =new boolean[solution.getNetwork().getLinks().length];//索引向量
		double[][] adjacencyMatrix=new double[solution.getNetwork().getNodes().length][solution.getNetwork().getNodes().length];//邻接表
		M_trail m_trail=new M_trail();
		//初始化,建立边索引向量
		for(int i=0;i<solution.getNetwork().getLinks().length;i++)
		{
			edgeFlag[solution.getNetwork().getLinks()[i].getLinkID()]=true;
		}
		for(int j=0;j<part_set.getParts().size();j++)
		{
			for(int k=0;k<part_set.getParts().get(j).getLinks().size();k++)
			{
				edgeFlag[part_set.getParts().get(j).getLinks().get(k).getLinkID()]=false;
			}
		}
		//建立邻接表
		for(int l=0;l<solution.getNetwork().getNodes().length;l++)
		{
			for(int m=0;m<solution.getNetwork().getNodes().length;m++)
			{
				if(solution.getNetwork().ExistAnEdge(l, m))
				{
				adjacencyMatrix[l][m]=solution.getNetwork().getLink(l, m).getCost();
				adjacencyMatrix[m][l]=solution.getNetwork().getLink(l, m).getCost();
				}
				else {
					adjacencyMatrix[l][m]=Integer.MAX_VALUE;
					adjacencyMatrix[m][l]=Integer.MAX_VALUE;
				}
				if(edgeFlag[solution.getNetwork().getLink(l, m).getLinkID()]==false)
					adjacencyMatrix[l][m]=Integer.MAX_VALUE;
					adjacencyMatrix[m][l]=Integer.MAX_VALUE;
			}
		}
		//Floyd算法
		      for(int k=0;k<solution.getNetwork().getNodes().length;k++)
		         for(int i=0;i<solution.getNetwork().getNodes().length;i++)
		              for(int j=0;j<solution.getNetwork().getNodes().length;j++)
		                 if(adjacencyMatrix[i][k]+adjacencyMatrix[k][j]<adjacencyMatrix[i][j])
		                	 adjacencyMatrix[i][j]=adjacencyMatrix[i][k]+adjacencyMatrix[k][j];
		 //连接pkj
		   for(int n=0;n<solution.getNetwork().getNodes().length;n++)
			   for(int p=0;p<solution.getNetwork().getNodes().length;p++)
			   {
				   if(adjacencyMatrix[n][p]!=Integer.MAX_VALUE)
					 if(IsdifferentPairConnect(n,p,part_set))
					 { 	
						m_trail.setLinks(FindPairConnect(n,p,adjacencyMatrix,solution));					
					 	part_set.getParts().get(GetDifferentTeams(n,p,part_set)[0]).getLinks().addAll(part_set.getParts().get(GetDifferentTeams(n,p,part_set)[1]).getLinks());
					 	m_trail.setLinks(part_set.getParts().get(GetDifferentTeams(n, p, part_set)[0]).getLinks());
					 	part_set.getParts().remove(GetDifferentTeams(n, p, part_set)[1]);
					 	
					 }
			   }
		return m_trail;
	}

	private static int[] GetDifferentTeams(int n, int p, Part_set part_set) {
		// TODO Auto-generated method stub
		int[] teams=new int[2];
		int team1=Integer.MAX_VALUE,team2=Integer.MAX_VALUE;
		for(int i=0;n<part_set.getParts().size();i++)
			for(int j=0;j<part_set.getParts().get(i).getLinks().size();j++)
				if(part_set.getParts().get(i).getLinks().get(j).getLinkID()==n)
					team1=i;
		for(int i=0;n<part_set.getParts().size();i++)
			for(int j=0;j<part_set.getParts().get(i).getLinks().size();j++)
				if(part_set.getParts().get(i).getLinks().get(j).getLinkID()==p)
					team2=i;
		teams[0]=team1;
		teams[1]=team2;	
		return teams;
	}

	private static ArrayList<Link> FindPairConnect(int n, int p,double[][] adjacencyMatrix,Srmf_Solution solution) {
		// TODO Auto-generated method stub
		//反向回溯的最短路径发现算法
		ArrayList<Link> links=new ArrayList<Link>();
		int iteNode=p,i;
		do {
			
			for(i=0;i<solution.getNetwork().getNodes()[iteNode].getAjacent_links().size();i++)
			{
				if(solution.getNetwork().getNodes()[iteNode].getAjacent_links().get(i).getCost()+adjacencyMatrix[solution.getNetwork().getNodes()[iteNode].
				       getAjacent_links().get(i).getNeighbourNode(solution.getNetwork().getNodes()[iteNode]).getNodeID()][iteNode]==adjacencyMatrix[iteNode][n]);
				{
					links.add(solution.getNetwork().getNodes()[iteNode].getAjacent_links().get(i));
					iteNode=solution.getNetwork().getNodes()[iteNode].
						       getAjacent_links().get(i).getNeighbourNode(solution.getNetwork().getNodes()[iteNode]).getNodeID();
					break;
				}
			}}
			//所有的pkj都被连接
			while(solution.getNetwork().getNodes()[iteNode].
				       getAjacent_links().get(i).getNeighbourNode(solution.getNetwork().getNodes()[iteNode]).getNodeID()!=n);
		return links;
	}

	private static boolean IsdifferentPairConnect(int n, int p,Part_set part_set) {
		// TODO Auto-generated method stub
		int team1=Integer.MAX_VALUE,team2=Integer.MAX_VALUE;
		for(int i=0;n<part_set.getParts().size();i++)
			for(int j=0;j<part_set.getParts().get(i).getLinks().size();j++)
				if(part_set.getParts().get(i).getLinks().get(j).getLinkID()==n)
					team1=i;
		for(int i=0;n<part_set.getParts().size();i++)
			for(int j=0;j<part_set.getParts().get(i).getLinks().size();j++)
				if(part_set.getParts().get(i).getLinks().get(j).getLinkID()==p)
					team2=i;
		if(team1!=team2&&team1!=Integer.MAX_VALUE&&team2!=Integer.MAX_VALUE)
			return true;
		else {
			return false;	
			 }
	}

	/**
	 * 姹� NUSS_c鍜孭_j鐨勪氦闆�
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
