package com.shgxzybaba.musalasoft.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiResponseBody {

    private boolean success;
    private int statusCode;
    private Object body;
    private List<String> errors = new ArrayList<>();
}
