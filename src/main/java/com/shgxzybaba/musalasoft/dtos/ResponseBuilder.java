package com.shgxzybaba.musalasoft.dtos;

public class ResponseBuilder {

    public static ApiResponseBody buildResponse(Throwable exception, int httpCode) {
        final ApiResponseBody output = new ApiResponseBody();
        output.setSuccess(false);
        output.setStatusCode(httpCode);
        String exceptionMessage = exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage();
        output.getErrors().add(exceptionMessage);

        return output;
    }
}
