/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.controller;

import com.frankfurter.domain.CurrencyDetails;
import com.frankfurter.enums.ResultCode;
import com.frankfurter.model.CurrencyModel;
import com.frankfurter.service.Services;
import com.frankfurter.util.DateCheckcer;
import com.frankfurter.util.OpenFeignClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author USER
 */
@RestController
public class CurrencyController {
    
    @Autowired
    Services iService;
    
    @Autowired
    OpenFeignClient feignClient;
    
    @GetMapping(value = "/{date}")
    public String getRate(@PathVariable String date){
        if(!DateCheckcer.dateValidation(date)){
            return "Invalid Date Format!!!";
        }
        
        String rate = iService.findByDate(date);
        if(rate !=null && rate!=""){
            return "USD: " + rate + " ON " + date;
        }        
        CurrencyDetails cd = feignClient.getRate(date);
        if(cd.getCode() == (ResultCode.ERROR)){
            return "Please try it later!!!";
        }
        CurrencyModel model = new CurrencyModel();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date requestDate = new Date();
        
        Map<String,Object> map = cd.getRates();
        model.setRate(map.get("USD").toString());
        model.setRateDate(date);
        model.setRequestDate(dateFormat.format(requestDate));
        
        iService.save(model);
        return "USD: " + map.get("USD").toString() + " ON " + date;
    }
    
    @GetMapping(value = "/getAllSearch")
    public List<CurrencyModel> getAll(){
        List<CurrencyModel> list = iService.listAll();
        return list;
    }
}
