package ch.hearc.votingservice.remote.impl;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignExceptionDecoder();
    }
}
