package srmf.solution;

import java.util.ArrayList;

import srmf.network.Link;
import srmf.network.Node;

public class Part {
	private ArrayList<Node> nodes = new ArrayList<>();
	private ArrayList<Link> links = new ArrayList<>();
	
	
	
	public Part() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	public ArrayList<Link> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

}
