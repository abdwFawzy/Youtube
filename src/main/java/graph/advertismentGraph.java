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

import similarity.JaccardIndex;

/**
 * Represents a graph that implements the Graph interface and provides recommendations for videos similar to an advertisement.
 * This class manages the relationships between videos and advertisements and calculates the similarity between them.
 * Author: Abdalrhman Fawzy
 */

public class advertismentGraph implements Graph {
    private HashMap<Video, HashSet<Video>> adjacencyList;
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
    public void addEdge(Video from, Video to) {
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
    public HashMap<Video, HashSet<Video>> exportGraph() {
        return adjacencyList;
    }

    @Override
    public boolean hasEdge(Video source, Video destination) {
        return adjacencyList.containsKey(source) && adjacencyList.get(source).contains(destination);
    }

    @Override
    public boolean hasVertex(Video vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public HashMap<Video, HashSet<Video>> getAdjacencyList()
    {
        return adjacencyList;
    }

    class VideoSimilarityPair {
        private Video video;
        private double similarity;

        public VideoSimilarityPair(Video video, double similarity) {
            this.video = video;
            this.similarity = similarity;
        }

        public Video getVideo() {
            return video;
        }

        public double getSimilarity() {
            return similarity;
        }

        @Override
        public String toString() {
            return "VideoSimilarityPair{" +
                    "video=" + video +
                    ", similarity=" + similarity +
                    '}';
        }
    }
}

