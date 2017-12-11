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

import com.parisventes.servlets.Home;


/**
 * Servlet implementation class Article
 */
@WebServlet("/articles")
public class Articles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Articles() {
        super();
    }

    public String readFile(HttpServletRequest request, Integer id) { 
        String html = new String(); 
        try { 
          List<String> allLines = Files.readAllLines(Paths.get(Home.FILENAME)); 
           
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
            }else { 
              html = "Aucun article n'existe avec cet identifiant"; 
            } 
          } 
     
        } catch (IOException e) { 
        	e.printStackTrace();
        } 
        return html;
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> allLines = Home.getAllLines();

		try {
			Integer id   = Integer.parseInt(request.getParameter("id"));
			for (String line : allLines) {
				String[] splitted = line.split("\\|");
				if( Integer.parseInt(splitted[0]) == id) {
					String allHtml = Home.makeHtml(splitted, request);
					request.setAttribute("allHtml", allHtml);	
				}
			}	
		}catch(NumberFormatException e) {
			e.printStackTrace();
			
		}


		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
