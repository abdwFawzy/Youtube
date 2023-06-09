package graph;
 
import advertisment.Video;
import advertisment.Ad;
import java.util.List;
import java.util.HashSet;
/**
 * The GraphAnalyzer interface provides methods for analyzing and processing graphs.
 * @author Abdalrhman Fawzy
 */
public interface GraphAnalyzer {

    /**
     * Retrieves the Ego Network (Egonet) of a specified center node in the graph.
     *
     * @param center The center node of the Egonet.
     * @return A Graph object representing the Egonet of the center node.
     */
    public Graph getEgonet(int center);

    /**
     * Retrieves a list of Strongly Connected Components (SCCs) in the graph.
     *
     * @return A List of Graph objects representing the Strongly Connected Components in the graph.
     */
    public List<Graph> getSCCs();


    /**
     * Retrieves a list of similar videos based on the provided ad.
     *
     * @param ad The ad object used to determine the similarity of videos.
     * @return A list of videos that are similar to the ad.
     */
     public List<Video> getSimilarVideos(Ad ad);   

    /**
     * Retrieves a list of videos that are similar to the given video based on a similarity threshold.
     * The implementation of this method should perform a search algorithm, such as breadth-first search (BFS),
     * on a graph or data structure representing the relationships between videos.
     *
     * @param video                The video for which similar videos are retrieved.
     * @param similarityThreshold  The similarity threshold for considering a video as similar.
     *                             Only videos with similarity scores above this threshold will be included in the result.
     * @return A list of videos that are similar to the given video.
     */
     public  List<Video> retrieveSimilarVideos(Video video, double similarityThreshold);

     /**
     * Retrieves the set of ads associated with the given video.
     *
     * @param video The video for which to retrieve the associated ads.
     * @return A HashSet of Ad objects associated with the given video.
     */
    public HashSet<Ad> retrieveAdsForVideo(Video video);
}

