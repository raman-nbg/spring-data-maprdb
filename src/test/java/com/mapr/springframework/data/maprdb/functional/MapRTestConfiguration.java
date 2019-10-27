package com.mapr.springframework.data.maprdb.functional;

import com.mapr.springframework.data.maprdb.config.AbstractMapRConfiguration;
import com.mapr.springframework.data.maprdb.core.MapROperations;
import com.mapr.springframework.data.maprdb.core.MapROptions;
import com.mapr.springframework.data.maprdb.core.MapRTemplate;
import com.mapr.springframework.data.maprdb.repository.config.EnableMapRRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableMapRRepository
@PropertySource("classpath:tests.properties")
public class MapRTestConfiguration extends AbstractMapRConfiguration {

    @Value("${database.name}")
    public String databaseName;

    @Value("${database.host}")
    public String databaseHost;

    @Value("${database.username}")
    public String databaseUsername;

    @Value("${database.password}")
    public String databasePassword;

    @Override
    public MapROperations maprOperations(MapROptions mapROptions) {
        MapROptions options = new MapROptions();
        options.setDatabaseName(databaseName);

        return new MapRTemplate(options);
    }
}
