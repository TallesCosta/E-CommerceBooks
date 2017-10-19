package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Author;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.book.Category;
import br.com.talles.ecommercebooks.domain.book.ChangeStatus;
import br.com.talles.ecommercebooks.domain.book.StatusCategory;
import br.com.talles.ecommercebooks.domain.book.Dimension;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.History;
import br.com.talles.ecommercebooks.domain.book.PriceGroup;
import br.com.talles.ecommercebooks.domain.book.PublishingCompany;
import br.com.talles.ecommercebooks.domain.book.SaleParameterization;
import br.com.talles.ecommercebooks.domain.customer.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		
		String idDimensionS = request.getParameter("idDimension");
		long idDimension = 0L;
		if (!(idDimensionS == null || idDimensionS.equals("")))
			idDimension = Long.valueOf(idDimensionS);
		
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
		
		String idSaleParameterizationS = request.getParameter("idSaleParameterization");
		long idSaleParameterization = 0L;
		if (!(idSaleParameterizationS == null || idSaleParameterizationS.equals("")))
			idSaleParameterization = Long.valueOf(idSaleParameterizationS);
		
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
			if (!(idCategoryS == null || idCategoryS.equals("")))
				idCategory = Long.valueOf(idCategoryS);
			categories.add(new Category(idCategory));
		}
		
		if (categories.isEmpty()) {
			categories.add(new Category(0L));
		}
		
		// ChangeStatus (Save)
		String justification = request.getParameter("justification");
		
		// ChangeStatus (Update)
		String idChangeStatusS = request.getParameter("idChangeStatus");
		long idChangeStatus = 0L;
		if (!(idChangeStatusS == null || idChangeStatusS.equals("")))
			idChangeStatus = Long.valueOf(idChangeStatusS);
		
		String justificationChangeStatus = request.getParameter("justificationChangeStatus");
		
		String idStatusCategoryChangeStatusS = request.getParameter("idStatusCategoryChangeStatus");
		long idStatusCategoryChangeStatus = 0L;
		if (!(idStatusCategoryChangeStatusS == null || idStatusCategoryChangeStatusS.equals("")))
			idStatusCategoryChangeStatus = Long.valueOf(idStatusCategoryChangeStatusS);
		
		// DeactivationCategory
		String idDeactivationCategoryS = request.getParameter("deactivationCategory");
		long idDeactivationCategory = 0;
		if (!(idDeactivationCategoryS == null || idDeactivationCategoryS.equals("")))
			idDeactivationCategory = Long.valueOf(idDeactivationCategoryS);
		
		// ActivationCategory		
		String idActivationCategoryS = request.getParameter("activationCategory");
		long idActivationCategory = 0;
		if (!(idActivationCategoryS == null || idActivationCategoryS.equals("")))
			idActivationCategory = Long.valueOf(idActivationCategoryS);
		
		// History
		// Fake user - (TEMP)
		String idUserOnS = "1";
		long idUserOn = 0;
		if (!(idUserOnS == null || idUserOnS.equals("")))
			idUserOn = Long.valueOf(idUserOnS);
		
		// Book
		Book book = new Book();
		
		switch(request.getParameter("operation")) {
			case "SAVE":				
				// Book
				book.setTitle(title);
				book.setSynopsis(synopsis);
				book.setPublicationYear(publicationYear);
				book.setNumberOfPages(numberOfPages);
				book.setEdition(edition);
				book.setIsbn(isbn);
				book.setEan13(ean13);
				book.setHistory(new History(new Date(), new User(idUserOn), book));
				// Dimension
				book.setDimension(new Dimension(height, widht, weight, depth));
				// Sale Parameterization
				book.setSaleParameterization(new SaleParameterization(minSaleLimit, periodicity));
				// Author
				book.setAuthor(new Author(idAuthor));
				// Publishing Company
				book.setPublishingCompany(new PublishingCompany(idPublishingCompany));
				// Price Group
				book.setPriceGroup(new PriceGroup(idPriceGroup));
				// Change Status
				book.setChangeStatus(new ChangeStatus("Livro novo", new StatusCategory(-1L)));
				// Categories
				book.addCategories(categories);
				break;

			case "LIST":
				// Book
				book.setTitle(title);
				book.setSynopsis(synopsis);
				book.setPublicationYear(publicationYear);
				book.setNumberOfPages(numberOfPages);
				book.setEdition(edition);
				book.setIsbn(isbn);
				book.setEan13(ean13);
				// Dimension
				book.setDimension(new Dimension(height, widht, weight, depth));
				// Sale Parameterization
				book.setSaleParameterization(new SaleParameterization(minSaleLimit, periodicity));
				// Author
				book.setAuthor(new Author(idAuthor));
				// Publishing Company
				book.setPublishingCompany(new PublishingCompany(idPublishingCompany));
				// Price Group
				book.setPriceGroup(new PriceGroup(idPriceGroup));
				// Categories
				book.addCategories(categories);
				break;

			case "LIST-DISABLE":
				// Book
				book.setTitle(title);
				book.setSynopsis(synopsis);
				book.setPublicationYear(publicationYear);
				book.setNumberOfPages(numberOfPages);
				book.setEdition(edition);
				book.setIsbn(isbn);
				book.setEan13(ean13);
				// Dimension
				book.setDimension(new Dimension(height, widht, weight, depth));
				// Sale Parameterization
				book.setSaleParameterization(new SaleParameterization(minSaleLimit, periodicity));
				// Author
				book.setAuthor(new Author(idAuthor));
				// Publishing Company
				book.setPublishingCompany(new PublishingCompany(idPublishingCompany));
				// Price Group
				book.setPriceGroup(new PriceGroup(idPriceGroup));
				// Categories
				book.addCategories(categories);
				break;
				
			case "FIND":
				// Book
				book.setId(id);
				break;

			case "HISTORY":
				// Book
				book.setId(id);
				break;
				
			case "UPDATE":				
				// Book
				book.setId(id);
				book.setTitle(title);
				book.setSynopsis(synopsis);
				book.setPublicationYear(publicationYear);
				book.setNumberOfPages(numberOfPages);
				book.setEdition(edition);
				book.setIsbn(isbn);
				book.setEan13(ean13);
				book.setHistory(new History(new Date(), new User(idUserOn)));
				// Dimension
				book.setDimension(new Dimension(height, widht, weight, depth, idDimension));
				// Sale Parameterization
				book.setSaleParameterization(new SaleParameterization(minSaleLimit, periodicity, 
						idSaleParameterization));
				// Author
				book.setAuthor(new Author(idAuthor));
				// Publishing Company
				book.setPublishingCompany(new PublishingCompany(idPublishingCompany));
				// Price Group
				book.setPriceGroup(new PriceGroup(idPriceGroup));
				// Change Status
				book.setChangeStatus(new ChangeStatus(justificationChangeStatus, 
						new StatusCategory(idStatusCategoryChangeStatus), idChangeStatus));
				// Categories
				book.addCategories(categories);
				break;

			case "DISABLE":
				// Book
				book.setId(id);
				book.setEnabled(false);
				book.setChangeStatus(new ChangeStatus(justification, new StatusCategory(idDeactivationCategory)));
				break;

			case "ENABLE":
				// Book
				book.setId(id);
				book.setEnabled(true);
				book.setChangeStatus(new ChangeStatus(justification, new StatusCategory(idActivationCategory)));
				break;
				
			case "DELETE":
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
				case "CREATE" :
					dispatcher = request.getRequestDispatcher("/book/create.jsp");
					dispatcher.forward(request, response);
					break;
					
				case "SAVE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/books/list?operation=LIST");
					} else {
						dispatcher = request.getRequestDispatcher("/book/create.jsp");
						dispatcher.forward(request, response);
					}					
					break;

				case "LIST":
					dispatcher = request.getRequestDispatcher("/book/list.jsp");
					dispatcher.forward(request, response);
					break;

				case "LIST-DISABLE":
					dispatcher = request.getRequestDispatcher("/book/list-disable.jsp");
					dispatcher.forward(request, response);
					break;

				case "FIND":
					dispatcher = request.getRequestDispatcher("/book/create.jsp");
					dispatcher.forward(request, response);
					break;
					
				case "HISTORY":
					dispatcher = request.getRequestDispatcher("/book/show.jsp");
					dispatcher.forward(request, response);
					break;

				case "UPDATE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/books/list?operation=LIST");
					} else {
						dispatcher = request.getRequestDispatcher("/book/create.jsp");
						dispatcher.forward(request, response);
					}
					break;

				case "DISABLE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/books/list?operation=LIST");
					} else {
						dispatcher = request.getRequestDispatcher("/book/list.jsp");
						dispatcher.forward(request, response);
					}
					break;

				case "ENABLE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/books/list-disable?operation=LIST-DISABLE");
					} else {
						dispatcher = request.getRequestDispatcher("/book/list-disable.jsp");
						dispatcher.forward(request, response);
					}
					break;					
					
				case "DELETE":
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
