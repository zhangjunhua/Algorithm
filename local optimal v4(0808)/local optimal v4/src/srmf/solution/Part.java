package srmf.solution;

import java.util.ArrayList;

import srmf.network.Link;
import srmf.network.Node;

public class Part {
	private ArrayList<Node> nodes = new ArrayList<>();
	private ArrayList<Link> links = new ArrayList<>();

	public static Part get_initial_part(Node ex_node) {
		return new Part(ex_node);
	}

	public Part() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ex_node
	 */
	public Part(Node ex_node) {
		nodes.add(ex_node);
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
