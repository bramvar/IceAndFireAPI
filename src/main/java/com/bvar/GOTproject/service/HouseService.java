package com.bvar.GOTproject.service;

import com.bvar.GOTproject.model.House;
import com.bvar.GOTproject.model.Lord;
import reactor.core.publisher.Flux;

public interface HouseService {
    Flux<House> getHousesRaw(String url);
    Lord getHouseAndLord(House house);
    Flux<House> getHousesAndLordsInParallel();
}
