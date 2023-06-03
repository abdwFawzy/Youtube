package advertisment;

import java.util.Date;

    /**
    /*    Represents a user's watching history of a video, including the user's ID, video title, watched duration, and date.
    /*    Author: Abdalrhman Fawzy
    */
public class UserWatchingHistory {
    private int userID;
    private String videoTitle;
    private int watchedDuration;
    private Date watchedDate;

    public UserWatchingHistory(int userID, String videoTitle, int watchedDuration, Date watchedDate) {
        this.userID = userID;
        this.videoTitle = videoTitle;
        this.watchedDuration = watchedDuration;
        this.watchedDate = watchedDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public int getWatchedDuration() {
        return watchedDuration;
    }

    public void setWatchedDuration(int watchedDuration) {
        this.watchedDuration = watchedDuration;
    }

    public Date getWatchedDate() {
        return watchedDate;
    }

    public void setWatchedDate(Date watchedDate) {
        this.watchedDate = watchedDate;
    }
}

