package com.example.demo;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String country;
    private List<String> cities;
    private int cities_count;
}
