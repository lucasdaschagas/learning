package com.webjump.training.core.models;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
public class ProductOfMineModelTest {

    private final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);
    private ProductOfMine product;

    @BeforeEach
    public void setup() {
        context.addModelsForClasses(ProductOfMine.class);
        context.load().json("src/test/resources/com.webjump.training.core.models.impl/ProductOfMineJsonTest.json","/content");

    }

    @Test
    void testGetName() {
        Resource resource = context.resourceResolver().getResource("/content/product_of_mine");
        assert resource != null;
        product = resource.adaptTo(ProductOfMine.class);
        System.out.println("resource path is " + resource.getPath());
        System.out.println("resource type is " + resource.getResourceType());


        final String expected = "Coffin Tester";
        String actual = product.getName();

        assertEquals(expected,actual);
    }

    @Test
    void testGetDescription(){
        Resource resource = context.resourceResolver().getResource("/content/product_of_mine");
        assert resource != null;
        product = resource.adaptTo(ProductOfMine.class);
        System.out.println("resource path is " + resource.getPath());
        System.out.println("resource type is " + resource.getResourceType());

        final String expected = "New rentable job";
        String actual = product.getDescription();

        assertEquals(expected,actual);

    }
}
