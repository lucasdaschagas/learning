package com.webjump.training.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.webjump.training.core.models.ProductOfMine;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;




import java.io.IOException;



@Component(service = ProductService.class)
public class ProductServiceImpl implements ProductService {

    @Reference
    private ResourceResolver resolver;
    @Reference
    private Resource resource;
    public void bindResourceResolver(ResourceResolver resolver){
        this.resolver = resolver;
    }public void bindResource(Resource resource){
        this.resource = resource;
    }


    @Override
    public void saveProduct(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws PersistenceException {
        resource = req.getResource();
        resolver = req.getResourceResolver();

        if (resource != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ProductOfMine product = mapper.readValue(req.getReader(), ProductOfMine.class);


                ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
                assert valueMap != null;
                valueMap.put("name".toLowerCase(), product.getName());
                valueMap.put("description".toLowerCase(), product.getDescription());


                JSONObject productJson = new JSONObject();
                productJson.put("jcr:title", "ProductOfMine");
                productJson.put("jcr:primaryType","cq:Component");
                productJson.put("name", product.getName());
                productJson.put("description", product.getDescription());

                resp.setContentType("application/json");
                resp.getWriter().write(productJson.toString());

                resolver.commit();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                throw new PersistenceException("Error trying to persist product " + e.getMessage());
            }

            resolver.close();
        }
    }
}
