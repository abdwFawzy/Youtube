import graph.advertismentGraph;
import graph.Graph;

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
        graph.calculateSimilarityAndAddEdges();
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
        int adId = 1001;
        String adTitle = "Interactive Cat Toy Set";
        String advertiser = "FunPlay";
        String adType = "Pre-roll";
        int adDuration = 30;
        int adViews = 10000;
        int adLikes = 300;
        double adLikeRate = 0.03;
        double adDislikeRate = 0.05;
        double adPrice = 500;
        String adDescription = "Engage your cat with this interactive toy set";
        List<String> adTags = new ArrayList<>();
        adTags.add("Cats");
        adTags.add("Toys");
        adTags.add("Entertainment");

        Ad ad = new Ad(adId, adTitle, advertiser, adDuration, adViews, adLikes, adLikeRate, adDislikeRate, adPrice, adDescription, adTags);

        // Perform the test
        List<Video> similarVideos = graph.getSimilarVideos(ad);
        int similarVideosSize = graph.getSimilarVideos(ad).size();

        assertEquals(similarVideos.get(0).getVideoTitle(), "Cute Kittens Playing");
    }

    /**
     * Tests the `exportGraph` method of the `advertismentGraph` class.
     */
    @Test
    public void testExportGraph() {
        HashMap<Video, HashSet<VideoSimilarityPair>> exportedGraph = graph.exportGraph();

        assertNotNull(exportedGraph);
        assertEquals(graph.getAdjacencyList(), exportedGraph);
        System.out.println(exportedGraph);
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
        Video video = videos.get(7);
        System.out.println(video);
        double similarityThreshold = 199;

        // Call the method under test
        List<Video> similarVideos = graph.retrieveSimilarVideos(video, similarityThreshold);
        System.out.println(similarVideos);

        // Perform assertions to validate the results
        assertNotNull(similarVideos);
        assertEquals(1, similarVideos.size());
        // Add more assertions as needed
    }

    // Rest of the test methods...
}
