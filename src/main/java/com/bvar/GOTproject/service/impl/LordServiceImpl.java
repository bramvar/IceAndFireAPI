package com.bvar.GOTproject.service.impl;

import com.bvar.GOTproject.model.Lord;
import com.bvar.GOTproject.service.LordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class LordServiceImpl implements LordService {
    private WebClient webClient;

    @Override
    public Lord getLordByUrl(String url) {
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Lord.class).block();
    }
}
