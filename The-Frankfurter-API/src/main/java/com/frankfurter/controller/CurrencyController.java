/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.controller;

import com.frankfurter.model.CurrencyModel;
import com.frankfurter.service.Services;
import com.frankfurter.util.DateCheckcer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
public class CurrencyController {
    
    @Autowired
    Services iService;
    
    @GetMapping(value = "/{date}")
    public String getRate(@PathVariable String date){
        //checking date format
        if(!DateCheckcer.dateValidation(date)){
            return "Invalid Date Format!!!";
        }
        String response = iService.findByDate(date);

        return response;
    }
    
    @GetMapping(value = "/getAllSearch")
    public List<CurrencyModel> getAll(){
        List<CurrencyModel> list = iService.listAll();
        return list;
    }

}
