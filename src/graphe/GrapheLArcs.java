package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe {
    private List<Arc> arcs;

    public GrapheLArcs() {
        this.arcs = new ArrayList<>();
    }

    public GrapheLArcs(String input) {
        this();
        peupler(input);
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

	@Override
	public List<String> getSommets() {
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
	public List<String> getSucc(String sommet) {
        List<String> outgoingArcs = new ArrayList<>();
        for (Arc arc : this.arcs) {
            if (arc.getSource().equals(sommet)) {
                outgoingArcs.add(arc.getSource());
            }
        }
        return outgoingArcs;
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
        this.arcs.add(new Arc(noeud, "", -1));
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		this.arcs.add(new Arc(source, destination, valeur));
	}

	@Override
	public void oterSommet(String noeud) {
			
	}

	@Override
	public void oterArc(String source, String destination) {
        for (int i = 0; i < this.arcs.size(); i++) {
            if (this.arcs.get(i).getSource().equals(source) && this.arcs.get(i).getDestination().equals(destination)) {
                this.arcs.remove(i);
                break;
            }
        }
	}

    @Override
    public String toString() {
        return this.toAString();
    }
}
