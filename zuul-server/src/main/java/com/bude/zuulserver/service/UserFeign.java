package com.bude.zuulserver.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server", url = "119.3.157.136:7001")
public interface UserFeign {
    @PostMapping("/getName")
    String getName(@RequestParam Integer userId);

    @PostMapping("/getSign")
    String getSign(@RequestParam Integer userId);
}
