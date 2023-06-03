package advertisment;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents a video object with various properties and methods related to video content.
 * @author Abdalrhman fawzy
 */
public class Video implements Comparable<Video>{
    private String videoTitle;
    private String channelTitle;
    private Date publishTime;
    private List<String> tags;
    private int views;
    private int likes;
    private int dislikes;
    private String description;
    private int commentCount;

    public Video(String videoTitle, String channelTitle, Date publishTime) {
        this.videoTitle = videoTitle;
        this.channelTitle = channelTitle;
        this.publishTime = publishTime;
        this.tags = new ArrayList<>();
    }

    public Video(String videoTitle, String channelTitle, Date publishTime, List<String> tags, int views,
            int likes, int dislikes, String description, int commentCount) {
        this.videoTitle = videoTitle;
        this.channelTitle = channelTitle;
        this.publishTime = publishTime;
        this.tags = tags;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.description = description;
        this.commentCount = commentCount;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public int hashCode() {
        return videoTitle.hashCode();
    }

    @Override
    public int compareTo(Video other) {
        return videoTitle.compareTo(other.videoTitle);
    }

    /**
     * Retrieves a set of attributes associated with the video, including words from the video title,
     * description, and tags.
     *
     * @return A set of attributes derived from the video's title, description, and tags.
     */
    public Set<String> getAttributes() {
        Set<String> attributes = new HashSet<>();

        // Extract words from video title
        String[] titleArray = videoTitle.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        for (String title : titleArray) {
            attributes.add(title);
        }

        // Add video title as a separate attribute
        // attributes.add(videoTitle);

        // Extract words from description
        String[] descArray = description.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        for (String currentDesc : descArray) {
            attributes.add(currentDesc);
        }

        // Add tags as attributes
        // Add tags as attributes (converted to lowercase)
        for (String tag : tags) {
            attributes.add(tag.toLowerCase());
        }

        return attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Video other = (Video) obj;
        return videoTitle.equals(other.videoTitle) &&
                channelTitle.equals(other.channelTitle) &&
                publishTime.equals(other.publishTime) &&
                tags.equals(other.tags) &&
                views == other.views &&
                likes == other.likes &&
                dislikes == other.dislikes &&
                description.equals(other.description) &&
                commentCount == other.commentCount;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Video Title: ").append(videoTitle);
        return sb.toString();
    }

}

