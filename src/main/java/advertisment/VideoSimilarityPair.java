package advertisment;

public class VideoSimilarityPair {
    private Video video;
    private double similarity;

    public VideoSimilarityPair(Video video, double similarity) {
        this.video = video;
        this.similarity = similarity;
    }

    public Video getVideo() {
        return video;
    }

    public double getSimilarity() {
        return similarity;
    }

    @Override
    public String toString() {
        return "VideoSimilarityPair{" +
                "video=" + video +
                ", similarity=" + similarity +
                '}';
    }
}
