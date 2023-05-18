package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final WebClient webClient;

    @Bean
    public void post() {
        System.out.println("SENT");
        Request[] requests = webClient.method(HttpMethod.GET)
                .uri("https://procodeday-01.herokuapp.com/meet-up/get-country-list")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Request[].class)
                .block();

        Student student = Student.builder()
                .name("Tilek Zholdoshbek")
                .phone("0700705022")
                .github_url("https://github.com/Zholdoshbek-t")
                .build();

        HashMap<String, List<String>> countryCities = new HashMap<>();

        for(Request request: requests) {
            if(countryCities.containsKey(request.getCountry())) {
                countryCities.get(request.getCountry()).add(request.getCity());
            } else {
                List<String> cities = new ArrayList<>();;
                countryCities.put(request.getCountry(), cities);
                countryCities.get(request.getCountry()).add(request.getCity());
            }
        }

        Response response = new Response();
        response.setStudent(student);
        List<Result> results = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: countryCities.entrySet()) {
            Result result = Result.builder()
                    .country(entry.getKey())
                    .cities(entry.getValue())
                    .cities_count(entry.getValue().size())
                    .build();
            results.add(result);
        }
        response.setResult(results);

        Response response1 = webClient.post()
                .uri("https://procodeday-01.herokuapp.com/meet-up/post-request")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .retrieve()
                .bodyToMono(Response.class)
                .block();
        System.out.println(response1);
    }

}
