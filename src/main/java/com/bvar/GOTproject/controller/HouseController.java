package com.bvar.GOTproject.controller;

import com.bvar.GOTproject.model.House;
import com.bvar.GOTproject.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class HouseController {

    private HouseService houseService;

    @GetMapping("/houses")
    public Flux<House> getCompleteHouses(){
       return houseService.getHousesAndLordsInParallel();
    }
}
