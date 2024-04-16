package ch.hearc.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    @Profile("routes & dsl")
    @Bean
    public RouteLocator stripPrefixRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("admin-service", r -> r.path("/admin/**")
                        .filters(
                                f -> f.stripPrefix(1))
                        .uri("lb://ADMIN-SERVICE"))
                .route("voting-service", r -> r.path("/voting/**")
                        .filters(
                                f -> f.stripPrefix(1))
                        .uri("lb://VOTING-SERVICE"))
                .build();
    }

    @Profile("status-code & dsl")
    @Bean
    public RouteLocator statusCodeRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("admin-service", r -> r.path("/admin/**")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .setStatus(201))
                        .uri("lb://ADMIN-SERVICE"))
                .route("voting-service", r -> r.path("/voting/**")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .setStatus(201))
                        .uri("lb://VOTING-SERVICE"))
                .build();
    }

    @Profile("security-header & dsl")
    @Bean
    public RouteLocator securityHeaderLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("admin-service",  r ->  r.path("/admin/**").and().header("AUTH-KEY","1234")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .setStatus(201))
                        .uri("lb://ADMIN-SERVICE"))
                .route("voting-service", r -> r.path("/voting/**")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .setStatus(201))
                        .uri("lb://VOTING-SERVICE"))
                .build();
    }

    @Profile("security-header-custom & dsl")
    @Bean
    public RouteLocator securityHeaderCustomBodyLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("admin-service",  r ->  r.path("/admin/**").and().header("AUTH-KEY","1234")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .setStatus(201))
                        .uri("lb://ADMIN-SERVICE"))
                .route("admin-service",  r ->  r.path("/admin/**")
                        .filters(
                                f -> f.
                                        setStatus(401).rewritePath("/admin(?<subpath>/?.*)","/error401/${subpath}"))
                        .uri("lb://API-GATEWAY"))
                .route("voting-service", r -> r.path("/voting/**")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .setStatus(201))
                        .uri("lb://VOTING-SERVICE"))
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }



}
