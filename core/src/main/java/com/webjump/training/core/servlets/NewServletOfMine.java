package com.webjump.training.core.servlets;


import com.day.cq.commons.jcr.JcrConstants;
import com.drew.lang.annotations.NotNull;
import com.webjump.training.core.exceptions.ErrorPersistingObjectException;
import com.webjump.training.core.services.ProductServiceImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes= "web-train/components/page",
        methods= {HttpConstants.METHOD_GET, HttpConstants.METHOD_POST}
        )
@SlingServletPaths("bin/servlets/myServlet")
public class NewServletOfMine extends SlingAllMethodsServlet {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private ProductServiceImpl service;

        public void bindService(ProductServiceImpl productService) {
                this.service = productService;
        }

        @Override
        protected void doPost(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws IOException {
                logger.info("<<<<<<<<<<<Doing Post>>>>>>>>>>>>>");

                try {

                        service.saveProduct(request, response);
                        response.setStatus(HttpServletResponse.SC_OK);
                } catch (ErrorPersistingObjectException e) {
                        e.getMessage();
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        throw new ErrorPersistingObjectException("Could not persist object, please try again");
                }
                response.getWriter().write("Post Ended");
                logger.info("<<<<<<<<<<<Post Ended>>>>>>>>>>>>>");
        }

        @Override
        protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws IOException {
                final Resource resource = request.getResource();
                response.setContentType("text/plain");
                response.getWriter().write("Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
        }

}


