/**
 * The `graph` package provides an interface and classes for managing a graph structure.
 * This package is specifically designed for managing relationships between advertisements and videos.
 * It allows adding vertices and edges, retrieving strongly connected components (SCCs), finding similar videos to an ad, and exporting the graph data.
 * 
 * The main classes/interfaces in this package are:
 * - `Graph`: An interface that defines the basic operations and functionality of a graph.
 * - `advertismentGraph`: A class that implements the `Graph` interface and provides methods to manage the graph structure for advertisements and videos.
 * 
 * 
 * The package utilizes data structures such as HashMap, HashSet, and List for efficient storage and retrieval of graph elements.
 * 
 * Authors:
 * - Abdalrhman Fawzy
 */

package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import advertisment.Ad;
import advertisment.Video;

public interface Graph {
        
    public void addVertex(Video video);
    
    public void addEdge(Video from, Video to);

    public Graph getEgonet(int center);

    public List<Graph> getSCCs();

    public List<Video> getSimilarVideos(Ad ad);   

	public HashMap<Video, HashSet<Video>> exportGraph();

	boolean hasEdge(Video source, Video destination);

	boolean hasVertex(Video vertex);

    public int getVertices();

    public int getEdges();

} 

