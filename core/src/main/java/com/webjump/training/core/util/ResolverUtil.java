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

    private static final String WEB_TRAIN_USER = "webtrainuser";

    public static ResourceResolver newResolver(ResourceResolverFactory factory) throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, WEB_TRAIN_USER);
        return factory.getServiceResourceResolver(paramMap);
    }

}
