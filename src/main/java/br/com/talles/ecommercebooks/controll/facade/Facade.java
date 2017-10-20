package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.CreateView;
import br.com.talles.ecommercebooks.business.InsertHistory;
import br.com.talles.ecommercebooks.business.UpdateHistory;
import br.com.talles.ecommercebooks.business.book.ModifyStatus;
import br.com.talles.ecommercebooks.business.book.save.BookValidateSave;
import br.com.talles.ecommercebooks.business.book.update.BookValidateUpdate;
import br.com.talles.ecommercebooks.business.customer.FindCustomer;
import br.com.talles.ecommercebooks.business.customer.save.CustomerValidateSave;
import br.com.talles.ecommercebooks.business.customer.update.CustomerValidateUpdate;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.HistoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

	private Map<String, Map<String, List<IStrategy>>> requirements;
	private Map<String, Map<String, List<IStrategy>>> requirementsLater;
    private Map<String, Map<String, IDao>> persistence;
    private Result result;
    
	private static final String CREATE = "CREATE";
    private static final String SAVE = "SAVE";
    private static final String LIST = "LIST";
    private static final String LIST_DISABLE = "LIST-DISABLE";
    private static final String FIND = "FIND";
    private static final String HISTORY = "HISTORY";
    private static final String UPDATE = "UPDATE";
    private static final String DISABLE = "DISABLE";
    private static final String ENABLE = "ENABLE";
    private static final String DELETE = "DELETE";
	
	 public Facade() {
		// Contexts
        String book = Book.class.getSimpleName();
        String customer = Customer.class.getSimpleName();
        
        // General Strategies
		IStrategy createView = new CreateView();
		IStrategy insertHistory = new InsertHistory();
		IStrategy updateHistory = new UpdateHistory();
		// Books
		IStrategy modifyStatus = new ModifyStatus();
		// Customers
		IStrategy custumerFind = new FindCustomer();
                
		List<IStrategy> createBook = new ArrayList();
		createBook.add(createView);
		
        List<IStrategy> saveBook = new ArrayList();
		saveBook.add(new BookValidateSave());
		
        List<IStrategy> listBook = new ArrayList();
		listBook.add(createView);
		
        List<IStrategy> listDisableBook = new ArrayList();
		listDisableBook.add(createView);
		        
		List<IStrategy> findBook = new ArrayList();
		List<IStrategy> historyBook = new ArrayList();
				
		List<IStrategy> updateBook = new ArrayList();
		updateBook.add(new BookValidateUpdate());
		updateBook.add(updateHistory);
		
		List<IStrategy> disableBook = new ArrayList();
		disableBook.add(modifyStatus);
		
		List<IStrategy> enableBook = new ArrayList();
		enableBook.add(modifyStatus);
		
		List<IStrategy> deleteBook = new ArrayList();
		
		List<IStrategy> createCustomer = new ArrayList();
		createCustomer.add(createView);
		
		List<IStrategy> saveCustomer = new ArrayList();
		saveCustomer.add(new CustomerValidateSave());
		
		List<IStrategy> listCustomer = new ArrayList();
		listCustomer.add(createView);
		
		List<IStrategy> listDisableCustomer = new ArrayList();
		List<IStrategy> findCustomer = new ArrayList();
		List<IStrategy> historyCustomer = new ArrayList();
				
		List<IStrategy> updateCustomer = new ArrayList();
		updateCustomer.add(new CustomerValidateUpdate());
		updateCustomer.add(updateHistory);
		
		List<IStrategy> disableCustomer = new ArrayList();
		disableCustomer.add(custumerFind);
		
		List<IStrategy> enableCustomer = new ArrayList();
		enableCustomer.add(custumerFind);
		
		List<IStrategy> deleteCustomer = new ArrayList();
		
		// Requirements Book
        Map<String, List<IStrategy>> contextReqBook = new HashMap();
        contextReqBook.put(CREATE, createBook);
		contextReqBook.put(SAVE, saveBook);
        contextReqBook.put(LIST, listBook);
        contextReqBook.put(LIST_DISABLE, listDisableBook);
		contextReqBook.put(FIND, findBook);
		contextReqBook.put(HISTORY, historyBook);
        contextReqBook.put(UPDATE, updateBook);
        contextReqBook.put(DISABLE, disableBook);
        contextReqBook.put(ENABLE, enableBook);
		contextReqBook.put(DELETE, deleteBook);
		
		// Requirements Customer
		Map<String, List<IStrategy>> contextReqCustomer = new HashMap();
        contextReqCustomer.put(CREATE, createCustomer);
        contextReqCustomer.put(SAVE, saveCustomer);
        contextReqCustomer.put(LIST, listCustomer);
        contextReqCustomer.put(LIST_DISABLE, listDisableCustomer);
		contextReqCustomer.put(FIND, findCustomer);
		contextReqCustomer.put(HISTORY, historyCustomer);
		contextReqCustomer.put(UPDATE, updateCustomer);
		contextReqCustomer.put(DISABLE, disableCustomer);
		contextReqCustomer.put(ENABLE, enableCustomer);
		contextReqCustomer.put(DELETE, deleteCustomer);
		
		// Requirements Later
		List<IStrategy> saveBookLater = new ArrayList();
		saveBookLater.add(insertHistory);
		
		List<IStrategy> saveCustomerLater = new ArrayList();
		saveCustomerLater.add(insertHistory);
		
		// Requirements Book Later
        Map<String, List<IStrategy>> contextReqBookLater = new HashMap();
		contextReqBookLater.put(SAVE, saveBookLater);
		
		// Requirements Customer Later
		Map<String, List<IStrategy>> contextReqCustomerLater = new HashMap();
        contextReqCustomerLater.put(SAVE, saveCustomerLater);
		
		// Requirements
        requirements = new HashMap<>();
        requirements.put(book, contextReqBook);
        requirements.put(customer, contextReqCustomer);
        
		// Requirements Later
		requirementsLater = new HashMap<>();
		requirementsLater.put(book, contextReqBookLater);
        requirementsLater.put(customer, contextReqCustomerLater);
		
		// All DAOs
		IDao bookDao = new BookDao();
		IDao historyDao = new HistoryDao();
		IDao customerDao = new CustomerDao();
		
		// Persistence Book
		Map<String, IDao> contextPersBook = new HashMap();
        contextPersBook.put(CREATE, bookDao);
        contextPersBook.put(SAVE, bookDao);
        contextPersBook.put(LIST, bookDao);
        contextPersBook.put(LIST_DISABLE, bookDao);
		contextPersBook.put(FIND, bookDao);
		contextPersBook.put(HISTORY, historyDao);
		contextPersBook.put(UPDATE, bookDao);
		contextPersBook.put(DISABLE, bookDao);
		contextPersBook.put(ENABLE, bookDao);
		contextPersBook.put(DELETE, bookDao);
		
		// Persistence Customer
		Map<String, IDao> contextPersCustomer = new HashMap();
        contextPersCustomer.put(CREATE, customerDao);
        contextPersCustomer.put(SAVE, customerDao);
        contextPersCustomer.put(LIST, customerDao);
        contextPersCustomer.put(LIST_DISABLE, customerDao);
		contextPersCustomer.put(FIND, customerDao);
		contextPersCustomer.put(HISTORY, historyDao);
		contextPersCustomer.put(UPDATE, customerDao);
		contextPersCustomer.put(DISABLE, customerDao);
		contextPersCustomer.put(ENABLE, customerDao);
		contextPersCustomer.put(DELETE, customerDao);
		
		// Persistences
        persistence = new HashMap();
        persistence.put(book, contextPersBook);
        persistence.put(customer, contextPersCustomer);

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
		
		Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(operation);
        result.addEntities(dao.select(operation.equals(LIST), entity));
        
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
		
        Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(operation);
        boolean resultDao = dao.save(entity);
        
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");
        
		Map<String, List<IStrategy>> reqsLater = requirementsLater.get(entity.getClass().getSimpleName());
        List<IStrategy> validationsLater = reqsLater.get(operation);
		
		result.setOperation(operation);
        result = executeValidations(entity, validationsLater);
		
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
				
	    Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(operation);
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
		
	    Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(operation);
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
		if(result.hasMsg()){
			String msg = result.getMsg();
			
			result = create(entity, CREATE);
			
			result.addEntity(entity);
			result.setMsg(msg);
			
			return result;
		}

        Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(operation);
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
