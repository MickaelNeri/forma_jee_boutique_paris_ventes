package com.parisventes.bean;

import javax.servlet.http.HttpServletRequest;

public class Article {
	private Integer id;
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

	public String makeHtml(String[] attributes, HttpServletRequest request) {
	    	String articleHtml = "";
			articleHtml += "<article><h4>";
			articleHtml += attributes[1];
			articleHtml += "</h4>";
			articleHtml += "<figure>";
			articleHtml += "<a href=\"";
			articleHtml += request.getContextPath() + "/articles?id=";
			articleHtml += attributes[0];
			articleHtml += "\"><img src=\"";
			articleHtml += request.getContextPath();
			articleHtml	+= "/img/";
			articleHtml += attributes[2];
			articleHtml += "\"></a>";
			articleHtml += "<figcaption>";
			articleHtml += attributes[3];
			articleHtml += "</figcaption></figure>";
			articleHtml += "<span>";
			articleHtml += attributes[4];
			articleHtml += "</span>€";
			articleHtml += "</article>";
	    	return articleHtml;
	 }
	
	public String toHtml(HttpServletRequest request) {
		String articleHtml = "";
		articleHtml += "<article><h4>";
		articleHtml += title;
		articleHtml += "</h4>";
		articleHtml += "<figure>";
		articleHtml += "<a href=\"";
		articleHtml += request.getContextPath() + "/articles?id=";
		articleHtml += id;
		articleHtml += "\"><img src=\"";
		articleHtml += request.getContextPath();
		articleHtml	+= "/img/";
		articleHtml += linkImg;
		articleHtml += "\"></a>";
		articleHtml += "<figcaption>";
		articleHtml += description;
		articleHtml += "</figcaption></figure>";
		articleHtml += "<span>";
		articleHtml += price;
		articleHtml += "</span>€";
		articleHtml += "</article>";
    	return articleHtml;
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", linkImg=" + linkImg + ", description=" + description + ", price=" + price
				+ "]";
	}

	public static void main(String[] args) {


	}

	
	
}
