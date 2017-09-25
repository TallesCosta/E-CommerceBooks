package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.SelectPublishingCompany;
import br.com.talles.ecommercebooks.business.AuthorNotBlank;
import br.com.talles.ecommercebooks.business.BookNotBlank;
import br.com.talles.ecommercebooks.business.CategoryNotBlank;
import br.com.talles.ecommercebooks.business.DimensionNotBlank;
import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.PriceGroupNotBlank;
import br.com.talles.ecommercebooks.business.PublishingCompanyNotBlank;
import br.com.talles.ecommercebooks.business.SaleParameterizationNotBlank;
import br.com.talles.ecommercebooks.business.view.SelectAuthor;
import br.com.talles.ecommercebooks.business.view.SelectCategory;
import br.com.talles.ecommercebooks.business.view.SelectPriceGroup;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

	private Map<String, Map<String, List<IStrategy>>> requirements;
    private Map<String, IDao> persistence;
    private Result result;
    
    private static final String LIST = "LIST";
    private static final String SAVE = "SAVE";
    private static final String DELETE = "DELETE";
    private static final String FIND = "FIND";
    private static final String UPDATE = "UPDATE";
	private static final String CREATE = "CREATE";
	private static final String FILTERS = "FILTERS";
	
	 public Facade() {
        String book = Book.class.getSimpleName();
        
        // All Strategies
        IStrategy selectCategory = new SelectCategory();
		IStrategy selectAuthor = new SelectAuthor();
		IStrategy selectPublishingCompany = new SelectPublishingCompany();
		IStrategy selectPriceGroup = new SelectPriceGroup();		
		IStrategy bookNotBlank = new BookNotBlank();
		IStrategy authorNotBlank = new AuthorNotBlank();
		IStrategy priceGroupNotBlank = new PriceGroupNotBlank();
		IStrategy categoryNotBlank = new CategoryNotBlank();
		IStrategy publishingCompanyNotBlank = new PublishingCompanyNotBlank();
		IStrategy dimensionNotBlank = new DimensionNotBlank();
		IStrategy saleParameterizationNotBlank = new SaleParameterizationNotBlank();
                
        List<IStrategy> listBook = new ArrayList();
		listBook.add(selectCategory);
		listBook.add(selectAuthor);
		listBook.add(selectPublishingCompany);
		listBook.add(selectPriceGroup);
		
        List<IStrategy> saveBook = new ArrayList();
		saveBook.add(bookNotBlank);
		saveBook.add(authorNotBlank);
		saveBook.add(priceGroupNotBlank);
		saveBook.add(categoryNotBlank);
		saveBook.add(publishingCompanyNotBlank);
		saveBook.add(dimensionNotBlank);
		saveBook.add(saleParameterizationNotBlank);
		
		List<IStrategy> deleteBook = new ArrayList();
		
        
        List<IStrategy> updateBook = new ArrayList();
		
		
		List<IStrategy> createBook = new ArrayList();
		createBook.add(selectCategory);
		createBook.add(selectAuthor);
		createBook.add(selectPublishingCompany);
		createBook.add(selectPriceGroup);
		
		List<IStrategy> filtersBook = new ArrayList();
        
		
        Map<String, List<IStrategy>> contextBook = new HashMap();
        contextBook.put(LIST, listBook);
		contextBook.put(SAVE, saveBook);
		contextBook.put(DELETE, deleteBook);
        contextBook.put(UPDATE, updateBook);
        contextBook.put(CREATE, createBook);
        contextBook.put(FILTERS, filtersBook);
		
        requirements = new HashMap();
        requirements.put(book, contextBook);
        
        persistence = new HashMap();
        persistence.put(book, new BookDao());

        this.result = new Result();
    }
	 
	@Override
	public Result list(Entity entity) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(LIST);
		
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		IDao dao = persistence.get(entity.getClass().getSimpleName());
        result.addEntities(dao.select());
        
        return result;
	}
	
	@Override
	public Result save(Entity entity) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(SAVE);
		
        result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
        IDao dao = persistence.get(entity.getClass().getSimpleName());
        boolean resultDao = dao.save(entity);
        
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");
        
        return result;
	}

	@Override
	public Result delete(Entity entity) {
		this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(DELETE);
		
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		IDao dao = persistence.get(entity.getClass().getSimpleName());        
        boolean resultDao = dao.delete(entity);
		
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");
        
        return result;
	}
	
	@Override
	public Result find(Entity entity) {
		this.result = new Result();
		
        Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(FIND);
		
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
				
	    IDao dao = persistence.get(entity.getClass().getSimpleName());
	    result.setEntity(dao.find(entity));
		
	    return result;
	}
	
	@Override
    public Result update(Entity entity) {
        this.result = new Result();
        
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(UPDATE);
		
        //result.setEntity(entity);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;

        IDao dao = persistence.get(entity.getClass().getSimpleName());
        boolean resultDao = dao.update(entity);
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");

        return result;
    }
	
	@Override
	public Result create(Entity entity){
		this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(CREATE);
		
		result = executeValidations(entity, validations);		
		return result;
	}
	
	public Result executeValidations(Entity entity, List<IStrategy> validations) {
        
        for(IStrategy validation : validations){
			// TODO: USAR ESTE CÓDIGO NAS STRATEGIES!!!
            //result.addMsg();
			
			validation.process(entity, result);
            if(result.hasMsg()){
                result.setEntity(entity);
                return result;
            }
        }
        
        return result;
    }
	
}
