package com.gegette.springgcp;

import com.gegette.springgcp.service.UserRepositoryService;
import com.google.cloud.datastore.Datastore;
import org.springframework.cloud.gcp.data.datastore.core.DatastoreTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RepositoryServiceConfiguration {

    @Bean
    DatastoreTransactionManager datastoreTransactionManager(Datastore datastore) {
        return new DatastoreTransactionManager(datastore);
    }

    @Bean
    public UserRepositoryService userRepositoryService() {
        return new UserRepositoryService();
    }
}