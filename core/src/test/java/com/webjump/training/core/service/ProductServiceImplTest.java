package com.webjump.training.core.service;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.Assert.fail;

@ExtendWith({MockitoExtension.class, AemContextExtension.class})
public class ProductServiceImplTest {

    @Mock
    private ResourceResolver resolver;
    @Mock
    private Resource resource;
    @InjectMocks
    private ProductServiceImplTest product;
    private AemContext context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);

    @BeforeEach
    public void setup(){}

    @Test
    void testSaveProduct(){
        fail("not yet implemented");
    }
}
