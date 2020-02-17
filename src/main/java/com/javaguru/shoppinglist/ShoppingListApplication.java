package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.consoleView.ConsoleUI;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.PriceValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductUniqueNameValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.util.HashSet;
import java.util.Set;

class ShoppingListApplication {

    public static void main(String[] args) {
        ProductInMemoryRepository repository = new ProductInMemoryRepository();

        ProductNameValidationRule productNameValidationRule = new ProductNameValidationRule();
        ProductUniqueNameValidationRule productUniqueNameValidationRule = new
                ProductUniqueNameValidationRule(repository);
        PriceValidationRule priceValidationRule = new PriceValidationRule();

        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(productNameValidationRule);
        rules.add(productUniqueNameValidationRule);
        rules.add(priceValidationRule);

        ProductValidationService validationService = new ProductValidationService(rules);
        ProductService taskService = new ProductService(repository, validationService);

        ConsoleUI consoleUI = new ConsoleUI(taskService);
        consoleUI.execute();
    }
}
