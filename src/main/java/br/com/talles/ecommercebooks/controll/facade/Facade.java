package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.business.book.BookNotBlank;
import br.com.talles.ecommercebooks.business.book.Ean13Unique;
import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.CreateView;
import br.com.talles.ecommercebooks.business.book.IsbnUnique;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

	private Map<String, Map<String, List<IStrategy>>> requirements;
    private Map<String, IDao> persistence;
    private Result result;
    
    private static final String LIST = "LIST";
    private static final String LIST_DISABLE = "LIST-DISABLE";
    private static final String SAVE = "SAVE";
    private static final String DELETE = "DELETE";
    private static final String FIND = "FIND";
    private static final String UPDATE = "UPDATE";
    private static final String DISABLE = "DISABLE";
    private static final String ENABLE = "ENABLE";
	private static final String CREATE = "CREATE";
	private static final String FILTERS = "FILTERS";
	
	 public Facade() {
        String book = Book.class.getSimpleName();
        String customer = Customer.class.getSimpleName();
        
        // All Strategies
		IStrategy bookNotBlank = new BookNotBlank();
		IStrategy isbnUnique = new IsbnUnique();
		IStrategy ean13Unique = new Ean13Unique();
                
        List<IStrategy> listBook = new ArrayList();
		listBook.add(new CreateView(LIST));
		
        List<IStrategy> listDisableBook = new ArrayList();
		listDisableBook.add(new CreateView(LIST_DISABLE));
		
        List<IStrategy> saveBook = new ArrayList();
		saveBook.add(bookNotBlank);
		saveBook.add(isbnUnique);
		saveBook.add(ean13Unique);
		
		List<IStrategy> deleteBook = new ArrayList();
		
        
		List<IStrategy> findBook = new ArrayList();
		
		
        List<IStrategy> updateBook = new ArrayList();
		
		
		List<IStrategy> disableBook = new ArrayList();
		
		
		List<IStrategy> enableBook = new ArrayList();
		
		
		List<IStrategy> createBook = new ArrayList();
		createBook.add(new CreateView(CREATE));
		
		List<IStrategy> filtersBook = new ArrayList();
        		
		
		List<IStrategy> createCustomer = new ArrayList();
		createCustomer.add(new CreateView(CREATE));
		
		
        Map<String, List<IStrategy>> contextBook = new HashMap();
        contextBook.put(LIST, listBook);
        contextBook.put(LIST_DISABLE, listDisableBook);
		contextBook.put(SAVE, saveBook);
		contextBook.put(DELETE, deleteBook);
		contextBook.put(FIND, findBook);
        contextBook.put(UPDATE, updateBook);
        contextBook.put(CREATE, createBook);
        contextBook.put(DISABLE, disableBook);
        contextBook.put(ENABLE, enableBook);
        contextBook.put(FILTERS, filtersBook);
		
		Map<String, List<IStrategy>> contextCustomer = new HashMap();
        contextCustomer.put(CREATE, createCustomer);
		
        requirements = new HashMap();
        requirements.put(book, contextBook);
        requirements.put(customer, contextCustomer);
        
        persistence = new HashMap();
        persistence.put(book, new BookDao());
        persistence.put(customer, new CustomerDao());

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
        result.addEntities(dao.select(true));
        
        return result;
	}
	
	@Override
	public Result listDisable(Entity entity) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(LIST_DISABLE);
		
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		IDao dao = persistence.get(entity.getClass().getSimpleName());
        result.addEntities(dao.select(false));
        
        return result;
	}
	
	@Override
	public Result save(Entity entity) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(SAVE);
		
        result = executeValidations(entity, validations);
        if(result.hasMsg()){
			String msg = result.getMsg();
			
			result = create(entity);
			
			result.addEntity(entity);
			result.setMsg(msg);
			
			return result;
		}
		
        IDao dao = persistence.get(entity.getClass().getSimpleName());
        boolean resultDao = dao.save(entity);
        
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");
        
        return result;
	}
	
	@Override
	public Result delete(Entity entity){
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
	public Result disable(Entity entity) {
		this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(DISABLE);
		
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		IDao dao = persistence.get(entity.getClass().getSimpleName());
        boolean resultDao = dao.disable(entity);
		
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");
        
        return result;
	}
	
	@Override
	public Result enable(Entity entity) {
		this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(ENABLE);
		
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		IDao dao = persistence.get(entity.getClass().getSimpleName());        
        boolean resultDao = dao.enable(entity);
		
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
			result = validation.process(entity, result);
			
            if(result.hasMsg()){
                result.setEntity(entity);
                return result;
            }
        }
        
        return result;
    }
	
}
