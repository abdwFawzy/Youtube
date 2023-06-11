package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.ArrayDeque;

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
    public List<Video> videos;
    private List<Ad> Ads;
    private int edges;
    private HashMap<Video, HashSet<Ad>> adMap;

    public advertismentGraph() 
    {
        adjacencyList = new HashMap<>();
        adMap = new HashMap<>();
        Ads = new ArrayList<Ad>();
        videos = new ArrayList<Video>(adjacencyList.keySet()); 
        edges = 0;
    }

    @Override
    public void addVertex(Video video) 
    {
        if (!adjacencyList.containsKey(video)) {
            adjacencyList.put(video, new HashSet<>());
        }
    }

    @Override
    public void addAd(Ad ad)
    {
        // Implement this TODO
        Ads.add(ad);
    }

    /**
     * Calculates the similarity between the specified video and other videos in the graph.
     * It adds weighted edges to the graph based on the similarity calculations.
     *
     * @param video The Video object for which similarity is calculated and edges are added.
     */
    @Override
    public void calculateSimilarityAndAddEdges() 
    {

        videos = new ArrayList<Video>(adjacencyList.keySet()); 

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

                if (similarity > 0.1)
                {
                    if (!video.equals(graphVideo))
                        addEdge(video, new VideoSimilarityPair(graphVideo, similarity));
                }
            }
        }

    }

    @Override
    public void calculateSimilarityAndAddEdgesInAds() {
        for (Video video : videos) {
            Set<String> videoSet = null;
            try {
                videoSet = video.getAttributes();
            } catch (NullPointerException e) {
                System.err.println("Error: Unable to retrieve attributes from the video");
                continue; // Skip to the next video
            }

            adMap.put(video, new HashSet<Ad>());

            for (Ad ad : Ads) {
                Set<String> adSet = null;
                try {
                    adSet = ad.getAttributes();
                } catch (NullPointerException e) {
                    System.err.println("Error: Unable to retrieve attributes from the ad");
                    continue; // Skip to the next ad
                }

                double similarity = JaccardIndex.calculateJaccardIndex(adSet, videoSet);
                if (similarity >= 0.1) {
                    adMap.get(video).add(ad);
                }
            }
        }
    }


    @Override
    public int getVertices()
    {
        return adjacencyList.size();
    }

   /**
    * Returns the List of ads.
    *
    * @return The List of ads.
    */
    @Override
    public List<Ad> getAds() {
        return Ads;
    }

    @Override
    public int getEdges()
    {
        return edges;
    }

    @Override
    public void addEdge(Video from, VideoSimilarityPair to) 
    {
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

    private double getSimilarVideo(Ad ad, Video video) 
    {
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

        return JaccardIndex.calculateJaccardIndex(videoSet, adSet);
    }

    @Override
    public List<Video> getSimilarVideos(Ad ad) 
    {
        // TODO: Implement the logic to find and return similar videos based on the given ad
        // You can use the graph's data structure to perform similarity calculations or lookups

        videos = new ArrayList<Video>(adjacencyList.keySet());

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

    /**
     * Retrieve videos that are similar to the given video.
     *
     * @param video                The video for which similar videos are retrieved.
     * @param similarityThreshold  Similarity threshold for considering a video as similar.
     * @return List of similar videos.
     */
    @Override
    public List<Video> retrieveSimilarVideos(Video video, double similarityThreshold) {
        List<Video> similarVideos = new ArrayList<>();

        Queue<Video> queue = new ArrayDeque<>(); // Queue to store videos for BFS traversal
        Set<Video> visited = new HashSet<>(); // Set to keep track of visited videos

        queue.add(video); // Start the traversal from the given video

        while (!queue.isEmpty() && similarVideos.size() < similarityThreshold) {
            Video currentVideo = queue.remove(); // Dequeue the current video
            visited.add(currentVideo);

            // Assuming adjacencyList is a Map<Video, Set<VideoSimilarityPair>> representing the graph structure.
            Set<VideoSimilarityPair> neighbors = adjacencyList.get(currentVideo);

            for (VideoSimilarityPair neighbor : neighbors) {
                Video neighborVideo = neighbor.getVideo();

                if (similarVideos.size() >= similarityThreshold)
                    break;

                if (!visited.contains(neighborVideo)) {

                    similarVideos.add(neighborVideo); // Add the neighbor video to the list of similar videos
                    queue.add(neighborVideo); // Enqueue the neighbor video for further traversal
                    
                }
            }
        }

        return similarVideos; // Return the list of similar videos
    }

    /**
    * Retrieves the set of ads associated with the given video.
    *
    * @param video The video for which to retrieve the associated ads.
    * @return A HashSet of Ad objects associated with the given video.
    */
    @Override
    public HashSet<Ad> retrieveAdsForVideo(Video video)
    {
        return adMap.get(video);
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

    public HashMap<Video, HashSet<Ad>> getAdMap()
    {
        return adMap;
    }

}

