
package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Entity;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Book datas
		String idS = request.getParameter("id");
		Long id;
		if(idS != null)
			id = new Long(idS);
		
        String title = request.getParameter("title");
		
		String publicationYearS = request.getParameter("publicationYear");
		int publicationYear = 0;
		if (publicationYearS != null)
			publicationYear = Integer.valueOf("publicationYearS");
		
		String	numberOfPagesS = request.getParameter("numberOfPages");
		int numberOfPages = 0;
		if (numberOfPagesS != null)
			numberOfPages = Integer.valueOf("numberOfPagesS");
		
        String edition = request.getParameter("edition");
        String synopsis = request.getParameter("synopsis");
        String isbn = request.getParameter("isbn");
        String ean13 = request.getParameter("ean13");
		
		// Dimension datas
		String heightS = request.getParameter("height");
		double height = 0.0;
		if(heightS != null)
			height = Double.valueOf(heightS);
			
		String widhtS = request.getParameter("widht");
		double widht = 0.0;
		if(widhtS != null)
			widht = Double.valueOf(widhtS);
		
		String weightS = request.getParameter("weight");
		double weight = 0.0;
		if(weightS != null)
			weight = Double.valueOf(weightS);
		
		String depthS = request.getParameter("depth");
		double depth = 0.0;
		if(depthS != null)
			depth = Double.valueOf(depthS);
		
		// SaleParameterization
		String minSaleLimitS = request.getParameter("minSaleLimit");
		int minSaleLimit = 0;
		if (minSaleLimitS != null)
			minSaleLimit = Integer.valueOf("minSaleLimitS");
		
		String periodicityS = request.getParameter("periodicity");
		int periodicity = 0;
		if (periodicityS != null)
			periodicity = Integer.valueOf("periodicityS");
		
		// Convert any time to minute
		periodicity = convertToMinute(periodicity, request.getParameter("classifierPeriod"));
		
		Book book = new Book();
		
		switch(request.getParameter("operation")) {
			case "SAVE":

				break;

			case "LIST":

				break;

			case "DELETE":

				break;

			case "FIND":

				break;

			case "UPDATE":

				break;


			case "CREATE" :
				
				break;
		}
		
		return book;
	}

	@Override
	public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
		
		RequestDispatcher dispatcher;
		request.setAttribute("result", result);
		
		try {
			switch(request.getParameter("operation")) {
				case "SAVE":

					break;

				case "LIST":

					break;

				case "DELETE":

					break;

				case "FIND":

					break;

				case "UPDATE":

					break;


				case "CREATE" :
					dispatcher = request.getRequestDispatcher("/create.jsp");
					dispatcher.forward(request, response);
					break;
			}
		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
			
	}
	
	private int convertToMinute(int periody, String classifierPeriod){
		
		if (periody == 0)
			return periody;
		
		int minutes;
		
		switch(classifierPeriod){
			case "H":
				minutes = periody * 60;
				break;
				
			case "D":
				minutes = periody * 60 * 24;
				break;
				
			case "M":
				minutes = periody * 60 * 24 * 30;
				break;
				
			case "Y":
				minutes = periody * 60 * 24 * 365;
				break;
				
			case "m":
			default:
				minutes = periody;
				break;
		}
		
		return minutes;
	}
	
}
