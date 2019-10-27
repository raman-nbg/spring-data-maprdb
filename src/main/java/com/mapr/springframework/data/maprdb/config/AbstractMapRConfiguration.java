package com.mapr.springframework.data.maprdb.config;
import com.mapr.springframework.data.maprdb.core.MapROperations;
import com.mapr.springframework.data.maprdb.core.MapROptions;
import com.mapr.springframework.data.maprdb.core.MapRTemplate;
import org.ojai.store.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Base class for Spring Data MapR-DB configuration.
 */
@Configuration
public abstract class AbstractMapRConfiguration {
    public abstract MapROperations maprOperations(MapROptions mapROptions);

    protected String[] getEntityBasePackages() {
        return new String[] { getClass().getPackage().getName() };
    }
}
