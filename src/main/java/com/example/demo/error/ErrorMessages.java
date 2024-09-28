package com.example.demo.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorMessages {

    private List<String> messages = new ArrayList<>();

    private Date date;

    private int statusCode;

}
