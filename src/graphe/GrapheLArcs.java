package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe {
    private List<Arc> arcs;

    // Constructor
    public GrapheLArcs() {
        this.arcs = new ArrayList<>();
    }

    // Constructor with string input
    public GrapheLArcs(String input) {
        this.arcs = new ArrayList<>();
        String[] nodes = input.split(", ");

        for (String node : nodes) {
            if (node.endsWith(":")) {
                Arc dummyArc = new Arc(node.replace(":", ""), "", 0);
                this.arcs.add(dummyArc);
            } else {
                String[] nodeData = node.split("-");
                String[] arcData = nodeData[1].replace("(", "").replace(")", "").split(",");
                String source = nodeData[0];
                String dest = arcData[0];
                int valuation = Integer.parseInt(arcData[1]);
                Arc arc = new Arc(source, dest, valuation);
                this.arcs.add(arc);
            }
        }
    }

    // Implement interface methods
    public List<Arc> getArcs() {
        return this.arcs;
    }

    public boolean addArc(Arc arc) {
        return this.arcs.add(arc);
    }

    public boolean removeArc(Arc arc) {
        return this.arcs.remove(arc);
    }

    public List<Arc> getIncomingArcs(String node) {
        List<Arc> incomingArcs = new ArrayList<>();
        for (Arc arc : this.arcs) {
            if (arc.getDestination().equals(node)) {
                incomingArcs.add(arc);
            }
        }
        return incomingArcs;
    }

    public List<Arc> getOutgoingArcs(String node) {
        List<Arc> outgoingArcs = new ArrayList<>();
        for (Arc arc : this.arcs) {
            if (arc.getSource().equals(node)) {
                outgoingArcs.add(arc);
            }
        }
        return outgoingArcs;
    }

    public List<String> getAllNodes() {
        List<String> nodes = new ArrayList<>();
        for (Arc arc : this.arcs) {
            if (!nodes.contains(arc.getSource())) {
                nodes.add(arc.getSource());
            }
            if (!nodes.contains(arc.getDestination())) {
                nodes.add(arc.getDestination());
            }
        }
        return nodes;
    }

	@Override
	public List<String> getSommets() {
		return null;
	}

	@Override
	public List<String> getSucc(String sommet) {
		return null;
	}

	@Override
	public int getValuation(String src, String dest) {
		return 0;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return false;
	}

	@Override
	public boolean contientArc(String src, String dest) {
		return false;
	}

	@Override
	public void ajouterSommet(String noeud) {
	
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		
	}

	@Override
	public void oterSommet(String noeud) {
			
	}

	@Override
	public void oterArc(String source, String destination) {
		
	}
}
