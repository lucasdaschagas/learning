package com.webjump.training.core.service;

import com.webjump.training.core.services.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith({MockitoExtension.class, AemContextExtension.class})
public class ProductServiceImplTest {

//    @Mock
//    private ResourceResolver resolver;
//    @Mock
//    private Resource resource;
    @Mock
    private ResourceResolverFactory factory;


    @InjectMocks
    private ProductServiceImpl product;
    private AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void setup(){
        context.registerService(ResourceResolverFactory.class, factory);
//        product.bindResource(resource);
//        product.bindResourceResolver(resolver);

    }

    @Test
    void testSaveProduct() throws PersistenceException {
        context.build().resource("/content/test", "jcr:title", "productServiceImplTest","name","","description","").commit();
        context.currentResource("/content/test");

        MockSlingHttpServletRequest request = context.request();
        request.setHeader("name", "otherName");
        request.setHeader("description", "anotherDescription");

        product.saveProduct(request);
    }
}
