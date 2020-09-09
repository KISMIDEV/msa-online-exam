package com.github.kingjava.common.security.tenant;

/**
 * ThreadLocal저장租户code
 *
 * @author kingjava
 * @date 2019/5/28 20:54
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenantCode(String tenantCode) {
        CONTEXT.set(tenantCode);
    }

    public static String getTenantCode() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
