package com.webjump.training.core.servlets;

import com.webjump.training.core.exceptions.RandomServiceErrorException;
import com.webjump.training.core.services.RandomNumber;
import org.json.JSONException;
import org.json.JSONObject;
import com.webjump.training.core.services.RandomNumberImpl;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletName;
import org.osgi.framework.Constants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

//@Component(service=Servlet.class,
//        properties={
//                Constants.SERVICE_DESCRIPTION + "=Test Cache Servlet",
//                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
//                "sling.servlet.paths=" + "/bin/servlets/path",
//                Constants.EXTENSION_DIRECTIVE + "=json"
//        }
//)
@Component(service = { Servlet.class },
        property = { Constants.SERVICE_DESCRIPTION + "=Test Cache Servlet",
                                                    "sling.servlet.paths=" + "/bin/servlets/path",
                                                    "sling.servlet.methods=" + HttpConstants.METHOD_GET
                                                    })
//@SlingServletResourceTypes(resourceTypes = {"/bin/servlets/path"},
//                           methods = HttpConstants.METHOD_GET)
@SlingServletName(servletName = "RandomServlet")
public class RandomNumberServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private RandomNumber number;


    @Override
    protected void doGet(SlingHttpServletRequest request,  SlingHttpServletResponse response) throws IOException {
        Integer x = number.getRandomNumber();

        if ( x != null) {


            response.setHeader("Cache-Control", "max-age=3600");

            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("randomNumber", x.toString());
                response.setStatus(HttpServletResponse.SC_OK);

            } catch (JSONException e) {
                throw new RandomServiceErrorException("Json Could not be created");
            }
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jsonObject.toString());

        } else {
            response.getWriter().write("Service not initialized");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}


