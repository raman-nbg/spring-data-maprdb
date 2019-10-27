package com.mapr.springframework.data.maprdb.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ojai.store.Connection;

public class MapROptions {
    private ObjectMapper objectMapper;
    private String databaseName;
    private Connection ojaiConnection;
    private DrillConnectionFactory drillConnectionFactory;
    private boolean autoCreateTables;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Connection getOjaiConnection() {
        return ojaiConnection;
    }

    public void setOjaiConnection(Connection ojaiConnection) {
        this.ojaiConnection = ojaiConnection;
    }

    public DrillConnectionFactory getDrillConnectionFactory() {
        return drillConnectionFactory;
    }

    public void setDrillConnectionFactory(DrillConnectionFactory drillConnectionFactory) {
        this.drillConnectionFactory = drillConnectionFactory;
    }

    public boolean getAutoCreateTables() {
        return autoCreateTables;
    }

    public void setAutoCreateTables(boolean autoCreateTables) {
        this.autoCreateTables = autoCreateTables;
    }
}
