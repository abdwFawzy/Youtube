/**
 * The `util` package provides utility classes for various operations and functionalities.
 * 
 * The main class in this package is:
 * - `GraphLoader`: A utility class that facilitates loading graph data from a CSV file into a graph data structure.
 * 
 * The package includes methods for loading a graph from a CSV file and opening a file dialog to choose the CSV file interactively.
 * It relies on the Apache Commons CSV library for parsing CSV files.
 * 
 * Example usage:
 * ```java
 * GraphLoader graphLoader = new GraphLoader();
 * Graph graph = new advertismentGraph();
 * graphLoader.openFile(graph);
 * ```
 * 
 * Dependencies:
 * - Apache Commons CSV library
 * 
 * Authors:
 * - Abdalrhman Fawzy
 */

package util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import graph.advertismentGraph;
import graph.Graph;

import advertisment.Video;
import advertisment.Ad;

import util.AdLoader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GraphLoader {
    public static Graph loadGraph(Graph g, String fileName) {
        try {
            Reader file = new FileReader(fileName);
            CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            for (CSVRecord currentRec : parser) {
                String videoTitle = currentRec.get("VideoTitle");
                String channelTitle = currentRec.get("ChannelTitle");
                String publishTimeStr = currentRec.get("PublishTime");

                ArrayList<String> tags = new ArrayList<>(Arrays.asList(currentRec.get("Tags").split(",")));
                int views = Integer.parseInt(currentRec.get("Views"));
                int likes = Integer.parseInt(currentRec.get("Likes"));
                int dislikes = Integer.parseInt(currentRec.get("Dislikes"));
                String description = currentRec.get("Description");
                int commentCount = Integer.parseInt(currentRec.get("CommentCount"));

                Date publishTime = dateFormatter.parse(publishTimeStr);

                Video video = new Video(videoTitle, channelTitle, publishTime, tags, views, likes, dislikes, description, commentCount);
                g.addVertex(video);
            }

            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        ((advertismentGraph) g).calculateSimilarityAndAddEdges();
        AdLoader.loadAds(g, "data/Adds_small.csv");
        g.calculateSimilarityAndAddEdgesInAds();

        return g;
    }
    public void openFile(Graph graph) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            // Process the selected file
            // Call the loadGraph() method with the selected file name
            this.loadGraph(graph, fileName);
        }
    }

}
