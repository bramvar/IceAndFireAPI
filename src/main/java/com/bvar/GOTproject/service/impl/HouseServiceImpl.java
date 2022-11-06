package com.bvar.GOTproject.service.impl;

import com.bvar.GOTproject.model.House;
import com.bvar.GOTproject.model.Lord;
import com.bvar.GOTproject.service.HouseService;
import com.bvar.GOTproject.service.LordService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.bvar.GOTproject.constants.Routes.GOT_API_HOUSES_ROUTE;

@Service
@AllArgsConstructor
public class HouseServiceImpl implements HouseService {

    private WebClient webClient;

    private LordService lordService;

    @Override
    public Flux<House> getHousesRaw(String url) {
        LoggerFactory.getLogger(HouseServiceImpl.class).info("Calling All Houses");
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(House.class);
    }

    @Override
    public Lord getHouseAndLord(House house) {
        LoggerFactory.getLogger(HouseServiceImpl.class).info(String.format("Calling House and lord (%s)", house.getName()));
        String lordUrl = house.getCurrentLordUrl();
        if(!lordUrl.isEmpty()){
            return lordService.getLordByUrl(lordUrl);
        }
        return null;
    }

    @Override
    @Cacheable(value="housesAndLords")
    public Flux<House> getHousesAndLordsInParallel() {
        Flux<House> houses = getHousesRaw(GOT_API_HOUSES_ROUTE);
        return houses.parallel()
                .runOn(Schedulers.boundedElastic())
                .doOnNext(i -> i.setLord(getHouseAndLord(i)))
                .ordered(((o1, o2) -> o1.getName().compareTo(o2.getName())));
    }
}
