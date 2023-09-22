package com.webjump.training.core.servlets;
import com.day.cq.wcm.api.Page;
import com.webjump.training.core.models.ProductOfMine;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(AemContextExtension.class)
public class NewServletOfMineTest {

    private final AemContext context = new AemContext();
    private NewServletOfMine servlet;
    private ProductOfMine product;
    private String NAME = "name";
    private String DESCRIPTION = "description";

//    @BeforeEach
//    public void setup() {
//
//        Page page = context.create().page("/content/mypage");
//        Resource resource = context.create().resource(page, "product",
//                "sling:resourceType", "web-train/components/product-of-mine", "name", NAME,
//                "description", DESCRIPTION);
//
//        product = resource.adaptTo(ProductOfMine.class);
//
//    }

    @Test
    void doPostTest() throws IOException {
        context.create().resource("/content/servletTest","jcr:title", "test","name","name"
                ,"description","description");
        context.currentResource("/content/servletTest");

//        context.currentPage(context.pageManager().getPage("/content/mypage"));
//        context.currentResource(context.resourceResolver().getResource("web-train/components/product-of-mine"));
//        context.requestPathInfo().setResourcePath("bin/servlets/myServlet");

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        request.addHeader("name","anotherName");
        request.addHeader("description","anotherDescription");


        servlet.doPost(request,response);
    }
}
