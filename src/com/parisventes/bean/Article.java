package com.parisventes.bean;

public class Article {
	private String title;
	private String linkImg;
	private String description;
	private Float  price;
	
	
	
	public Article() {
		title="";
		linkImg="";
		description="";
		price=0f;
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", linkImg=" + linkImg + ", description=" + description + ", price=" + price
				+ "]";
	}

	public static void main(String[] args) {


	}

	
	
}
