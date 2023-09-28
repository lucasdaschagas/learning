package com.webjump.training.core.service;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webjump.training.core.models.ProductOfMine;
import com.webjump.training.core.services.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.*;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONObject;

@ExtendWith({MockitoExtension.class, AemContextExtension.class})
public class ProductServiceImplTest {

    private ProductServiceImpl product;
    private final AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
    private Resource resource;

    @BeforeEach
    public void setup() {

        product = context.registerService(new ProductServiceImpl());
//        context.load().json("src/test/resources/com.webjump.training.core.models.impl/ProductOfMineJsonTest.json","/content");
        context.addModelsForClasses(ProductOfMine.class);
        resource=  context.create().resource("/content/product_of_mine", "sling:resourceType", "web-train/components/product-of-mine",
                "name","Coffin Tester",
                "description","New rentable job",
                "jcr:primaryType","nt:unstructured");


//        Page page = context.create().page("/content/mypage");
//        Resource resource = context.create().resource(page, "product",
//                "sling:resourceType", "web-train/components/product-of-mine", "name", "",
//                "description", "");

    }

    @Test
    void testSaveProduct() throws IOException, JSONException {

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        request.setResource(resource);
        request.addRequestParameter("name", "anotherName");
        request.addRequestParameter("description", "anotherOneDescription");
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.setContentType("application/json");
        request.setMethod("POST");

        JSONObject jsonContent = new JSONObject();
        jsonContent.put("name", request.getRequestParameter("name"));
        jsonContent.put("description", request.getRequestParameter("description"));

        request.setContent(jsonContent.toString().getBytes());

        product.saveProduct(request, response);

        response.setContentType("application/json");

        String responseOutputName = String.valueOf(response.getOutputAsString());

        assertEquals(200, response.getStatus(), "O status da resposta não é 200");
        assertNotNull(responseOutputName, "O conteúdo da resposta está nulo");
        assertNotNull(responseOutputName, "O conteúdo da resposta está nulo");

        System.out.println("resource path is " + resource.getPath());
        System.out.println("resource type is " + resource.getResourceType());
        System.out.println(responseOutputName);


    }
}