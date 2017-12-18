package com.parisventes.bean;

import java.util.List;

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
		id=0;
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
	
	public void setPrice(String price) {
		try {
			this.price = Float.parseFloat(price);
		}
		catch(NumberFormatException e) {
			this.price = 0f;
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException e) {
			this.id = 0;
			System.out.println(e.getLocalizedMessage());
		}
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
	
	public String toHtml(String line, HttpServletRequest request) {
		String[] attributes = line.split("\\|");
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


	
	
	public String findAll(List<String> allLines, HttpServletRequest request){
		String html = new String();
		for(String line : allLines) {
			Article article = this.splitLine(line);
			html += article.toHtml(line, request);
		}
    	return html;
		
	}
	
	public String findById( List<String> allLines, Integer id, HttpServletRequest request){
		for (String line : allLines) {
			Article article = this.splitLine(line);
			System.out.println(article.getId());
			if( article.getId() == id) {
				return article.toHtml(request);
			}
		}
		return "";
	}
	
	
	public Article splitLine(String line) {
		Article a = new Article();
		String[] splitted = line.split("\\|");
		a.setId(splitted[0]);
		a.setTitle(splitted[1]);
		a.setLinkImg(splitted[2]);
		a.setDescription(splitted[3]);
		a.setPrice(splitted[4]);
		return a;
	}
	
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", linkImg=" + linkImg + ", description=" + description + ", price=" + price
				+ "]";
	}

	public static void main(String[] args) {


	}

	
	
}
