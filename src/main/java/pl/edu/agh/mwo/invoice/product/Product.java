package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax)  {
		
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Product name cannot be null.");
		}
		
		this.name = name;
		
		if (price == null || price.compareTo(price.ZERO) <= 0) {
			throw new IllegalArgumentException("Product price cannot be les than zero.");
		}
		this.price = price;
		this.taxPercent = tax;
	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public BigDecimal getTaxPercent() {
		return this.taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		BigDecimal result = this.price.add(this.taxPercent.multiply(this.price)); 
		return result;
	}
}
