package io.github.luolong47.dsmsbackend.interceptor;

import cn.hutool.jwt.JWTUtil;
import io.github.luolong47.dsmsbackend.model.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class JwtInterceptor implements WebFilter {

    private static final String JWT_SECRET = "your-secret-key";
    // 不需要验证的路径
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
        "/user/login",
        "/user/out"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        
        // 如果是排除的路径，直接放行
        if (EXCLUDE_PATHS.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        // 获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || token.isEmpty()) {
            return unauthorized(exchange);
        }

        // 验证token
        try {
            boolean verify = JWTUtil.verify(token, JWT_SECRET.getBytes());
            if (!verify) {
                return unauthorized(exchange);
            }
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("Token验证失败", e);
            return unauthorized(exchange);
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().writeWith(
            Mono.just(exchange.getResponse().bufferFactory().wrap(
                R.fail("未授权或token已过期").toString().getBytes()
            ))
        );
    }
} 