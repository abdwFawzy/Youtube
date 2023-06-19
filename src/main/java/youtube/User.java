package youtube;

import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

/**
 * Represents a user of YouTube.
 * A user can subscribe to YouTube channels and receive notifications about new videos and channel updates.
 * 
 * @Author Abdalrhman Fawzy
 * @Date 6/19/2023
 */
public class User implements Observer {
    private String username;
    private String email;
    private List<YouTubeChannel> subscriptions;

    /**
     * Constructs a new User instance with the given username and email.
     *
     * @param username The username of the user.
     * @param email    The email address of the user.
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.subscriptions = new ArrayList<>();
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the list of YouTube channels that the user is subscribed to.
     *
     * @return The list of YouTube channels subscribed by the user.
     */
    public List<YouTubeChannel> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Subscribes the user to a YouTube channel.
     * This method adds the specified channel to the user's subscription list
     * and registers the user as an observer of the channel to receive notifications.
     *
     * @param channel The YouTube channel to subscribe to.
     */
    public void subscribeToChannel(YouTubeChannel channel) {
        subscriptions.add(channel);
        channel.addObserver(this);
    }

    /**
     * Unsubscribes the user from a YouTube channel.
     * This method removes the specified channel from the user's subscription list
     * and unregisters the user as an observer of the channel.
     *
     * @param channel The YouTube channel to unsubscribe from.
     */
    public void unsubscribeFromChannel(YouTubeChannel channel) {
        subscriptions.remove(channel);
        channel.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof YouTubeChannel) {
            YouTubeChannel channel = (YouTubeChannel) o;
            // Handle the update here
            System.out.println("Notification received for channel: " + channel.getName());
            System.out.println("New Video was uploded see now!: " + arg);
        }
    }
}
