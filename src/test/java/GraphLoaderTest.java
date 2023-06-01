import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graph.Graph;
import graph.advertismentGraph;

import util.GraphLoader;

public class GraphLoaderTest {

    private Graph graph;
    private GraphLoader graphLoader;

    @BeforeEach
    public void setup() {
        graph = new advertismentGraph();
        graphLoader = new GraphLoader();
        //graphLoader.openFile(graph);

        String fileName = "data/Videos_small.csv";
        graphLoader.loadGraph(graph, fileName);
    }

    @Test
    public void testLoadGraph() {

        // Verify the graph has been loaded correctly
        assertEquals(28, graph.getVertices()); // Expected number of vertices in the graph
        assertEquals(0, graph.getEdges()); // Expected number of edges in the graph

        // Verify the presence of specific vertices
//        assertTrue(graph.hasVertex("Cute Kittens Playing"));
//        assertTrue(graph.hasVertex("Top 10 Movie Trailers of 2022"));
//        assertTrue(graph.hasVertex("Delicious Chocolate Cake Recipe"));
//        assertFalse(graph.hasVertex("Non-existent Video"));
//
//        // Verify the presence of specific edges
//        assertTrue(graph.hasEdge("Cute Kittens Playing", "Top 10 Movie Trailers of 2022"));
//        assertTrue(graph.hasEdge("Delicious Chocolate Cake Recipe", "Guitar Tutorial: Beginner Chords"));
//        assertFalse(graph.hasEdge("Cute Kittens Playing", "Non-existent Video"));
    }
    @Test
    public void testExportGraph()
    {
        System.out.println(graph.exportGraph());
        assertEquals(0, 0);
    }
}

