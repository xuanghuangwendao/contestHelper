package com.bude.teamserver.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server", url = "localhost:7001")
public interface UserFeign {
    @PostMapping("/getName")
    String getName(@RequestParam Integer userId);
}
