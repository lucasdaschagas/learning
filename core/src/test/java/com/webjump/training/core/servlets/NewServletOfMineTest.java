package com.webjump.training.core.servlets;
import com.webjump.training.core.services.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith({AemContextExtension.class, MockitoExtension.class})
public class NewServletOfMineTest {
    @Mock
    private ProductServiceImpl productService;
    private final AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
    @InjectMocks
    private NewServletOfMine servlet;
    private Resource resource;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        servlet  = context.registerService(new NewServletOfMine());
        servlet.bindService(productService);

    }

    @Test
    void doPostTest() throws IOException {

        resource = context.create().resource("/content/test", "jcr:title", "servletTest","name","","description","");
        context.currentResource("/content/test");

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        request.addRequestParameter("name","anotherName");
        request.addRequestParameter("description","anotherDescription");

        servlet.doPost(request,response);

        response.setContentType("application/json");

        String responseOutputName = String.valueOf(response.getOutputAsString());

        assertEquals(200, response.getStatus(), "O status da resposta não é 200");
        assertEquals("Post Ended",response.getOutputAsString());
        assertNotNull(responseOutputName, "O conteúdo da resposta está nulo");
        assertNotNull(responseOutputName, "O conteúdo da resposta está nulo");

    }
}
