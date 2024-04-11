package ch.hearc.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("admin-service", r -> r.path("/admin/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://ADMIN-SERVICE"))
                .route("voting-service", r -> r.path("/voting/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://VOTING-SERVICE"))
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
