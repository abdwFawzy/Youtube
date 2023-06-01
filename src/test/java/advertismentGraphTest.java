import graph.advertismentGraph;
import graph.Graph;
import advertisment.Ad;
import advertisment.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class advertismentGraphTest {

    private advertismentGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new advertismentGraph();
    }

    @Test
    public void testAddVertexWithVideo() {
        Video video = new Video("Video1", "URL1", new Date(), new ArrayList<>(), 1, 1, 1, "Category1", 1);

        graph.addVertex(video.hashCode());

        assertTrue(graph.hasVertex(video.hashCode()));
    }

    @Test
    public void testAddEdgeWithVideos() {
        Video video1 = new Video("Video1", "URL1", new Date(), new ArrayList<>(), 1, 1, 1, "Category1", 1);
        Video video2 = new Video("Video1", "URL1", new Date(), new ArrayList<>(), 2, 2, 2, "Category2", 2);

        graph.addEdge(video1.hashCode(), video2.hashCode());

        assertTrue(graph.hasEdge(video1.hashCode(), video2.hashCode()));
    }
    @Test
    public void testGetSimilarVideos() {
        // Create sample data for the test
        Ad ad = new Ad(1, "Title", "Description", "URL", 100);
        
        // Perform the test
        List<Video> similarVideos = graph.getSimilarVideos(ad);

        // Assert the result
        assertNull(similarVideos); // Assuming the implementation returns null for now
    }

    @Test
    public void testExportGraph() {
        HashMap<Integer, HashSet<Integer>> exportedGraph = graph.exportGraph();

        assertNotNull(exportedGraph);
        assertEquals(graph.getAdjacencyList(), exportedGraph);
    }

    @Test
    public void testHasEdge() {
        graph.addEdge(1, 2);

        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(2, 1));
    }

    @Test
    public void testHasVertex() {
        graph.addVertex(1);

        assertTrue(graph.hasVertex(1));
        assertFalse(graph.hasVertex(2));
    }


    // Rest of the test methods...

}
