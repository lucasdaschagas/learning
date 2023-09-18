package com.webjump.training.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = Resource.class, resourceType = "web-train/components/product-of-mine", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json")
public class ProductOfMine {

    @ValueMapValue
    protected String name;

    @ValueMapValue
    protected String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}

