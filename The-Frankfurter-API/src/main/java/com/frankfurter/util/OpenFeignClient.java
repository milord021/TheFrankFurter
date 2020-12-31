/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.util;

import com.frankfurter.domain.CurrencyDetails;
import com.frankfurter.enums.ResultCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//calling outer rest api
@FeignClient(name = "frankfurter-client", url = "${frankfurter.feign.clients.url}", fallback = HystrixClientFallback.class)
public interface OpenFeignClient {
    @GetMapping(value = "/{date}")
    public CurrencyDetails findByDateAPI(@PathVariable String date);
}

//error handling
@Component
class HystrixClientFallback implements OpenFeignClient {
    @Override
    public CurrencyDetails findByDateAPI(@PathVariable String date){
        CurrencyDetails details = new CurrencyDetails();
        details.setCode(ResultCode.ERROR);
        return details;
    }
}