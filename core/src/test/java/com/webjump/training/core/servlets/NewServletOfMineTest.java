package com.webjump.training.core.servlets;

import com.webjump.training.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.servlet.ServletException;
import java.io.IOException;

@ExtendWith(AemContextExtension.class)
public class NewServletOfMineTest {

    private AemContext context = AppAemContext.newAemContext();
    private NewServletOfMine servlet;

    @BeforeEach
    void setup(){
        context.build().resource("/content/test", "jcr:title", "web-page");
        context.currentResource("/content/test");
    }

    @Test
    void doPostTest() throws ServletException, IOException {
        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        request.addHeader("name","name");
        request.addHeader("description","description");


        servlet.doPost(request,response);
    }
}
