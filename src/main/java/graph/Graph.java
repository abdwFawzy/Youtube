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
import advertisment.VideoSimilarityPair;

/**
 * Represents a graph data structure for storing videos.
 */
public interface Graph {

    /**
     * Adds a vertex (video) to the graph.
     *
     * @param video The video to be added as a vertex.
     */
    public void addVertex(Video video);

    /**
     * Adds an edge between two videos in the graph.
     *
     * @param from The source video from which the edge starts.
     * @param to   The destination video to which the edge points.
     */
    public void addEdge(Video from, VideoSimilarityPair to);

    /**
     * Exports the graph as a HashMap representation.
     *
     * @return A HashMap representing the graph structure, where each vertex is mapped to a set of VideoSimilarityPair objects.
     */
    public HashMap<Video, HashSet<VideoSimilarityPair>> exportGraph();

    /**
     * Checks if there is an edge (connection) between the source video and the destination video in the graph.
     *
     * @param source      The source video.
     * @param destination The destination video.
     * @return true if an edge exists between the source and destination videos, false otherwise.
     */
    boolean hasEdge(Video source, Video destination);

    /**
     * Checks if a given vertex (video) exists in the graph.
     *
     * @param vertex The video vertex to check.
     * @return true if the vertex exists in the graph, false otherwise.
     */
    boolean hasVertex(Video vertex);

    /**
     * Returns the number of vertices (videos) in the graph.
     *
     * @return The number of vertices in the graph.
     */
    public int getVertices();

    /**
     * Returns the number of edges in the graph.
     *
     * @return The number of edges in the graph.
     */
    public int getEdges();

    
    /**
     * Adds an advertisement to the graph.
     *
     * @param ad The advertisement to add to the graph.
     */
    public void addAd(Ad ad);

    /**
     * Returns the List of ads.
     *
     * @return The List of ads.
     */
    public List<Ad> getAds();

     /**
     * Calculates the similarity between a specified vertex (video) and all other videos in the graph.
     * It then adds edges in the graph based on the similarity calculations.
     *
     * @param vertex The Video vertex for which similarity is calculated and edges are added.
     */
    public void calculateSimilarityAndAddEdges();

   
    public void calculateSimilarityAndAddEdgesInAds();


}
