package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.CreateView;
import br.com.talles.ecommercebooks.business.InsertHistory;
import br.com.talles.ecommercebooks.business.UpdateHistory;
import br.com.talles.ecommercebooks.business.address.save.RememberCustomerAddress;
import br.com.talles.ecommercebooks.business.book.ModifyStatus;
import br.com.talles.ecommercebooks.business.book.save.BookValidateSave;
import br.com.talles.ecommercebooks.business.book.update.BookValidateUpdate;
import br.com.talles.ecommercebooks.business.cart.DestroyCart;
import br.com.talles.ecommercebooks.business.cart.CheckouStock;
import br.com.talles.ecommercebooks.business.cart.delete.RemoveItemOfCart;
import br.com.talles.ecommercebooks.business.cart.save.AddItemOfCart;
import br.com.talles.ecommercebooks.business.cart.save.ValidateAmountStock;
import br.com.talles.ecommercebooks.business.cart.save.ValidateEmpityCart;
import br.com.talles.ecommercebooks.business.creditCard.save.RememberCustomerCreditCard;
import br.com.talles.ecommercebooks.business.address.save.AddressNotBlank;
import br.com.talles.ecommercebooks.business.creditCard.save.CreditCardNotBlank;
import br.com.talles.ecommercebooks.business.customer.FindCustomer;
import br.com.talles.ecommercebooks.business.exchange.create.FoundSaleExchange;
import br.com.talles.ecommercebooks.business.exchange.ExchangeStatusSale;
import br.com.talles.ecommercebooks.business.order.list.SetCustomerCurrent;
import br.com.talles.ecommercebooks.business.sale.save.CompleteSaleData;
import br.com.talles.ecommercebooks.business.sale.create.BuildDataForSaleScreen;
import br.com.talles.ecommercebooks.business.sale.save.DisableUsedExchangeCoupons;
import br.com.talles.ecommercebooks.business.sale.save.ValidatePayment;
import br.com.talles.ecommercebooks.business.salesPerGenders.list.BuildSalesPerGendersJsonReport;
import br.com.talles.ecommercebooks.business.salesPerGenders.list.CompleteReportConsult;
import br.com.talles.ecommercebooks.business.stock.list.MakeStockSession;
import br.com.talles.ecommercebooks.business.user.delete.DestroyUser;
import br.com.talles.ecommercebooks.business.user.list.LoginUser;
import br.com.talles.ecommercebooks.business.customer.save.CustomerValidateSave;
import br.com.talles.ecommercebooks.business.customer.update.CustomerValidateUpdate;
import br.com.talles.ecommercebooks.controll.Transaction;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.*;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.SalesPerGenders;
import br.com.talles.ecommercebooks.domain.sale.*;
import br.com.talles.ecommercebooks.persistence.dao.HistoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.*;
import br.com.talles.ecommercebooks.persistence.dao.report.SalesPerGendersDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.ExchangeDao;
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
		String orderRequest = OrderRequest.class.getSimpleName();
        String exchange = Exchange.class.getSimpleName();
        String chargeAddress = ChargeAddress.class.getSimpleName();
		String deliveryAddress = DeliveryAddress.class.getSimpleName();
		String creditCard = CreditCard.class.getSimpleName();
		String salesPerGenders = SalesPerGenders.class.getSimpleName();

        // General Strategies
		IStrategy createView = new CreateView();
		IStrategy insertHistory = new InsertHistory();
		IStrategy updateHistory = new UpdateHistory();
		// Book Strategies
		IStrategy modifyStatus = new ModifyStatus();
		// Customer Strategies
		IStrategy custumerFind = new FindCustomer();
		// User Strategies
		IStrategy foundUser = new LoginUser();
		IStrategy destroyUser = new DestroyUser();
		// Cart Strategies
		IStrategy validateAmount = new ValidateAmountStock();
	 	IStrategy cartSession = new AddItemOfCart();
	 	IStrategy cartWithoutSession = new RemoveItemOfCart();
	 	// Sale Strategies
		IStrategy validateCart = new ValidateEmpityCart();
	 	IStrategy customerFragment = new BuildDataForSaleScreen();
	 	IStrategy completeSale = new CompleteSaleData();
	 	IStrategy giveBackStock = new CheckouStock();
	 	IStrategy destroyCart = new DestroyCart();
	 	IStrategy disableExchangeCoupons = new DisableUsedExchangeCoupons();
	 	IStrategy validatePayment = new ValidatePayment();
	 	// Stock Strategies
		IStrategy stockSession = new MakeStockSession();
		// OrderRequest Strategies
		IStrategy chooseCustomer = new SetCustomerCurrent();
		IStrategy foundSale = new FoundSaleExchange();
		// Exchange
		IStrategy exchangeStatusSale = new ExchangeStatusSale();
		// Address
		IStrategy addressNotBlank = new AddressNotBlank();
		IStrategy rememberCustomerAddress = new RememberCustomerAddress();
		// CreditCard
		IStrategy creditCardNotBlank = new CreditCardNotBlank();
        IStrategy rememberCustomer = new RememberCustomerCreditCard();
        // SalesPerGernders
		IStrategy completeReportConsult = new CompleteReportConsult();
		IStrategy buildSalesPerGendersJsonReport = new BuildSalesPerGendersJsonReport();

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
		saveCart.add(validateAmount);
		saveCart.add(cartSession);
		
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

		List<IStrategy> deleteUser = new ArrayList();
		deleteUser.add(destroyUser);

		// Stock Requirements
		List<IStrategy> listStock = new ArrayList();
		
		// Sales Requirements
		List<IStrategy> createSale = new ArrayList();
		createSale.add(validateCart);
		createSale.add(customerFragment);

	 	List<IStrategy> saveSale = new ArrayList();
	 	saveSale.add(completeSale);
	 	saveSale.add(validatePayment);

	 	List<IStrategy> listSale = new ArrayList();
	 	List<IStrategy> findSale = new ArrayList();

	 	List<IStrategy> updateSale = new ArrayList();
		updateSale.add(updateHistory);

	 	// OrderRequests Requirements
	 	List<IStrategy> listOrderRequest = new ArrayList();
		listOrderRequest.add(chooseCustomer);

	 	List<IStrategy> findOrderRequest = new ArrayList();
		List<IStrategy> createOrderRequest = new ArrayList();
		List<IStrategy> updateOrderRequest = new ArrayList();

		// Exchanges Requirements
        List<IStrategy> createExchange = new ArrayList();
        createExchange.add(foundSale);

		List<IStrategy> saveExchange = new ArrayList();
		List<IStrategy> updateExchange = new ArrayList();

		// ChargeAdresses Requirements
		List<IStrategy> createChargeAddress = new ArrayList();
		createChargeAddress.add(createView);

		List<IStrategy> listChargeAddress = new ArrayList();

		List<IStrategy> saveChargeAddress = new ArrayList();
		saveChargeAddress.add(addressNotBlank);

		// DeliveryAdresses Requirements
		List<IStrategy> createDeliveryAddress = new ArrayList();
		createDeliveryAddress.add(createView);

		List<IStrategy> listDeliveryAddress = new ArrayList();

		List<IStrategy> saveDeliveryAddress = new ArrayList();
		saveDeliveryAddress.add(addressNotBlank);

		// CreditCards Requirements
		List<IStrategy> createCreditCard = new ArrayList();
		createCreditCard.add(createView);

		List<IStrategy> listCreditCard = new ArrayList();

		List<IStrategy> saveCreditCard = new ArrayList();
		saveCreditCard.add(creditCardNotBlank);

		// SalesPerGenders Requirements
		List<IStrategy> listSalesPerGenders = new ArrayList<>();

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
		contextReqUser.put(DELETE, deleteUser);

		 // Stock Requirements to contexts
		Map<String, List<IStrategy>> contextReqStock = new HashMap();
        contextReqStock.put(LIST, listStock);
		
		// Sale Requirements to contexts
		Map<String, List<IStrategy>> contextReqSale = new HashMap();
		contextReqSale.put(CREATE, createSale);
		contextReqSale.put(SAVE, saveSale);
        contextReqSale.put(LIST, listSale);
        contextReqSale.put(FIND, findSale);
        contextReqSale.put(UPDATE, updateSale);

        // Order Requirements to contexts
		Map<String, List<IStrategy>> contextReqOrderRequest = new HashMap();
		contextReqOrderRequest.put(LIST, listOrderRequest);
		contextReqOrderRequest.put(FIND, findOrderRequest);
		contextReqOrderRequest.put(CREATE, createOrderRequest);
		contextReqOrderRequest.put(UPDATE, updateOrderRequest);

		// Cart Requirements to contexts
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

        // Exchanges to contexts
        Map<String, List<IStrategy>> contextReqExchange = new HashMap();
        contextReqExchange.put(CREATE, createExchange);
		contextReqExchange.put(SAVE, saveExchange);
		contextReqExchange.put(UPDATE, updateExchange);

		// ChargeAddress to contexts
		Map<String, List<IStrategy>> contextReqChargeAddress = new HashMap();
        contextReqChargeAddress.put(CREATE, createChargeAddress);
        contextReqChargeAddress.put(SAVE, saveChargeAddress);
        contextReqChargeAddress.put(LIST, listChargeAddress);

		// DeliveryAddress to contexts
		Map<String, List<IStrategy>> contextReqDeliveryAddress = new HashMap();
        contextReqDeliveryAddress.put(CREATE, createDeliveryAddress);
        contextReqDeliveryAddress.put(SAVE, saveDeliveryAddress);
        contextReqDeliveryAddress.put(LIST, listDeliveryAddress);

		// CreditCard to contexts
		Map<String, List<IStrategy>> contextReqCreditCard = new HashMap();
        contextReqCreditCard.put(CREATE, createCreditCard);
        contextReqCreditCard.put(SAVE, saveCreditCard);
        contextReqCreditCard.put(LIST, listCreditCard);

        Map<String, List<IStrategy>> contextReqSalesPerGenders = new HashMap<>();
        contextReqSalesPerGenders.put(LIST, listSalesPerGenders);

		// Requirements Later
		// Book Requirements Later to contexts
		List<IStrategy> saveBookLater = new ArrayList();
		saveBookLater.add(insertHistory);
		
		List<IStrategy> listBookLater = new ArrayList();
		List<IStrategy> listDisableBookLater = new ArrayList();

		// Customer Requirements Later to contexts
		List<IStrategy> saveCustomerLater = new ArrayList();
		saveCustomerLater.add(insertHistory);
		
		List<IStrategy> listCustomerLater = new ArrayList();
		List<IStrategy> listDisableCustomerLater = new ArrayList();

		// User Requirements Later to contexts
		List<IStrategy> listUserLater = new ArrayList();
		listUserLater.add(foundUser);

		// Stock Requirements Later to contexts
		List<IStrategy> listStockLater = new ArrayList();
		listStockLater.add(stockSession);
		//listStockLater.add(new LoginUser()); What? Ctrl + C / Ctrl + V????

		// Sale Requirements Later to contexts
		List<IStrategy> saveSaleLater = new ArrayList();
		saveSaleLater.add(giveBackStock);
		saveSaleLater.add(destroyCart);
        saveSaleLater.add(disableExchangeCoupons);
		saveSaleLater.add(insertHistory);

		List<IStrategy> listSaleLater = new ArrayList();

		// OrderRequest Requirements Later to contexts
		List<IStrategy> listOrderRequestLater = new ArrayList();

		// Exchange Requirements Later to contexts
        List<IStrategy> saveExchangeLater = new ArrayList();
        saveExchangeLater.add(exchangeStatusSale);

		List<IStrategy> updateExchangeLater = new ArrayList();
		updateExchangeLater.add(exchangeStatusSale);

		// ChargeAddress Requirements Later to contexts
		List<IStrategy> listChargeAddressLater = new ArrayList();

		List<IStrategy> saveChargeAddressLater = new ArrayList();
		saveChargeAddressLater.add(rememberCustomerAddress);

		// DeliveryAddress Requirements Later to contexts
		List<IStrategy> listDeliveryAddressLater = new ArrayList();

		List<IStrategy> saveDeliveryAddressLater = new ArrayList();
		saveDeliveryAddressLater.add(rememberCustomerAddress);

		// CreditCard Requirements Later to contexts
		List<IStrategy> listCreditCardLater = new ArrayList();

		List<IStrategy> saveCreditCardLater = new ArrayList();
		saveCreditCardLater.add(rememberCustomer);

		// SalesPerGenders Requirements to contexts
		List<IStrategy> listSalesPerGendersLater = new ArrayList<>();
		listSalesPerGendersLater.add(completeReportConsult);
		listSalesPerGendersLater.add(buildSalesPerGendersJsonReport);

		// Requirements Book Later to contexts
        Map<String, List<IStrategy>> contextReqBookLater = new HashMap();
		contextReqBookLater.put(SAVE, saveBookLater);
		contextReqBookLater.put(LIST, listBookLater);
		contextReqBookLater.put(LIST_DISABLE, listDisableBookLater);
		
		// Requirements Customer Later to contexts
		Map<String, List<IStrategy>> contextReqCustomerLater = new HashMap();
        contextReqCustomerLater.put(SAVE, saveCustomerLater);
        contextReqCustomerLater.put(LIST, listCustomerLater);
        contextReqCustomerLater.put(LIST_DISABLE, listDisableCustomerLater);
		
		// Requirements User Later to contexts
		Map<String, List<IStrategy>> contextReqUserLater = new HashMap();
        contextReqUserLater.put(LIST, listUserLater);
		
		// Requirements Stock Later to contexts
		Map<String, List<IStrategy>> contextReqStockLater = new HashMap();
        contextReqStockLater.put(LIST, listStockLater);
		
		// Requirements Sale Later to contexts
		Map<String, List<IStrategy>> contextReqSaleLater = new HashMap();
		contextReqSaleLater.put(SAVE, saveSaleLater);
        contextReqSaleLater.put(LIST, listSaleLater);

        // Requirements OrderRequest Later to contexts
		Map<String, List<IStrategy>> contextReqOrderRequestLater = new HashMap();
		contextReqOrderRequestLater.put(LIST, listOrderRequestLater);

		// Requirements Exchange Later to contexts
        Map<String, List<IStrategy>> contextReqExchangeLater = new HashMap();
        contextReqExchangeLater.put(SAVE, saveExchangeLater);
		contextReqExchangeLater.put(UPDATE, updateExchangeLater);

		// Requirements ChargeAddress Later to contexts
		Map<String, List<IStrategy>> contextReqChargeAddressLater = new HashMap();
        contextReqChargeAddressLater.put(SAVE, saveChargeAddressLater);
        contextReqChargeAddressLater.put(LIST, listChargeAddressLater);

		// Requirements DeliveryAddress Later to contexts
		Map<String, List<IStrategy>> contextReqDeliveryAddressLater = new HashMap();
        contextReqDeliveryAddressLater.put(SAVE, saveDeliveryAddressLater);
        contextReqDeliveryAddressLater.put(LIST, listDeliveryAddressLater);

		// Requirements CreditCard Later to contexts
		Map<String, List<IStrategy>> contextReqCreditCardLater = new HashMap();
        contextReqCreditCardLater.put(SAVE, saveCreditCardLater);
        contextReqCreditCardLater.put(LIST, listCreditCardLater);

		// Requirements SalesPerGenders Later to contexts
		Map<String, List<IStrategy>> contextReqSalesPerGendersLater = new HashMap();
		contextReqSalesPerGendersLater.put(LIST, listSalesPerGendersLater);

		// Requirements
        requirements = new HashMap<>();
        requirements.put(book, contextReqBook);
        requirements.put(customer, contextReqCustomer);
        requirements.put(user, contextReqUser);
        requirements.put(stock, contextReqStock);
        requirements.put(sale, contextReqSale);
        requirements.put(cart, contextReqCart);
        requirements.put(orderRequest, contextReqOrderRequest);
        requirements.put(exchange, contextReqExchange);
		requirements.put(chargeAddress, contextReqChargeAddress);
		requirements.put(deliveryAddress, contextReqDeliveryAddress);
		requirements.put(creditCard, contextReqCreditCard);
		requirements.put(salesPerGenders, contextReqSalesPerGenders);
        
		// Requirements Later
		requirementsLater = new HashMap<>();
		requirementsLater.put(book, contextReqBookLater);
        requirementsLater.put(customer, contextReqCustomerLater);
        requirementsLater.put(user, contextReqUserLater);
        requirementsLater.put(stock, contextReqStockLater);
        requirementsLater.put(sale, contextReqSaleLater);
        requirementsLater.put(orderRequest, contextReqOrderRequestLater);
		requirementsLater.put(exchange, contextReqExchangeLater);
		requirementsLater.put(chargeAddress, contextReqChargeAddressLater);
		requirementsLater.put(deliveryAddress, contextReqDeliveryAddressLater);
		requirementsLater.put(creditCard, contextReqCreditCardLater);
		requirementsLater.put(salesPerGenders, contextReqSalesPerGendersLater);
		
		// All DAOs
		IDao historyDao = new HistoryDao();
		IDao bookDao = new BookDao();
		IDao customerDao = new CustomerDao();
		IDao userDao = new UserDao();
		IDao stockDao = new StockDao();
		IDao saleDao = new SaleDao();
		IDao exchangeDao = new ExchangeDao();
		IDao chargeAddressDao = new ChargeAddressDao();
		IDao deliveryAddressDao = new DeliveryAddressDao();
		IDao creditCardDao = new CreditCardDao();
		IDao salesPerGendersDao = new SalesPerGendersDao();
		
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
		
		// User Persistence to contexts
		Map<String, IDao> contextPersUser = new HashMap();
        contextPersUser.put(LIST, userDao);
		
		// Stock Persistence to contexts
		Map<String, IDao> contextPersStock = new HashMap();
        contextPersStock.put(LIST, stockDao);
		
		// Order (Sale and OrderRequest) Persistence to contexts
		Map<String, IDao> contextPersSale = new HashMap();
        contextPersSale.put(LIST, saleDao);
        contextPersSale.put(SAVE, saleDao);
        contextPersSale.put(FIND, saleDao);
        contextPersSale.put(UPDATE, saleDao);

        // Exchange Persistence to contexts
		Map<String, IDao> contextPersExchange = new HashMap();
		contextPersExchange.put(SAVE, exchangeDao);
		contextPersExchange.put(UPDATE, exchangeDao);

		// ChargeAddress Persistence to contexts
		Map<String, IDao> contextPersChargeAddress = new HashMap();
		contextPersChargeAddress.put(SAVE, chargeAddressDao);
		contextPersChargeAddress.put(LIST, chargeAddressDao);

		// DeliveryAddress Persistence to contexts
		Map<String, IDao> contextPersDeliveryAddress = new HashMap();
		contextPersDeliveryAddress.put(SAVE, deliveryAddressDao);
		contextPersDeliveryAddress.put(LIST, deliveryAddressDao);

		// CreditCard Persistence to contexts
		Map<String, IDao> contextPersCreditCard = new HashMap();
		contextPersCreditCard.put(SAVE, creditCardDao);
		contextPersCreditCard.put(LIST, creditCardDao);

		// SalesPerGenders Persistence to contexts
		Map<String, IDao> contextPersSalesPerGenders = new HashMap<>();
		contextPersSalesPerGenders.put(LIST, salesPerGendersDao);

		// Persistences
        persistence = new HashMap();
        persistence.put(book, contextPersBook);
        persistence.put(customer, contextPersCustomer);
        persistence.put(stock, contextPersStock);
        persistence.put(user, contextPersUser);
        persistence.put(sale, contextPersSale);
		persistence.put(orderRequest, contextPersSale);
		persistence.put(exchange, contextPersExchange);
		persistence.put(chargeAddress, contextPersChargeAddress);
		persistence.put(deliveryAddress, contextPersDeliveryAddress);
		persistence.put(creditCard, contextPersCreditCard);
		persistence.put(salesPerGenders, contextPersSalesPerGenders);

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

			if (dao != null) {
				boolean resultDao = dao.delete(entity);

				if(!resultDao)
					result.addMsg("An error has occurred in the process of your operation, "
							+ "it has been noted and will be resolved soon!");
			}
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
		validations = reqs.get(CREATE);
		if (validations.contains(new CreateView())) {
			result = create(entity, new Transaction(transaction.getRequest(), CREATE));
			result.setTransaction(transaction);
		}

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

		Map<String, List<IStrategy>> reqsLater = requirementsLater.get(entity.getClass().getSimpleName());
		if (reqsLater != null) {
			List<IStrategy> validationsLater = reqsLater.get(transaction.getOperation());
			if (validationsLater != null) {
				result.setTransaction(transaction);
				result = executeValidations(entity, validationsLater);
			}
		}

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
