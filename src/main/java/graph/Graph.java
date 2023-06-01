package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import advertisment.Ad;
import advertisment.Video;

public interface Graph {
        
    public void addVertex(int num);
    
    public void addEdge(int from, int to);

    public Graph getEgonet(int center);

    public List<Graph> getSCCs();

    public List<Video> getSimilarVideos(Ad ad);   

	public HashMap<Integer, HashSet<Integer>> exportGraph();

	boolean hasEdge(int source, int destination);

	boolean hasVertex(int vertex);

    public int getVertices();

    public int getEdges();

} 

