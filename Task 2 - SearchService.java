package com.mockcompany.webapp.service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductItemRepository productItemRepository;

    public Collection<ProductItem> searchItems(String query) {
        Iterable<ProductItem> allItems = productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        // This is a loop that the code inside will execute on each of the items from the database.
        for (ProductItem item : allItems) {
            // TODO: Figure out if the item should be returned based on the query parameter!
            boolean matchesSearch = false;
            if (query.charAt(0) == '"' && query.charAt(query.length() - 1) == '"') {
                query = query.substring(1, query.length() - 1);
                if (item.getName().equals(query) || item.getDescription().equals(query))
                    matchesSearch = true;
            }
            if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(query.toLowerCase())) {
                matchesSearch = true;
            }
            if (matchesSearch)
                itemList.add(item);
        }
        return itemList;
    }
}
