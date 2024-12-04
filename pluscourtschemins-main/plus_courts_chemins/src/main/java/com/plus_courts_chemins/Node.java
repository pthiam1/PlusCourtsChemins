package com.plus_courts_chemins;
import java.util.*;

public class Node {
    private String id;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;

    public Node(String id) {
        this.id = id;
        this.outgoingEdges = new ArrayList<>();
        this.incomingEdges = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public void addOutgoingEdge(Edge edge) {
        this.outgoingEdges.add(edge);
    }

    public void addIncomingEdge(Edge edge) {
        this.incomingEdges.add(edge);
    }
}