package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.business.book.BookNotBlank;
import br.com.talles.ecommercebooks.business.book.Ean13Unique;
import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.CreateView;
import br.com.talles.ecommercebooks.business.book.IsbnUnique;
import br.com.talles.ecommercebooks.business.customer.CustomerNotBlank;
import br.com.talles.ecommercebooks.business.customer.FindCustomer;
import br.com.talles.ecommercebooks.business.customer.PasswordValidate;
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
		// Books
		IStrategy bookNotBlank = new BookNotBlank();
		IStrategy isbnUnique = new IsbnUnique();
		IStrategy ean13Unique = new Ean13Unique();
		// Customers
		IStrategy customerNotBlank = new CustomerNotBlank();
		IStrategy findCostumer = new FindCustomer();
                
        List<IStrategy> listBook = new ArrayList();
		listBook.add(new CreateView());
		
        List<IStrategy> listDisableBook = new ArrayList();
		listDisableBook.add(new CreateView());
		
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
		createBook.add(new CreateView());
		
		List<IStrategy> filtersBook = new ArrayList();
        
		
		List<IStrategy> createCustomer = new ArrayList();
		createCustomer.add(new CreateView());
		
		List<IStrategy> saveCustomer = new ArrayList();
		saveCustomer.add(customerNotBlank);
		
		List<IStrategy> listCustomer = new ArrayList();
		
		
		List<IStrategy> listDisableCustomer = new ArrayList();
		
		
		List<IStrategy> findCustomer = new ArrayList();
		
		
		List<IStrategy> updateCustomer = new ArrayList();
		updateCustomer.add(customerNotBlank);
		
		List<IStrategy> disableCustomer = new ArrayList();
		disableCustomer.add(findCostumer);
		
		List<IStrategy> enableCustomer = new ArrayList();
		enableCustomer.add(findCostumer);
		
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
        contextCustomer.put(SAVE, saveCustomer);
        contextCustomer.put(LIST, listCustomer);
        contextCustomer.put(LIST_DISABLE, listDisableCustomer);
		contextCustomer.put(FIND, findCustomer);
		contextCustomer.put(UPDATE, updateCustomer);
		contextCustomer.put(DISABLE, disableCustomer);
		contextCustomer.put(ENABLE, enableCustomer);
		
        requirements = new HashMap();
        requirements.put(book, contextBook);
        requirements.put(customer, contextCustomer);
        
        persistence = new HashMap();
        persistence.put(book, new BookDao());
        persistence.put(customer, new CustomerDao());

        this.result = new Result();
    }
	 
	@Override
	public Result list(Entity entity, String operation) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(operation);
		
		result.setOperation(operation);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		IDao dao = persistence.get(entity.getClass().getSimpleName());
        result.addEntities(dao.select(operation.equals(LIST)));
        
        return result;
	}
		
	@Override
	public Result save(Entity entity, String operation) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(operation);
		
		result.setOperation(operation);
        result = executeValidations(entity, validations);
        if(result.hasMsg()){
			String msg = result.getMsg();
			
			result = create(entity, CREATE);
			
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
	public Result delete(Entity entity, String operation){
		this.result = new Result();
		
        Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(operation);
		
		result.setOperation(operation);
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
	public Result find(Entity entity, String operation) {
		this.result = new Result();
		
        Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(operation);
		
		result.setOperation(operation);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		// Loads create view datas
		result = create(entity, CREATE);
		result.setOperation(operation);
		
	    IDao dao = persistence.get(entity.getClass().getSimpleName());
	    result.addEntity(dao.find(entity));
		
	    return result;
	}
	
	@Override
    public Result update(Entity entity, String operation) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(operation);
		
        result.setOperation(operation);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;

        IDao dao = persistence.get(entity.getClass().getSimpleName());
        boolean resultDao = dao.update(entity, operation);
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");

        return result;
    }
	
	@Override
	public Result create(Entity entity, String operation){
		this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(operation);
		
		result.setOperation(operation);
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
