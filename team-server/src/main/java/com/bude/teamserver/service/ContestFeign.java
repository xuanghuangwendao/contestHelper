package com.bude.teamserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "contest-server", url = "localhost:7002")
public interface ContestFeign {
    @PostMapping("/getName")
    String getName(@RequestParam Integer contestId);
}
