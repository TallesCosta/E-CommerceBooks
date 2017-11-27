
package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;

import javax.servlet.http.HttpSession;

public class CartSession implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		HttpSession session = result.getTransaction().getRequest().getSession();
		Cart cartSession = (Cart) session.getAttribute("cart");

		Cart cart = (Cart) entity;
		int i = cart.countSaleItems() - 1;

		if (cartSession == null)
			cartSession = new Cart();

		cartSession.addSaleItems(cart.getSaleItems());
		cart.getSaleItem(i).getBook().getStock().minusVirtualAmount(cart.getSaleItem(i).getAmount());

		session.setAttribute("cart", cartSession);
		return result;
	}
	
}
