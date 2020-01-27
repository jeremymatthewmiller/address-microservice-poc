package com.grainger.zuul.filters;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrackingFilter extends ZuulFilter {

	private static final boolean SHOULD_FILTER = true;
	private static final int FILTER_ORDER = 1;

	@Autowired
	FilterUtils filterUtils;

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
        log.info("Processing incoming request for {}.",  ctx.getRequest().getRequestURI());
        return null;
	}

	@Override
	public String filterType() {
		return FilterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

}
