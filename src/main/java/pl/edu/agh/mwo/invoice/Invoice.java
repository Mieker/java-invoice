package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private int number;
    static int lastNumber = 0;
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Invoice() {
        this.number = lastNumber + 1;
        lastNumber = this.number;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {

        return number;
    }
    
    public int countProductsQuantitySummary() {
        int quantitySummary = 0;
        for (Product product : products.keySet()) {
            quantitySummary += products.get(product);
        }
        return quantitySummary;
    }

    public String printInvoice() {
        String invoiceToPrint = "";
        String head = String.format("%16s %10s %15s %13s", "NAZWA       |", "ILOSC   |",
                "CENA NETTO   |", "CENA BRUTTO ");
        invoiceToPrint += Integer.toString(this.getNumber()) + "\n\n" + head;
        for (Product product : products.keySet()) {
            String productInfoLine = String.format("%-14s %s %8s %s %13s %s %13s",
                    product.getName(), "|", products.get(product) + " szt.", "|",
                    product.getPrice() + " PLN", "|", product.getPrice() + " PLN");
            invoiceToPrint += "\n" + productInfoLine;
        }
        invoiceToPrint += "\n\n" + countProductsQuantitySummary();

        return invoiceToPrint;
    }
}
