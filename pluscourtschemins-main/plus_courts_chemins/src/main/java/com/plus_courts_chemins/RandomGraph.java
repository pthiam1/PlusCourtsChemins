package com.plus_courts_chemins;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Edge;

import java.util.Random;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;


public class RandomGraph {
    public static void main(String[] args) throws Exception {
        System.out.println("#".repeat(30) + " Exemple de graphe aléatoire " + "#".repeat(30));
        System.setProperty("org.graphstream.ui", "swing");
        
        /*
        Graph graph = new SingleGraph("random euclidean");
        Generator gen = new RandomEuclideanGenerator();
        gen.addSink(graph);
        gen.begin();
        for(int i=0; i<1000; i++) {
                gen.nextEvents();
        }
        gen.end();
        graph.display(true);
        
        //un graphe aléatoire de 100 noeuds et de degré moyen 2
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph);
        gen.begin();
        for(int i=0; i<100; i++)
            gen.nextEvents();
        gen.end();
        graph.display();
        */
       
        RandomGraph randomGraph = new RandomGraph();
        randomGraph.getRandGraph(100, 2);

    }

    // Générer un graphe aléatoire de n noeuds et de degré moyen m
    public void getRandGraph(int n, int m) {
        Graph graph = new SingleGraph("Graphe aléatoire");
        Generator generator = new RandomGenerator(3); // degré moyen 3
        generator.addSink(graph);
        graph.setAttribute("ui.stylesheet", "node { size: 15px; fill-color: red; } edge { fill-color: green; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

    
        Random rand = new Random();
        generator.begin();

        // Générer 100 événements 
        for (int i = 0; i < 100; i++) {
            generator.nextEvents();
        }
        
        // Ajouter un poids aléatoire à chaque arête
        for (Edge edge : graph.nodes().flatMap(node -> node.edges()).toArray(Edge[]::new)) {
            edge.setAttribute("weight", rand.nextInt(10));
        }
        
        generator.end();

        graph.display();
    }

}

