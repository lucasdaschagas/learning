package com.webjump.training.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.webjump.training.core.models.ProductOfMine;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;




import java.io.IOException;



@Component(service = ProductService.class)
public class ProductServiceImpl implements ProductService {

    @Reference
    private ResourceResolver resolver;
    @Reference
    private Resource resource;

    @Override
    public void saveProduct(SlingHttpServletRequest req) throws PersistenceException {
        resource = req.getResource();
        resolver = req.getResourceResolver();

        if (resource != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ProductOfMine product = mapper.readValue(req.getReader(), ProductOfMine.class);


                ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
                valueMap.put("name", product.getName());
                valueMap.put("description", product.getDescription());

                resolver.commit();
            } catch (IOException e) {
                e.printStackTrace();
                throw new PersistenceException("Error trying to persist product " + e.getMessage());
            }

            resolver.close();
        }
    }
}
