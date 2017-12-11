package com.parisventes.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
