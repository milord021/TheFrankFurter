/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.util;

import com.frankfurter.domain.CurrencyDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "frankfurter-client", url = "https://api.frankfurter.app/")
public interface OpenFeignClient {
    @GetMapping(value = "/{date}")
    public CurrencyDetails getRate(@PathVariable String date);
}
