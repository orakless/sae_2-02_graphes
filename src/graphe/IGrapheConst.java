package graphe;


import java.util.List;

public interface IGrapheConst {
	List<String> getSommets();
	List<String> getSucc(String sommet);
	int getValuation(String src, String dest);
	boolean contientSommet(String sommet);
	boolean contientArc(String src, String dest);
}
