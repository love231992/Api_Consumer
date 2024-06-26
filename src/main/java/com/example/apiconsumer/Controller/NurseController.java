package com.example.apiconsumer.Controller;

import com.example.apiconsumer.Dto.Nurse;
import com.example.apiconsumer.Service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


import java.util.List;

@RestController
public class NurseController {

    @Autowired
    NurseService nurseService;

    @GetMapping("/getNurseByFirstName/{firstName}")
    public Nurse getNurseByFirstName(@PathVariable String firstName){
       return nurseService.getNurseByFirstName(firstName);
    }

    @GetMapping("/getNurses")
    public List<Nurse> getNurses(){
        return nurseService.getNurses();
    }

    @DeleteMapping("/delNurse")
    public ResponseEntity<String> delNurse(@RequestBody Nurse nurse){
          nurseService.delNurse(nurse);
        return  ResponseEntity.status(HttpStatus.OK).body("Nurse data has been deleted");
    }

    @PostMapping("/saveNurse")
    public Mono<Void> saveNurse(@RequestBody Nurse nurse){
      return nurseService.saveNurse(nurse);
    }

    @PatchMapping("/updateNurse")
    public Mono<Void> updateNurse(@RequestBody Nurse nurse){
        return nurseService.updateNurse(nurse);
    }

}
