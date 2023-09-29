package com.webjump.training.core.util;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Map;


public class ResolverUtil {


    public ResolverUtil() {
    }

    public static final String WEB_TRAIN_USER = "webtrainuser";

    public static ResourceResolver newResolver(ResourceResolverFactory factory) throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, WEB_TRAIN_USER);
        return factory.getServiceResourceResolver(paramMap);
    }
// WARNING
//    For the scheduler to run properly, create a new user on the localhost:4502/crx/explorer/index.jsp with
//    the name of your preference and path "system" and assign his permissions on localhost:4502/useradmin,
//    then you need to declare him in localhost:4502/system/console/configMgr go to mapper service amendment, and declare
//    like this example: projectName.core:youUserToBeUsingInCode=yourSystemUser, only then this class will have an effect
}
