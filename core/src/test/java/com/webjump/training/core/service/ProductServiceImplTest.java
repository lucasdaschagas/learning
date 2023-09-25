package com.webjump.training.core.service;

import com.webjump.training.core.services.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.*;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;


@ExtendWith({MockitoExtension.class, AemContextExtension.class})
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl product;

    @Mock
    private ResourceResolverFactory factory;

    private final AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void setup() {
        context.registerService(ResourceResolverFactory.class, factory);

    }

    @Test
    void testSaveProduct() throws IOException {
        context.create().resource("/content/test", "jcr:title", "ServiceTest","name","","description","");

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();
        request.setMethod("POST");
        request.addRequestParameter("name", "anotherName");
        request.addRequestParameter("description", "anotherOneDescription");
        request.setContentType("application/json");

        product.saveProduct(request, response);
        response.setContentType("application/json");

    }
}