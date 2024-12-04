package com.plus_courts_chemins;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Graph;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("#".repeat(30) + " Plus courts chemins " + "#".repeat(30));

        // Configuration pour afficher le graphe
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Graphe");
        graph.setAttribute("ui.stylesheet", "node { size: 40px; fill-color: red; text-size: 20px; }" +
                "edge { fill-color: green; text-size: 10px; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        // Création des nœuds
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");

        // Création des arêtes
        Edge AB = new Edge(A, B, 4);
        Edge AC = new Edge(A, C, 3);
        Edge BC = new Edge(B, C, 3);
        Edge BD = new Edge(B, D, 2);
        Edge BE = new Edge(B, E, 3);
        Edge CB = new Edge(C, B, 1);
        Edge CE = new Edge(C, E, 5);
        Edge CD = new Edge(C, D, 4);
        Edge ED = new Edge(E, D, 1);

        // Ajout des arêtes sortantes et entrantes
        A.addOutgoingEdge(AB);
        A.addOutgoingEdge(AC);
        B.addOutgoingEdge(BD);
        B.addOutgoingEdge(BE);
        B.addOutgoingEdge(BC);
        C.addOutgoingEdge(CB);
        C.addOutgoingEdge(CE);
        C.addOutgoingEdge(CD);
        E.addOutgoingEdge(ED);

        B.addIncomingEdge(AB);
        B.addIncomingEdge(CB);
        C.addIncomingEdge(AC);
        C.addIncomingEdge(BC);
        D.addIncomingEdge(BD);
        D.addIncomingEdge(CD);
        D.addIncomingEdge(ED);
        E.addIncomingEdge(BE);
        E.addIncomingEdge(CE);

        // Ajout des nœuds et des arêtes au graphe GraphStream
        graph.addNode("A").setAttribute("ui.label", "A");
        graph.addNode("B").setAttribute("ui.label", "B");
        graph.addNode("C").setAttribute("ui.label", "C");
        graph.addNode("D").setAttribute("ui.label", "D");
        graph.addNode("E").setAttribute("ui.label", "E");

        graph.addEdge("AB", "A", "B", true).setAttribute("ui.label", "4");
        graph.addEdge("AC", "A", "C", true).setAttribute("ui.label", "3");
        graph.addEdge("BC", "B", "C", true).setAttribute("ui.label", "3");
        graph.addEdge("BD", "B", "D", true).setAttribute("ui.label", "2");
        graph.addEdge("BE", "B", "E", true).setAttribute("ui.label", "3");
        graph.addEdge("CB", "C", "B", true).setAttribute("ui.label", "1");
        graph.addEdge("CE", "C", "E", true).setAttribute("ui.label", "5");
        graph.addEdge("CD", "C", "D", true).setAttribute("ui.label", "4");
        graph.addEdge("ED", "E", "D", true).setAttribute("ui.label", "1");

        graph.display();

        // 1. Timing the custom Dijkstra algorithm
        System.out.println("-".repeat(40) + " Mon Dijkstra " + "-".repeat(40));
        Map<String, Node> graphMap = new HashMap<>();
        graphMap.put("A", A);
        graphMap.put("B", B);
        graphMap.put("C", C);
        graphMap.put("D", D);
        graphMap.put("E", E);

        myDijkstra myDijkstra = new myDijkstra();
        
        double startTime = System.nanoTime();
        myDijkstra.computeShortestPaths(A);
        double endTime = System.nanoTime();
        
        double customDijkstraTime = endTime - startTime;
        customDijkstraTime = customDijkstraTime * Math.pow(10, -9);
        System.out.println("Temps d'exécution du Dijkstra personnalisé: " + customDijkstraTime + " secondes");

        Map<Node, Integer> distances = myDijkstra.getDistances();
        // Map<Node, Node> predecessors = myDijkstra.getPredecessors();

        for (Map.Entry<Node, Integer> entry : distances.entrySet()) {
            System.out.println("Chemin le plus court vers " + entry.getKey().getId() + ": " + entry.getValue());
            System.out.println("Distance: " + entry.getValue());
        }

    
        System.out.println("-".repeat(40) + " Dijkstra de GraphStream " + "-".repeat(40));
        
        Dijkstra dijkstra = new Dijkstra(Element.EDGE, null, "ui.label"); 
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode("A")); 
        
        startTime = System.nanoTime();
        dijkstra.compute(); 
        endTime = System.nanoTime();
        
        double graphStreamDijkstraTime = endTime - startTime;
        graphStreamDijkstraTime = graphStreamDijkstraTime * Math.pow(10, -9);

        System.out.println("Temps d'exécution du Dijkstra de GraphStream: " + graphStreamDijkstraTime + " secondes");

        // Affichage des plus courts chemins
        for (org.graphstream.graph.Node node : graph) {
            System.out.println("Chemin le plus court vers " + node.getId() + ": " + 
                               dijkstra.getPath(node)); 
            System.out.println("Distance: " + dijkstra.getPathLength(node)); 
        }

        // Libérer les ressources
        dijkstra.clear();
    }
}


/**
 * Remarque: 
 * Le temps d'exécution du Dijkstra personnalisé est plus rapide que celui de GraphStream pour le même graphe.
 */