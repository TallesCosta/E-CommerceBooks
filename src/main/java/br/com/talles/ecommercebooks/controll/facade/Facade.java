package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.CreateView;
import br.com.talles.ecommercebooks.business.InsertHistory;
import br.com.talles.ecommercebooks.business.UpdateHistory;
import br.com.talles.ecommercebooks.business.book.ModifyStatus;
import br.com.talles.ecommercebooks.business.book.save.BookValidateSave;
import br.com.talles.ecommercebooks.business.book.update.BookValidateUpdate;
import br.com.talles.ecommercebooks.business.cart.DestroyCart;
import br.com.talles.ecommercebooks.business.cart.GiveBackStock;
import br.com.talles.ecommercebooks.business.cart.delete.CartWithoutSession;
import br.com.talles.ecommercebooks.business.cart.save.CartSession;
import br.com.talles.ecommercebooks.business.cart.save.CompleteCart;
import br.com.talles.ecommercebooks.business.cart.save.UpdateStockLastManipulation;
import br.com.talles.ecommercebooks.business.cart.save.ValidateAmount;
import br.com.talles.ecommercebooks.business.customer.FindCustomer;
import br.com.talles.ecommercebooks.business.sale.save.CompleteSale;
import br.com.talles.ecommercebooks.business.sale.create.CustomerFragment;
import br.com.talles.ecommercebooks.business.user.list.FoundUser;
import br.com.talles.ecommercebooks.business.customer.save.CustomerValidateSave;
import br.com.talles.ecommercebooks.business.customer.update.CustomerValidateUpdate;
import br.com.talles.ecommercebooks.controll.Transaction;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import br.com.talles.ecommercebooks.persistence.dao.HistoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.UserDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.SaleDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.StockDao;

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
		String user = User.class.getSimpleName();
		String sale = Sale.class.getSimpleName();
		String stock = Stock.class.getSimpleName();
		String cart = Cart.class.getSimpleName();
        
        // General Strategies
		IStrategy createView = new CreateView();
		IStrategy insertHistory = new InsertHistory();
		IStrategy updateHistory = new UpdateHistory();
		// Book Strategies
		IStrategy modifyStatus = new ModifyStatus();
		// Customer Strategies
		IStrategy custumerFind = new FindCustomer();
		// User Strategies
		FoundUser foundUser = new FoundUser();
		// Cart Strategies
 		IStrategy completeCart = new CompleteCart();
	 	IStrategy validateAmount = new ValidateAmount();
	 	IStrategy cartSession = new CartSession();
	 	IStrategy updateStockLastManipulation = new UpdateStockLastManipulation();
	 	IStrategy cartWithoutSession = new CartWithoutSession();
	 	// Sale Strategies
	 	IStrategy customerFragment = new CustomerFragment();
	 	IStrategy completeSale = new CompleteSale();
	 	IStrategy giveBackStock = new GiveBackStock();
	 	IStrategy destroyCart = new DestroyCart();

		// Book Requirements
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
		
		// Customer Requirements
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
		
		// Cart Requirements
		List<IStrategy> createCart = new ArrayList();
		
		List<IStrategy> saveCart = new ArrayList();
		saveCart.add(completeCart);
		saveCart.add(validateAmount);
		saveCart.add(cartSession);
		saveCart.add(updateStockLastManipulation);
		
		List<IStrategy> listCart = new ArrayList();
		List<IStrategy> listDisableCart = new ArrayList();
		List<IStrategy> findCart = new ArrayList();
		List<IStrategy> historyCart = new ArrayList();
		List<IStrategy> updateCart = new ArrayList();		
		List<IStrategy> disableCart = new ArrayList();
		List<IStrategy> enableCart = new ArrayList();		
		
		List<IStrategy> deleteCart = new ArrayList();
		deleteCart.add(cartWithoutSession);
		
		// User Requirements
		List<IStrategy> listUser = new ArrayList();
		
		// Stock Requirements
		List<IStrategy> listStock = new ArrayList();
		
		// Sales Requirements
		List<IStrategy> createSale = new ArrayList();
		createSale.add(customerFragment);

	 	List<IStrategy> saveSale = new ArrayList();
	 	saveSale.add(completeSale);

	 	List<IStrategy> listSale = new ArrayList();

		// Book Requirements to contexts
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

		// Customer Requirements to contexts
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
		
		// User Requirements to contexts
		Map<String, List<IStrategy>> contextReqUser = new HashMap();
        contextReqUser.put(LIST, listUser);
		
		// Stock Requirements to contexts
		Map<String, List<IStrategy>> contextReqStock = new HashMap();
        contextReqStock.put(LIST, listStock);
		
		// Sale Requirements to contexts
		Map<String, List<IStrategy>> contextReqSale = new HashMap();
		contextReqSale.put(CREATE, createSale);
		contextReqSale.put(SAVE, saveSale);
        contextReqSale.put(LIST, listSale);
		
		// Book Requirements to contexts
        Map<String, List<IStrategy>> contextReqCart = new HashMap();
        contextReqCart.put(CREATE, createCart);
		contextReqCart.put(SAVE, saveCart);
        contextReqCart.put(LIST, listCart);
        contextReqCart.put(LIST_DISABLE, listDisableCart);
		contextReqCart.put(FIND, findCart);
		contextReqCart.put(HISTORY, historyCart);
        contextReqCart.put(UPDATE, updateCart);
        contextReqCart.put(DISABLE, disableCart);
        contextReqCart.put(ENABLE, enableCart);
		contextReqCart.put(DELETE, deleteCart);
		
		// Requirements Later
		List<IStrategy> saveBookLater = new ArrayList();
		saveBookLater.add(insertHistory);
		
		List<IStrategy> listBookLater = new ArrayList();
		List<IStrategy> listDisableBookLater = new ArrayList();
		
		List<IStrategy> saveCustomerLater = new ArrayList();
		saveCustomerLater.add(insertHistory);
		
		List<IStrategy> listCustomerLater = new ArrayList();
		List<IStrategy> listDisableCustomerLater = new ArrayList();
		
		List<IStrategy> listUserLater = new ArrayList();
		listUserLater.add(foundUser);
		
		List<IStrategy> listStockLater = new ArrayList();
		//listStockLater.add(new FoundUser()); What? Ctrl + C / Ctrl + V????
		
		List<IStrategy> saveSaleLater = new ArrayList();
		saveSaleLater.add(destroyCart);

		List<IStrategy> listSaleLater = new ArrayList();
		
		// Requirements Book Later
        Map<String, List<IStrategy>> contextReqBookLater = new HashMap();
		contextReqBookLater.put(SAVE, saveBookLater);
		contextReqBookLater.put(LIST, listBookLater);
		contextReqBookLater.put(LIST_DISABLE, listDisableBookLater);
		
		// Requirements Customer Later
		Map<String, List<IStrategy>> contextReqCustomerLater = new HashMap();
        contextReqCustomerLater.put(SAVE, saveCustomerLater);
        contextReqCustomerLater.put(LIST, listCustomerLater);
        contextReqCustomerLater.put(LIST_DISABLE, listDisableCustomerLater);
		
		// Requirements User Later
		Map<String, List<IStrategy>> contextReqUserLater = new HashMap();
        contextReqUserLater.put(LIST, listUserLater);
		
		// Requirements Stock Later
		Map<String, List<IStrategy>> contextReqStockLater = new HashMap();
        contextReqStockLater.put(LIST, listStockLater);
		
		// Requirements Sale Later
		Map<String, List<IStrategy>> contextReqSaleLater = new HashMap();
		contextReqSaleLater.put(SAVE, saveSaleLater);
        contextReqSaleLater.put(LIST, listSaleLater);
		
		// Requirements
        requirements = new HashMap<>();
        requirements.put(book, contextReqBook);
        requirements.put(customer, contextReqCustomer);
        requirements.put(user, contextReqUser);
        requirements.put(stock, contextReqStock);
        requirements.put(sale, contextReqSale);
        requirements.put(cart, contextReqCart);
        
		// Requirements Later
		requirementsLater = new HashMap<>();
		requirementsLater.put(book, contextReqBookLater);
        requirementsLater.put(customer, contextReqCustomerLater);
        requirementsLater.put(user, contextReqUserLater);
        requirementsLater.put(stock, contextReqStockLater);
        requirementsLater.put(sale, contextReqSaleLater);
		
		// All DAOs
		IDao historyDao = new HistoryDao();
		IDao bookDao = new BookDao();
		IDao customerDao = new CustomerDao();
		IDao userDao = new UserDao();
		IDao stockDao = new StockDao();
		IDao saleDao = new SaleDao();
		
		// Book Persistence
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
		
		// Customer Persistence
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
		
		// User Persistence 
		Map<String, IDao> contextPersUser = new HashMap();
        contextPersUser.put(LIST, userDao);
		
		// Stock Persistence 
		Map<String, IDao> contextPersStock = new HashMap();
        contextPersStock.put(LIST, stockDao);
		
		// Sale Persistence 
		Map<String, IDao> contextPersSale = new HashMap();
        contextPersSale.put(LIST, saleDao);
        contextPersSale.put(SAVE, saleDao);
		
		// Persistences
        persistence = new HashMap();
        persistence.put(book, contextPersBook);
        persistence.put(customer, contextPersCustomer);
        persistence.put(stock, contextPersStock);
        persistence.put(user, contextPersUser);
        persistence.put(sale, contextPersSale);

        this.result = new Result();
    }
	 
	@Override
	public Result list(Entity entity, Transaction transaction) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(transaction.getOperation());
		
		result.setTransaction(transaction);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(transaction.getOperation());
        result.addEntities(dao.select(transaction.getOperation().equals(LIST), entity));
        
		Map<String, List<IStrategy>> reqsLater = requirementsLater.get(entity.getClass().getSimpleName());
        List<IStrategy> validationsLater = reqsLater.get(transaction.getOperation());
		
		result.setTransaction(transaction);
        result = executeValidations(entity, validationsLater);
		
        return result;
	}
		
	@Override
	public Result save(Entity entity, Transaction transaction) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(transaction.getOperation());
		
		result.setTransaction(transaction);
        result = executeValidations(entity, validations);
        if(result.hasMsg()){
			String msg = result.getMsg();
			
			result = create(entity, new Transaction(transaction.getRequest(), CREATE));
			
			result.addEntity(entity);
			result.setMsg(msg);
			
			return result;
		}
		
        Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		if (daosEntity != null){
			IDao dao = daosEntity.get(transaction.getOperation());
			boolean resultDao = dao.save(entity);

			if(!resultDao)
				result.addMsg("An error has occurred in the process of your operation, "
						+ "it has been noted and will be resolved soon!");
			
			Map<String, List<IStrategy>> reqsLater = requirementsLater.get(entity.getClass().getSimpleName());
			List<IStrategy> validationsLater = reqsLater.get(transaction.getOperation());
			
			result.setTransaction(transaction);
			result = executeValidations(entity, validationsLater);
		}
		
        return result;
	}
	
	@Override
	public Result delete(Entity entity, Transaction transaction){
		this.result = new Result();
		
        Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(transaction.getOperation());
		
		result.setTransaction(transaction);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
				
	    Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		if (daosEntity != null){
			IDao dao = daosEntity.get(transaction.getOperation());
			boolean resultDao = dao.delete(entity);

			if(!resultDao)
				result.addMsg("An error has occurred in the process of your operation, "
						+ "it has been noted and will be resolved soon!");
		}
		
	    return result;
	}
	
	@Override
	public Result find(Entity entity, Transaction transaction) {
		this.result = new Result();
		
        Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(transaction.getOperation());
		
		result.setTransaction(transaction);
		result = executeValidations(entity, validations);
        if(result.hasMsg())
            return result;
		
		// Loads create view datas
		result = create(entity, new Transaction(transaction.getRequest(), CREATE));
		result.setTransaction(transaction);
		
	    Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(transaction.getOperation());
	    result.addEntity(dao.find(entity));
		
	    return result;
	}
	
	@Override
    public Result update(Entity entity, Transaction transaction) {
        this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(transaction.getOperation());
		
        result.setTransaction(transaction);
		result = executeValidations(entity, validations);
		if(result.hasMsg()){
			String msg = result.getMsg();
			
			result = create(entity, new Transaction(transaction.getRequest(), CREATE));
			
			result.addEntity(entity);
			result.setMsg(msg);
			
			return result;
		}

        Map<String, IDao> daosEntity = persistence.get(entity.getClass().getSimpleName());
		IDao dao = daosEntity.get(transaction.getOperation());
        boolean resultDao = dao.update(entity, transaction.getOperation());
        if(!resultDao)
            result.addMsg("An error has occurred in the process of your operation, "
					+ "it has been noted and will be resolved soon!");

        return result;
    }
	
	@Override
	public Result create(Entity entity, Transaction transaction){
		this.result = new Result();
		
		Map<String, List<IStrategy>> reqs = requirements.get(entity.getClass().getSimpleName());
        List<IStrategy> validations = reqs.get(transaction.getOperation());
		
		result.setTransaction(transaction);
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
