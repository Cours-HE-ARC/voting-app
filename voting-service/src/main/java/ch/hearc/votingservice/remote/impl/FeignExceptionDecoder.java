package ch.hearc.votingservice.remote.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.NotFoundException;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.io.InputStream;

public class FeignExceptionDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException();
            case 404:
                return new Error400Exception("NOot found");
            default:
                return new Exception("Exception while calling service");
        }
    }
}

