package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;

import advertisment.Ad;
import advertisment.Video;
import advertisment.VideoSimilarityPair;

import similarity.JaccardIndex;

/**
 * Represents a graph that implements the Graph interface and provides recommendations for videos similar to an advertisement.
 * This class manages the relationships between videos and advertisements and calculates the similarity between them.
 * Author: Abdalrhman Fawzy
 */

public class advertismentGraph implements Graph, GraphAnalyzer{
    private HashMap<Video, HashSet<VideoSimilarityPair>> adjacencyList;
    private int edges;
public advertismentGraph() {
        adjacencyList = new HashMap<>();
        edges = 0;
    }

    @Override
    public void addVertex(Video video) {
        if (!adjacencyList.containsKey(video)) {
            adjacencyList.put(video, new HashSet<>());
        }
    }

    /**
     * Calculates the similarity between the specified video and other videos in the graph.
     * It adds weighted edges to the graph based on the similarity calculations.
     *
     * @param video The Video object for which similarity is calculated and edges are added.
     */
    @Override
    public void calculateSimilarityAndAddEdges() {

        List<Video> videos = new ArrayList<Video>(adjacencyList.keySet());
        Set<String> videoSet = new HashSet<String>();
        Set<String> videoGraphSet = new HashSet<String>();

        for (Video video : videos)
        {
            try {
                videoSet = video.getAttributes();
            } catch (NullPointerException e) {
                // Handle the exception gracefully, e.g., provide default values or return an appropriate result
                System.err.println("Error: Unable to retrieve attributes from the video");
            }

            for (Video graphVideo : videos)
            {
                try {
                    videoGraphSet = graphVideo.getAttributes();
                } catch (NullPointerException e) {
                    // Handle the exception gracefully, e.g., provide default values or return an appropriate result
                    System.err.println("Error: Unable to retrieve attributes from the video");
                }

                double similarity = JaccardIndex.calculateJaccardIndex(videoGraphSet, videoSet);

                if (similarity > 0.2)
                {
                    if (!video.equals(graphVideo))
                        addEdge(video, new VideoSimilarityPair(graphVideo, similarity));
                }
            }
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
    public void addEdge(Video from, VideoSimilarityPair to) {
        addVertex(from);
        addVertex(to.getVideo());

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

    private double getSimilarVideo(Ad ad, Video video) {
        Set<String> adSet = new HashSet<>();
        Set<String> videoSet = new HashSet<>();

        try {
            adSet = ad.getAttributes();
        } catch (NullPointerException e) {
            // Handle the exception gracefully, e.g., provide default values or return an appropriate result
            System.err.println("Error: Unable to retrieve attributes from the ad");
        }

        try {
            videoSet = video.getAttributes();
        } catch (NullPointerException e) {
            // Handle the exception gracefully, e.g., provide default values or return an appropriate result
            System.err.println("Error: Unable to retrieve attributes from the video");
        }

        return JaccardIndex.calculateJaccardIndex(adSet, videoSet);
    }

    @Override
    public List<Video> getSimilarVideos(Ad ad) {
        // TODO: Implement the logic to find and return similar videos based on the given ad
        // You can use the graph's data structure to perform similarity calculations or lookups
        List<Video> videos = new ArrayList<Video>(adjacencyList.keySet());

        List<VideoSimilarityPair> videoSimilarities = new ArrayList<VideoSimilarityPair>();

        for (Video video : videos)
        {
            double similarity = getSimilarVideo(ad, video);
            videoSimilarities.add(new VideoSimilarityPair(video, similarity));
        }


        // Sort the videoSimilarities list based on similarity in descending order
        Collections.sort(videoSimilarities, Comparator.comparingDouble(VideoSimilarityPair::getSimilarity).reversed());

        List<Video> sortedVideos = new ArrayList<>();
        for (VideoSimilarityPair pair : videoSimilarities) {
            sortedVideos.add(pair.getVideo());
        }

        return sortedVideos;
        
    }

    @Override
    public HashMap<Video, HashSet<VideoSimilarityPair>> exportGraph() {
        return adjacencyList;
    }

    @Override
    public boolean hasEdge(Video source, Video destination) {
        // Check if the adjacencyList contains the source video
        if (adjacencyList.containsKey(source)) {
            // Retrieve the HashSet of VideoSimilarityPair objects for the source video
            HashSet<VideoSimilarityPair> videoPairs = adjacencyList.get(source);
            // Iterate through the videoPairs and check if the destination video is present
            for (VideoSimilarityPair pair : videoPairs) {
                if (pair.getVideo().equals(destination)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasVertex(Video vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public HashMap<Video, HashSet<VideoSimilarityPair>> getAdjacencyList()
    {
        return adjacencyList;
    }
}

