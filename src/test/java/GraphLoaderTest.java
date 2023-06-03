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

    }
}

