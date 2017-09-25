package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Author;
import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Category;
import br.com.talles.ecommercebooks.domain.Dimension;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.PriceGroup;
import br.com.talles.ecommercebooks.domain.PublishingCompany;
import br.com.talles.ecommercebooks.domain.SaleParameterization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		long id = 0L;
		if (!(idS == null || idS.equals("")))
			id = Long.valueOf(idS);
		
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
		
		String publicationYearS = request.getParameter("publicationYear");
		int publicationYear = 0;
		if (!(publicationYearS == null || publicationYearS.equals("")))
			publicationYear = Integer.valueOf(publicationYearS);
		
		String numberOfPagesS = request.getParameter("numberOfPages");
		int numberOfPages = 0;
		if (!(numberOfPagesS == null || numberOfPagesS.equals("")))
			numberOfPages = Integer.valueOf(numberOfPagesS);
		
        String edition = request.getParameter("edition");
        String isbn = request.getParameter("isbn");
        String ean13 = request.getParameter("ean13");
		
		// Dimension datas
		String heightS = request.getParameter("height");
		double height = 0.0;
		if (!(heightS == null || heightS.equals("")))
			height = Double.valueOf(heightS);
			
		String widhtS = request.getParameter("widht");
		double widht = 0.0;
		if (!(widhtS == null || widhtS.equals("")))
			widht = Double.valueOf(widhtS);
		
		String weightS = request.getParameter("weight");
		double weight = 0.0;
		if (!(weightS == null || weightS.equals("")))
			weight = Double.valueOf(weightS);
		
		String depthS = request.getParameter("depth");
		double depth = 0.0;
		if (!(depthS == null || depthS.equals("")))
			depth = Double.valueOf(depthS);
		
		// SaleParameterization datas
		String minSaleLimitS = request.getParameter("minSaleLimit");
		int minSaleLimit = 0;
		if (!(minSaleLimitS == null || minSaleLimitS.equals("")))
			minSaleLimit = Integer.valueOf(minSaleLimitS);
		
		String periodicityS = request.getParameter("periodicity");
		int periodicity = 0;
		if (!(periodicityS == null || periodicityS.equals(""))){
			periodicity = Integer.valueOf(periodicityS);
			
			// Convert any time to minute
			periodicity = convertToMinute(periodicity, request.getParameter("classifierPeriod"));
		}
		
		// Author data
		String idAuthorS = request.getParameter("author");
		long idAuthor = 0L;
		if (!(idAuthorS == null || idAuthorS.equals("")))
			idAuthor = Long.valueOf(idAuthorS);
		
		// PublishingCompany data
		String idPublishingCompanyS = request.getParameter("publishingCompany");
		long idPublishingCompany = 0L;
		if (!(idPublishingCompanyS == null || idPublishingCompanyS.equals("")))
			idPublishingCompany = Long.valueOf(idPublishingCompanyS);
		
		// PriceGroup data
		String idPriceGroupS = request.getParameter("priceGroup");
		long idPriceGroup = 0L;
		if (!(idPriceGroupS == null || idPriceGroupS.equals("")))
			idPriceGroup = Long.valueOf(idPriceGroupS);
		
		// Category data
		List<String> idCategoriesS = new ArrayList<>();
		if(request.getParameterValues("category") != null)
			idCategoriesS = Arrays.asList(request.getParameterValues("category"));
		
		List<Category> categories = new ArrayList<>();
		long idCategory = 0L;
		for(String idCategoryS : idCategoriesS){
			idCategory = Long.valueOf(idCategoryS);
			categories.add(new Category(idCategory));
		}
		
		/*String idCategoryS = request.getParameter("category");
		long idCategory = 0;
		if (!(idCategoryS == null || idCategoryS.equals("")))
			idCategory = Long.valueOf(idCategoryS);*/
		
		Book book = new Book();
		
		switch(request.getParameter("operation")) {
			case "SAVE":
				book.setTitle(title);
				book.setSynopsis(synopsis);
				book.setPublicationYear(publicationYear);
				book.setNumberOfPages(numberOfPages);
				book.setEdition(edition);
				book.setIsbn(isbn);
				book.setEan13(ean13);
				book.setDimension(new Dimension(height, widht, weight, depth));
				book.setSaleParameterization(new SaleParameterization(minSaleLimit, periodicity));
				book.setAuthor(new Author(idAuthor));
				book.setPublishingCompany(new PublishingCompany(idPublishingCompany));
				book.setPriceGroup(new PriceGroup(idPriceGroup));
				book.addCategories(categories);
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
					response.sendRedirect("/E-CommerceBooks/books/list?operation=LIST");
					break;

				case "LIST":
					dispatcher = request.getRequestDispatcher("/list.jsp");
					dispatcher.forward(request, response);
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
