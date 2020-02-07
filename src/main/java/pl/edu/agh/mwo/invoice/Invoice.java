package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products = new ArrayList<Product>();

	public void addProduct(Product product) {
		products.add(product);
	}

	public void addProduct(Product product, Integer quantity) {
		for (int i = 1; i <= quantity; i++) {
			products.add(product);
		}
	}

	public BigDecimal getSubtotal() {
		BigDecimal result = BigDecimal.ZERO;
		for (Product product : products) {
			result = result.add(product.getPrice());
		}
		return result;
	}

	public BigDecimal getTax() {
		BigDecimal result = BigDecimal.ZERO;
		for (Product product : products) {
			result = result.add(product.getPrice().multiply(product.getTaxPercent()));
		}
		return result;
	}

	public BigDecimal getTotal() {
		BigDecimal result = BigDecimal.ZERO;
		for (Product product : products) {
			result = result.add(product.getPriceWithTax());
		}
		return result;
	}
}
