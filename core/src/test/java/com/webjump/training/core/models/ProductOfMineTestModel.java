package com.webjump.training.core.models;

import com.day.cq.wcm.api.Page;
import com.webjump.training.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
public class ProductOfMineTestModel {

    private final AemContext context = AppAemContext.newAemContext();

    private ProductOfMine product;

    private final String NAME = "name";
    private final String DESCRIPTION = "description";

    @BeforeEach
    public void setup() throws Exception {

        Page page = context.create().page("/content/mypage");
        Resource resource = context.create().resource(page, "product",
                "sling:resourceType", "web-train/components/product-of-mine", "name", NAME,
                "description", DESCRIPTION);

        product = resource.adaptTo(ProductOfMine.class);
    }

    @Test
    void testGetName() throws Exception {
        String nameMsg = product.getName();
        assertNotNull(nameMsg);
        assertEquals(nameMsg, NAME);
    }

    @Test
    void testGetDescription() throws Exception{
        String descriptionMsg = product.getDescription();
        assertNotNull(descriptionMsg);
        assertEquals(descriptionMsg, DESCRIPTION);

    }
}
