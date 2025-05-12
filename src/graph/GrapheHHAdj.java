package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrapheHHAdj implements VarGraph {


	private final HashMap<String, List<Arc<String>>> graph = new HashMap<>();

	@Override
	public List<Arc<String>> getSucc(String s) {
		if (!graph.containsKey(s)) {
			throw new IllegalArgumentException("Sommet absent");
		}
		return new ArrayList<>(graph.get(s));
	}

	@Override
	public void ajouterSommet(String noeud) {
		graph.putIfAbsent(noeud, new ArrayList<>());
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		ajouterSommet(source);
		ajouterSommet(destination);

		List<Arc<String>> voisins = graph.get(source);
		for (Arc<String> arc : voisins) {
			if (arc.dst().equals(destination)) {
				throw new IllegalArgumentException("Arc déjà existant");
			}
		}
		voisins.add(new Arc<>(valeur, destination));
	}

	public List<String> getAllSommets() {
		return new ArrayList<>(graph.keySet());
	}

	public boolean isGraphEmpty() {
		return graph.isEmpty();
	}

	public boolean isConnected(String source, String destination) {
		if (!graph.containsKey(source)) {
			throw new IllegalArgumentException("Sommet absent");
		}
		else if (!graph.containsKey(destination)) {
			throw new IllegalArgumentException("Sommet absent");
		}
		for (Arc<String> arc : graph.get(source)) {
			if (arc.dst().equals(destination)) {
				return true; // Si on trouve l'arc entre source et destination
			}
		}

		return false; // Aucun arc trouvé entre source et destination
	}

	public int getPoids(String source, String destination) {
		if (!graph.containsKey(source)) {
			throw new IllegalArgumentException("Sommet source introuvable : " + source);
		}

		for (Arc<String> arc : graph.get(source)) {
			if (arc.dst().equals(destination)) {
				return arc.val(); // ← Poids trouvé
			}
		}
		throw new IllegalArgumentException("Aucun arc de " + source + " vers " + destination);
	}

	public List<Arc<String>> getVoisins(String source) {
		if (!graph.containsKey(source)) {
			throw new IllegalArgumentException("Sommet source introuvable : " + source);
		}
		return graph.get(source);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String source : graph.keySet()) { // Parcours de toutes les clés (les sommets)
			List<Arc<String>> voisins = graph.get(source); // Liste des successeurs de 'source'
			if (voisins.isEmpty()) { // Si 'source' n'a pas de successeurs
				sb.append(source).append(": , ");
			} else {
				for (Arc<String> arc : voisins) {
					sb.append(source).append("-").append(arc.dst()).append("(").append(arc.val()).append("), ");
				}
			}
		}
		return sb.toString();
	}


}
