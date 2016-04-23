package srmf.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import srmf.network.SRLG;

public class Srmf_Solution {
	public ArrayList<M_trail> mTrails=new ArrayList<>();
	public HashMap<String, HashSet<SRLG>> act=new HashMap<String, HashSet<SRLG>>();
	public double cost;
	
	
	
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
	
	
	
	
}
