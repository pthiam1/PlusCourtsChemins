package com.plus_courts_chemins;

import java.util.Map;

public interface Algorithm {
    
    void computeShortestPaths(Node source);
    Map<Node, Integer> getDistances();
    Map<Node, Node> getPredecessors();

}
