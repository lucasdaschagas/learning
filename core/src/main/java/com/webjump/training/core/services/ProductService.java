package com.webjump.training.core.services;

import com.webjump.training.core.models.ProductOfMine;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;

import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;

@ServiceMode(Service.Mode.PAYLOAD)
public interface ProductService {
   void saveProduct(SlingHttpServletRequest req) throws PersistenceException;
}
