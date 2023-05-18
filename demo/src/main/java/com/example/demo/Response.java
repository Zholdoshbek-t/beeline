package com.example.demo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Student student;

    private List<Result> result;
}
