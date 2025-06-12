package com.example.appeal_service.feign;

import com.example.appeal_service.DTOs.feign.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="demo", url="${demo.service.url}")
public interface DemoClient {


    @GetMapping(path = "/api/accounts/get-user-account/{id}")
    List<AccountDTO> getAccountByUserId(@PathVariable("id") Long userId,@RequestHeader("Authorization") String authHeader);

}
