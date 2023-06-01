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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GraphLoader {
    public Graph loadGraph(Graph g, String fileName) {
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
                g.addVertex(video.hashCode());
            }

            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

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
