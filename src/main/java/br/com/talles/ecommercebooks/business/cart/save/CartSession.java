
package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.Stock;

import javax.servlet.http.HttpSession;

public class CartSession implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		HttpSession session = result.getTransaction().getRequest().getSession();
		Cart cartSession = (Cart) session.getAttribute("cart");

		Cart cart = (Cart) entity;
		if (cartSession == null)
			cartSession = new Cart();
		cartSession.addSaleItems(cart.getSaleItems());

		int i = cartSession.countSaleItems() - 1;
		long idBook = cartSession.getSaleItem(i).getBook().getId();

		Stock stockSession = (Stock) session.getAttribute("stock" + idBook);
		stockSession.minusRealAmount(cartSession.getSaleItem(i).getAmount());
		cartSession.getSaleItem(i).getBook().setStock(stockSession);

		session.setAttribute("cart", cartSession);
		session.setAttribute("stock" + idBook, stockSession);
		session.setMaxInactiveInterval(180);

		return result;
	}
	
}
