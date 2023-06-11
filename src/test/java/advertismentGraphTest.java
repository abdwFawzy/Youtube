import graph.advertismentGraph;
import graph.Graph;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import advertisment.Ad;
import advertisment.Video;
import advertisment.VideoSimilarityPair;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

import util.GraphLoader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The `advertismentGraphTest` class contains unit tests for the `advertismentGraph` class.
 * It tests various methods and functionalities of the `advertismentGraph` class.
 *
 * Authors:
 * - Abdalrhman Fawzy
 */
public class advertismentGraphTest {

    private advertismentGraph graph;

    /**
     * Sets up the test environment before each test case.
     * It creates an instance of `advertismentGraph` and loads the graph from a CSV file.
     */
    @BeforeEach
    public void setUp() {
        graph = new advertismentGraph();
        GraphLoader.loadGraph(graph, "data/Videos_small.csv");
    }

    /**
     * Tests the `addVertex` method of the `advertismentGraph` class.
     */
    @Test
    public void testAddVertexWithVideo() {
        Video video = new Video("Sample Video", "Sample Channel", new Date(), new ArrayList<>(), 0, 0, 0, "", 0);

        graph.addVertex(video);

        assertTrue(graph.hasVertex(video));
    }

    /**
     * Tests the `addEdge` method of the `advertismentGraph` class.
     */
    @Test
    public void testAddEdgeWithVideos() {
        Video video1 = new Video("Video 1", "Channel 1", new Date(), new ArrayList<>(), 0, 0, 0, "", 0);
        Video video2 = new Video("Video 2", "Channel 2", new Date(), new ArrayList<>(), 0, 0, 0, "", 0);

        graph.addVertex(video1);
        graph.addVertex(video2);
        graph.addEdge(video1, new VideoSimilarityPair(video2, 3));

        assertTrue(graph.hasEdge(video1, video2));
    }

    /**
     * Tests the `getSimilarVideos` method of the `advertismentGraph` class.
     */
    @Test
    public void testGetSimilarVideos() {
        // Create sample data for the test
        List<Ad> Ads = graph.getAds();
        Ad ad = Ads.get(5);
        // Perform the test
        List<Video> similarVideos = graph.getSimilarVideos(ad);
        int similarVideosSize = similarVideos.size();
        assertEquals("Unboxing the Latest Tech Gadgets", similarVideos.get(0).getVideoTitle());


//        assertEquals(similarVideos.get(0).getVideoTitle(), "Cute Kittens Playing");
    }

    /**
     * Tests the `exportGraph` method of the `advertismentGraph` class.
     */
    @Test
    public void testExportGraph() {
        HashMap<Video, HashSet<VideoSimilarityPair>> exportedGraph = graph.exportGraph();

        assertNotNull(exportedGraph);
        assertEquals(graph.getAdjacencyList(), exportedGraph);
    }

    /**
     * Tests the `hasEdge` method of the `advertismentGraph` class.
     */
    @Test
    public void testHasEdge() {
        Video video1 = new Video("Video 1", "Channel 1", new Date(), new ArrayList<>(), 0, 0, 0, "", 0);
        Video video2 = new Video("Video 2", "Channel 2", new Date(), new ArrayList<>(), 0, 0, 0, "", 0);

        graph.addVertex(video1);
        graph.addVertex(video2);
        graph.addEdge(video1, new VideoSimilarityPair(video2, 1));

        assertTrue(graph.hasEdge(video1, video2));
    }

    /**
     * Tests the `hasVertex` method of the `advertismentGraph` class.
     */
    @Test
    public void testHasVertex() {
        Video video = new Video("Sample Video", "Sample Channel", new Date(), new ArrayList<>(), 0, 0, 0, "", 0);

        graph.addVertex(video);

        assertTrue(graph.hasVertex(video));
    }

    @Test
    public void testRetrieveSimilarVideos() {
        // Create test data
        List<Video> videos = new ArrayList<Video>(graph.exportGraph().keySet());
        Video video = videos.get(2);
        double similarityThreshold = 1;

        // Call the method under test
        List<Video> similarVideos = graph.retrieveSimilarVideos(video, similarityThreshold);

        // Perform assertions to validate the results
        assertNotNull(similarVideos);
        assertEquals(1, similarVideos.size());
        // Add more assertions as needed
    }

    // Rest of the test methods...

    @Test
    public void testRetrieveAdsForVideo()
    {
        Video video = graph.videos.get(2);

        // Call the method under test
        HashSet<Ad> similarAds = graph.retrieveAdsForVideo(video);

        // Perform assertions to validate the results
        System.out.println(video);
        System.out.println(similarAds);
    }
}
