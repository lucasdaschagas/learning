package com.webjump.training.core.service;

import com.webjump.training.core.services.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@ExtendWith({MockitoExtension.class, AemContextExtension.class})
public class ProductServiceImplTest {

    @Mock
    private ResourceResolver resolver;
    @Mock
    private Resource resource;
    @Mock
    private ResourceResolverFactory factory;
    @InjectMocks
    private ProductServiceImpl product;
    private AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void setup() {
        context.registerService(ResourceResolverFactory.class, factory);
    }

    @Test
    void testSaveProduct() throws PersistenceException, JSONException {
            String requestJson = "{\"jcr:title\"ProductOfMine\"," +
                                 "\"primaryType\":\"cq:Component\","+
                                 "\"name\":\"otherName\"," +
                                 "\"description\":\"anotherDescription\"}";


            MockSlingHttpServletRequest request = context.request();
            MockSlingHttpServletResponse response = context.response();
            request.setMethod("POST");
            request.addRequestParameter("name","anotherName");
            request.addRequestParameter("description","anotherDescriprion");
            request.addHeader("Content-Type", "application/json");
            request.setCharacterEncoding("UTF-8");
            request.setContent(requestJson.getBytes());



            product.saveProduct(request,response);


        }
    }