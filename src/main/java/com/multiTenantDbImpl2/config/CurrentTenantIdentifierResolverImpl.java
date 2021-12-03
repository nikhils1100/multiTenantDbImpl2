package com.multiTenantDbImpl2.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Configuration;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT_ID = "persistence-tenant_book_default";

    @Override
    public String resolveCurrentTenantIdentifier() {
        //DbContextHolder.setCurrentDb(DEFAULT_TENANT_ID);
        String tenant = DbContextHolder.getCurrentDb();
        if(tenant==null)
            tenant = DEFAULT_TENANT_ID;
        return tenant.isEmpty() || tenant.isBlank()? DEFAULT_TENANT_ID : tenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
