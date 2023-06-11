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

/**
 * The `AdLoader` class provides utility methods for loading ad data from a CSV file into a graph data structure.
 * @author Abdalrhman Fawzy
 */
public class AdLoader {

    /**
     * Loads ad data from a CSV file into the given graph data structure.
     *
     * @param g        The graph data structure to load the ad data into.
     * @param fileName The name of the CSV file containing the ad data.
     * @return The updated graph with the loaded ad data.
     */
    public static Graph loadAds(Graph g, String fileName) {
        try {
            Reader file = new FileReader(fileName);
            CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord currentRec : parser) {
                int adId = Integer.parseInt(currentRec.get("AdID"));
                String product = currentRec.get("Product");
                String company = currentRec.get("Company");
                String adType = currentRec.get("AdType");
                int duration = Integer.parseInt(currentRec.get("Duration"));
                int views = Integer.parseInt(currentRec.get("Views"));
                int clicks = Integer.parseInt(currentRec.get("Clicks"));
                double ctr = Double.parseDouble(currentRec.get("CTR"));
                double conversionRate = Double.parseDouble(currentRec.get("ConversionRate"));

                String costStr = currentRec.get("Cost");
                costStr = costStr.substring(1); // Remove the first character ('$')
                double cost = Double.parseDouble(costStr);

                String description = currentRec.get("Description");
                String title = currentRec.get("Title");

                 // Extract and sanitize tags
                 String tagsString = currentRec.get("Tags");
                 String[] tagArray = tagsString.split(",");
                 List<String> tags = new ArrayList<>();

                 for (String tag : tagArray) {
                     String sanitizedTag = tag.trim().replace("#", "");
                     tags.add(sanitizedTag);
                 }

                Ad ad = new Ad(adId, product, company, adType, duration, views, clicks, ctr, conversionRate, cost, description, title,  tags);
                g.addAd(ad);
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return g;
    }
}
