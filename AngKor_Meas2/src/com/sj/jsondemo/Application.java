package com.sj.jsondemo;

public class Application {
	private String id;
    private String title;
    private long totalDl;
    private int rating;
    private String icon;
    private String price;
    
	
    
    
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getTotalDl() {
        return totalDl;
    }
    public void setTotalDl(long totalDl) {
        this.totalDl = totalDl;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
}
