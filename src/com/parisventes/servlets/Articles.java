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
import com.parisventes.servlets.Home;


/**
 * Servlet implementation class Article
 */
@WebServlet("/articles")
public class Articles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String FILENAME = "C:\\Users\\Administrateur\\Desktop\\Formation_POE\\08_JEE\\ParisVentes\\WebContent\\articles.txt";


    public Articles() {
        super();
    }

	public String readFile(HttpServletRequest request, Integer id) {
		String html = new String();
		// List<String> allLines = Files.readAllLines(Paths.get(Home.FILENAME));
		BDD bdd = new BDD(Home.FILENAME);
		List<String> allLines = bdd.readFile();
		for (int i = 0; i < allLines.size(); i++) {

			String[] arr = allLines.get(i).split("\\|");
			Integer tabId = 0;
			try {
				tabId = Integer.parseInt(arr[0]);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			if (tabId == id) {
				html = "<article><h4>";
				html += arr[1] + "</h4><figure><img src=\"";
				html += request.getContextPath() + "/img/" + arr[2] + "\" alt=\"\"><figcaption>";
				html += arr[3] + "</figcaption></figure><span>";
				html += arr[4] + "</span></article>";
				return html;
			} else {
				html = "Aucun article n'existe avec cet identifiant";
			}
		}

		return html;
	}
    
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BDD bdd = new BDD(FILENAME);
		List<String> allLines = bdd.readFile();

		Article art = new Article();
		Integer id = 0;
		try {
			  id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			e.printStackTrace();		
		}
		String allHtml = art.findById(allLines, id, request);
		request.setAttribute("allHtml", allHtml);	

		this.getServletContext().getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
