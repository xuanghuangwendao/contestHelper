package com.bude.communityserver.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-server")
public interface UserFeign {
    @PostMapping("/getName")
    String getName(@RequestParam Integer userId);
}
