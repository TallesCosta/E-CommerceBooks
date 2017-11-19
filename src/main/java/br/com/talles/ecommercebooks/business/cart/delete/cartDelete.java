
package br.com.talles.ecommercebooks.business.cart.delete;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import javax.servlet.http.HttpSession;

public class cartDelete implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		HttpSession session = result.getTransaction().getRequest().getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null)
			cart = new Cart();
		
		cart.removeSaleItem(cart.getSaleItem((int) cart.getId()));
		
		session.setAttribute("cart", cart);
		return result;
	}
	
}
