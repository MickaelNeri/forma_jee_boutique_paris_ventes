package com.parisventes.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parisventes.bean.Article;
import com.parisventes.bean.BDD;


/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String FILENAME = "C:\\Users\\Administrateur\\Desktop\\Formation_POE\\08_JEE\\ParisVentes\\WebContent\\articles.txt";

    public Home() {
        super();
    }

    public List<String> getAllLines(){
		BDD bdd = new BDD(FILENAME);
		List<String> allLines = bdd.readFile();
    	try {
    		allLines = (ArrayList<String>) Files.readAllLines(Paths.get(FILENAME));
    	} catch( IOException e) {
    		e.printStackTrace();
    	}
    	return allLines;
    }
    
    public static String makeHtml(String[] attributes, HttpServletRequest request) {
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
    
    public String makeHtml(Article a, HttpServletRequest request) {
    	String articleHtml = "";
		articleHtml += "<article><h4>";
		articleHtml += a.getTitle();
		articleHtml += "</h4>";
		articleHtml += "<figure>";
		articleHtml += "<a href=\"";
		articleHtml += request.getContextPath() + "/articles?id=";
		articleHtml += a.getId();
		articleHtml += "\"><img src=\"";
		articleHtml += request.getContextPath();
		articleHtml	+= "/img/";
		articleHtml += a.getLinkImg();
		articleHtml += "\"></a>";
		articleHtml += "<figcaption>";
		articleHtml += a.getDescription();
		articleHtml += "</figcaption></figure>";
		articleHtml += "<span>";
		articleHtml += a.getPrice();
		articleHtml += "</span>€";
		articleHtml += "</article>";
    	return articleHtml;

    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List<String> allLines = getAllLines();
		BDD bdd = new BDD(FILENAME);
		List<String> allLines = bdd.readFile();

		Article art = new Article();
		String allHtml = art.findAll(allLines, request);
		request.setAttribute("allHtml", allHtml);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
