package com.webjump.training.core.servlets;
import com.webjump.training.core.services.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;



@ExtendWith({AemContextExtension.class, MockitoExtension.class})
public class NewServletOfMineTest {

    @Mock
    private ResourceResolverFactory factory;
    @Mock
    private ProductServiceImpl productService;
    private final AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
    @InjectMocks
    private NewServletOfMine servlet = new NewServletOfMine();


    @BeforeEach
    public void setup() {
        context.registerService(ResourceResolverFactory.class, factory);
        servlet.bindService(productService);

    }

    @Test
    void doPostTest() throws IOException {


        context.build().resource("/content/test", "jcr:title", "servletTest","name","","description","").commit();
        context.currentResource("/content/test");

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        request.addRequestParameter("name","anotherName");
        request.addRequestParameter("description","anotherDescription");

        servlet.doPost(request,response);

    }
}
