package com.example.demo.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorMessages {

    private List<String> messages = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd 'T' hh:mm:ss")
    private Date date;

    private int statusCode;

}
