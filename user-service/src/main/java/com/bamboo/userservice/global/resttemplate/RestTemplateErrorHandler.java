package com.bamboo.userservice.global.resttemplate;

import com.bamboo.userservice.global.exception.GlobalException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        final HttpStatus httpStatus = response.getStatusCode();
        return !httpStatus.is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        final String error = getErrorAsString(response);
        log.error("Headers: {}", response.getHeaders());
        log.error("Response failed: {}", error);
        if(error.equals("{\"status\":403,\"message\":\"변조된 code입니다\"}")){
            throw new GlobalException(HttpStatus.BAD_REQUEST, "변조된 code입니다.");
        }
        throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 통신 중 오류");
    }

    private String getErrorAsString(@NonNull final ClientHttpResponse response)
            throws IOException {
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody()))) {
            return bufferedReader.readLine();
        }
    }
}