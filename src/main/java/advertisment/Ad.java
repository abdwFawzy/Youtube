package advertisment;
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

    public Ad(int adID, String product, String company, String adType, int duration) {
        this.adID = adID;
        this.product = product;
        this.company = company;
        this.adType = adType;
        this.duration = duration;
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
}

