package com.javbus.server;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ServiceFilter implements GatewayFilter, Ordered{

	@Override
	public int getOrder() {
		// 给过滤器设置优先级，值越低优先级越高
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("》》》》》》》》》》》》》》》》》》》》》》》  进入过滤器：");
		return null;
	}
	
}
