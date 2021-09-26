package com.apple.formatter;

import com.apple.model.Product;
import com.apple.service.product.IProductService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProductFormatter implements Formatter<Product> {
    private IProductService productService;

    public ProductFormatter(IProductService productService) {
        this.productService = productService;

    }

    @Override
    public Product parse(String text, Locale locale) throws ParseException {
        return productService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Product object, Locale locale) {
        return null;
    }
}
