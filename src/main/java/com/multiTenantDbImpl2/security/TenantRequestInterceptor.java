package com.multiTenantDbImpl2.security;

import com.multiTenantDbImpl2.config.DbContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {

    public TenantRequestInterceptor() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader("X-TENANT-ID");
        return Optional.ofNullable(request)
                .map(req -> req.getHeader("X-TENANT-ID"))
                .map(tenant -> setTenantContext("persistence-tenant_book_".concat(tenant)))
                .orElse(false);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        DbContextHolder.clear();
    }

    private boolean setTenantContext(String tenant) {
        DbContextHolder.setCurrentDb(tenant);
        return true;
    }
}
