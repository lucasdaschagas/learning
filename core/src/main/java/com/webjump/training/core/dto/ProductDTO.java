package com.webjump.training.core.dto;

import com.drew.lang.annotations.NotNull;
import com.webjump.training.core.models.ProductOfMine;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Designate(ocd = ProductOfMine.class)
public class ProductDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;

    public ProductDTO() {
    }




    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
