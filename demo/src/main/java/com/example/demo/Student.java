package com.example.demo;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private String phone;
    private String github_url;

}
