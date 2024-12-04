package com.plus_courts_chemins;

import java.util.*;

public class myDijkstra implements Algorithm {
    private Map<Node, Integer> distances; // Distance entre le nœud source et le nœud courant
    private Map<Node, Node> predecessors; // Prédécesseur du nœud courant
    private PriorityQueue<Node> pq; // File de priorité pour les nœuds à traiter

    public myDijkstra() {
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
    }

    @Override
    public void computeShortestPaths(Node source) {
        distances.put(source, 0);
        pq.add(source);
        // Tant que la file de priorité n'est pas vide
        while (!pq.isEmpty()) {
            Node u = pq.poll();

            // Parcourir les arêtes sortantes du nœud u
            for (Edge e : u.getOutgoingEdges()) {
                Node v = e.getDestination();
                int weight = e.getWeight();
                int distanceThroughU = distances.get(u) + weight; // Distance entre le nœud source et le nœud v en passant par u

                // Si la distance entre le nœud source et le nœud v en passant par u est inférieure à la distance actuelle
                if (distanceThroughU < distances.getOrDefault(v, Integer.MAX_VALUE)) {
                    pq.remove(v);
                    distances.put(v, distanceThroughU);
                    predecessors.put(v, u);
                    pq.add(v);
                }
            }
        }
    }

    @Override
    public Map<Node, Integer> getDistances() {
        return distances;
    }

    @Override
    public Map<Node, Node> getPredecessors() {
        return predecessors;
    }

}

