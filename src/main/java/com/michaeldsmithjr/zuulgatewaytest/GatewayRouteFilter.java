package com.michaeldsmithjr.zuulgatewaytest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GatewayRouteFilter extends ZuulFilter {

    private Map<String,String> rewriteRules = new HashMap<String, String>()
    {
        {
            put("/api/users", "/users");
            put("/api/devices", "/devices");
        }
    };

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 20;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String[] parts = ctx.getRequest().getRequestURI().split("/");
        String path = "/"+parts[1]+"/"+parts[2];
        return rewriteRules.containsKey(path);
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        String[] parts = context.getRequest().getRequestURI().split("/");
        String path = "/"+parts[1]+"/"+parts[2];
        String contextURI = (String) context.get("requestURI");
        if (contextURI != null) {
            if (rewriteRules.containsKey(path)) {
                context.put("requestURI", rewriteRules.get(path) + contextURI);
            }
        }

        return null;
    }
}
