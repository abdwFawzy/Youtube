package advertisment;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
/**
/*Represents an advertisement object with various properties and methods related to ad content.
/*Author: Abdalrhman Fawzy
*/
public class Ad {
    private int adID;
    private String product;
    private String company;
    private String adType;
    private int duration;
    private int views;
    private int clicks;
    private double ctr;
    private double conversionRate;
    private double cost;
    private String description;
    private String title;
    private ArrayList<String> tags;

    public Ad(int adId, String adTitle, String advertiser,
              int adDuration, int adViews, int adLikes, double adLikeRate,
              double adDislikeRate, double adPrice, String adDescription, List<String> adTags) {
        this.adID = adId;
        this.title = adTitle;
        this.description = adDescription;
        this.company = advertiser;
        this.duration = adDuration;
        this.views = adViews;
        this.clicks = adLikes;
        this.ctr = adLikeRate;
        this.conversionRate = adDislikeRate;
        this.cost = adPrice;
        this.tags = new ArrayList<>(adTags);
    }

    public Ad(int adID, String product, String company, String adType, int duration) {
        this.adID = adID;
        this.product = product;
        this.company = company;
        this.adType = adType;
        this.duration = duration;
        this.tags = new ArrayList<>();
    }

    // Getters and setters for the class variables...

    /**
     * Retrieves a set of attributes associated with the advertisement, including words from the ad's title,
     * description, and tags.
     *
     * @return A set of attributes derived from the advertisement's title, description, and tags.
     */
    public Set<String> getAttributes() {
        Set<String> attributes = new HashSet<>();

        if (title != null) {
            String[] titleArray = title.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            for (String titleWord : titleArray) {
                attributes.add(titleWord);
            }
            attributes.add(title);
        }

//        if (product != null) {
//            String[] productArray = product.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
//            for (String productWord : productArray) {
//                attributes.add(productWord);
//            }
//        }
//
//        if (description != null) {
//            String[] descArray = description.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
//            for (String descWord : descArray) {
//                attributes.add(descWord);
//            }
//        }

        if (tags != null) {
            // Add tags as attributes (converted to lowercase)
            for (String tag : tags) {
                attributes.add(tag.toLowerCase());
            }
        }

        return attributes;
    }

    public int getAdID() {
        return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

}
