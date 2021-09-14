package com.karasuno.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        exposedIds(config);
    }

    private void exposedIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entityTypeSet = entityManager.getMetamodel().getEntities();

        List<Class<?>> entityClasses = new ArrayList<>();

        entityTypeSet.forEach(entityType -> entityClasses.add(entityType.getJavaType()));

        Class<?>[] domainType = entityClasses.toArray(new Class[0]);

        config.exposeIdsFor(domainType);
    }
}
