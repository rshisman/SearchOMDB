package com.example.movielocator.util;

import org.springframework.http.ResponseEntity;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class RESTApiResponse<T> {

    private T resultData;
    private String status;

    public RESTApiResponse(String statusCode, T resultData) {
        this.status = statusCode;
        this.resultData = resultData;
    }

    public RESTApiResponse(ResponseEntity springResponse, T resultData) {
        this.status = springResponse.getStatusCode().getReasonPhrase();
        this.resultData = resultData;
    }

    public boolean isSuccess() {
        return status.equals(Response.Status.OK.getReasonPhrase());
    }

    public Optional<T> getResultData() {

        if(resultData==null){
            return Optional.empty();
        }else {
            return Optional.of(resultData);
        }    }

    public String getStatus() {
        return status;
    }

    public boolean isUnauthorized() {
        return status.equals(Response.Status.UNAUTHORIZED.getReasonPhrase());
    }
}
