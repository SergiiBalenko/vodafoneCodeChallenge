package com.balenko.vodafone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    private Error error;
    private T data;

    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(Error error) {
        this.error = error;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private int code;
        private String message;
    }

}


