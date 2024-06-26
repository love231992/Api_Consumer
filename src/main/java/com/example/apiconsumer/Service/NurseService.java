package com.example.apiconsumer.Service;

import com.example.apiconsumer.Dto.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;

@Service
public class NurseService {

    @Autowired
    WebClient webClient;

    public Nurse getNurseByFirstName(String firstName){
       return webClient.get()
                .uri("http://localhost:8080/getNurseByFirstName/"+firstName)
                .retrieve()
                .bodyToMono(Nurse.class)
                .block();
    }
    public List<Nurse> getNurses(){
        Flux<Nurse> nurseFlux = webClient.get()
                .uri("http://localhost:8080/allNurses")
                .retrieve()
                .bodyToFlux(Nurse.class);
        return nurseFlux.collectList().block();
    }

    public void delNurse(Nurse nurse){
        webClient.method(HttpMethod.DELETE)
                .uri("http://localhost:8080/deleteNurse")
                .body(Mono.just(nurse), Nurse.class)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    public Mono<Void> saveNurse(Nurse nurse){
        return webClient.post()
                .uri("http://localhost:8080/saveNurse")
                .body(Mono.just(nurse), Nurse.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> updateNurse(Nurse nurse){
        return webClient.patch()
                .uri("http://localhost:8080/updateNurse")
                .body(Mono.just(nurse), Nurse.class)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
