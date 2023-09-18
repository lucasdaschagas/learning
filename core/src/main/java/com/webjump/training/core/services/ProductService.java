package com.webjump.training.core.services;

import com.webjump.training.core.models.ProductOfMine;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;

public interface ProductService {
   void saveProduct(SlingHttpServletRequest req) throws PersistenceException;
}
