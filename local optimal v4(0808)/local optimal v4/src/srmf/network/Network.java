package srmf.network;

public class Network {
	public Node[] nodes;
	public Link[] links;
	public SRLG[] srlgs;

	public Network() {
		// TODO Auto-generated constructor stub
	}

	public Network(Node[] nodes, Link[] links) {
		this.nodes = nodes;
		this.links = links;
	}

	public Network(Node[] nodes, Link[] links, SRLG[] srlgs) {
		this.nodes = nodes;
		this.links = links;
		this.srlgs = srlgs;
	}

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Link[] getLinks() {
		return links;
	}
	public boolean ExistAnEdge(int scr,int dir) {
		for(int i=0;i<this.getLinks().length;i++)
		{
			if(((this.getLinks()[i].getEndNodes()[0].getNodeID()==scr)&&(this.getLinks()[1].getLinkID()==dir))||
				((this.getLinks()[i].getEndNodes()[1].getNodeID()==scr)&&(this.getLinks()[0].getLinkID()==dir)))
				return true;
		}
		return false;
	}
	public Link getLink(int scr,int dir) {
		for(int i=0;i<this.getLinks().length;i++)
		{
			if(((this.getLinks()[i].getEndNodes()[0].getNodeID()==scr)&&(this.getLinks()[1].getLinkID()==dir))||
				((this.getLinks()[i].getEndNodes()[1].getNodeID()==scr)&&(this.getLinks()[0].getLinkID()==dir)))
				return this.getLinks()[i];
		}
	 throw new RuntimeException();
	
	}
	public void setLinks(Link[] links) {
		this.links = links;
	}
	
	

	public SRLG[] getSrlgs() {
		return srlgs;
	}

	public void setSrlgs(SRLG[] srlgs) {
		this.srlgs = srlgs;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < nodes.length; i++)
			stringBuffer.append(nodes[i].toString() + "\n");
		for (int i = 0; i < links.length; i++)
			stringBuffer.append(links[i].toString() + "\n");
		return stringBuffer.toString();
	}

}
