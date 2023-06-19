
package youtube;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import advertisment.Video;

/**
 * Represents a YouTube channel.
 * Extends Observable to make it observable by observers.
 * 
 * @Author Abdalrhman Fawzy
 * @Date 6/19/2023
 */
public class YouTubeChannel extends Observable {
    private String name;
    private User creator;
    private List<Video> videos;

    /**
     * Initializes a YouTubeChannel instance with a given name and creator.
     *
     * @param name    The name of the YouTube channel.
     * @param creator The creator/owner of the YouTube channel.
     */
    public YouTubeChannel(String name, User creator) {
        this.name = name;
        this.creator = creator;
        this.videos = new ArrayList<>();
    }

    /**
     * Returns the name of the YouTube channel.
     *
     * @return The name of the YouTube channel.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the creator/owner of the YouTube channel.
     *
     * @return The creator/owner of the YouTube channel.
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Returns the number of subscribers to the YouTube channel.
     *
     * @return The number of subscribers to the YouTube channel.
     */
    public int getSubscriberCount() {
        return countObservers();
    }

    /**
     * Returns the list of videos uploaded to the YouTube channel.
     *
     * @return A list of videos uploaded to the YouTube channel.
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     * Increases the subscriber count of the YouTube channel by 1.
     */
    public void addSubscriber(User user) {
        addObserver(user);
    }

    /**
     * Uploads a video to the YouTube channel.
     *
     * @param video The video to be uploaded.
     */
    public void uploadVideo(Video video) {
        videos.add(video);
        
        // Notify observers of the new video uploaded
        setChanged();
        notifyObservers(video);
    }
}
