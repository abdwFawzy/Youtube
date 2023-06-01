package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import advertisment.Ad;
import advertisment.Video;

public class advertismentGraph implements Graph {
    private HashMap<Integer, HashSet<Integer>> adjacencyList;
    private int edges;

    public advertismentGraph() {
        adjacencyList = new HashMap<>();
        edges = 0;
    }

    @Override
    public void addVertex(int num) {
        if (!adjacencyList.containsKey(num)) {
            adjacencyList.put(num, new HashSet<>());
        }
    }

    @Override
    public int getVertices()
    {
        return adjacencyList.size();
    }

    @Override
    public int getEdges()
    {
        return edges;
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);

        adjacencyList.get(from).add(to);
        edges++;
    }

    @Override
    public Graph getEgonet(int center) {
        // TODO: Implement the method to get strongly connected components (SCCs)
        return null;
    }

    @Override
    public List<Graph> getSCCs() {
        // TODO: Implement the method to get strongly connected components (SCCs)
        return null;
    }

    @Override
    public List<Video> getSimilarVideos(Ad ad) {
        // TODO: Implement the logic to find and return similar videos based on the given ad
        // You can use the graph's data structure to perform similarity calculations or lookups
        
        return null;
    }

    @Override
    public HashMap<Integer, HashSet<Integer>> exportGraph() {
        return adjacencyList;
    }

    @Override
    public boolean hasEdge(int source, int destination) {
        return adjacencyList.containsKey(source) && adjacencyList.get(source).contains(destination);
    }

    @Override
    public boolean hasVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public HashMap<Integer, HashSet<Integer>> getAdjacencyList()
    {
        return adjacencyList;
    }
}

