/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.service;

import com.frankfurter.domain.CurrencyDetails;
import com.frankfurter.enums.ResultCode;
import com.frankfurter.model.CurrencyModel;
import com.frankfurter.repository.ICurrencyRepository;
import com.frankfurter.util.OpenFeignClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USER
 */
@Service
@Transactional
public class Services {

    @Autowired
    ICurrencyRepository repository;

    @Autowired
    OpenFeignClient feignClient;

    public void save(String rate, String date) {
        //getting request date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date requestDate = new Date();
        CurrencyModel model = new CurrencyModel();
        model.setRate(rate);
        model.setRateDate(date);
        model.setRequestDate(dateFormat.format(requestDate));
        repository.save(model);
    }

    public List<CurrencyModel> listAll() {
        return repository.findAllByOrderByRateDateDesc();
    }

    public String findByDate(String date) {
        //checking local db to get data
        String rate = repository.findByRateDate(date);
        //creating persistence object
        if (rate != null && rate != "") {
            //if callback saving to db
            save(rate, date);
            return "USD: " + rate + " ON " + date;
            
        } else {
            //getting data from outer api
            CurrencyDetails cd = feignClient.findByDateAPI(date);
            if (cd.getCode() == (ResultCode.ERROR)) {
                return "Please try it later!!!";
            }
            //saving to db
            Map<String, Object> map = cd.getRates();
            save(map.get("USD").toString(), date);
            return "USD: " + map.get("USD").toString() + " ON " + date;
        }
    }
}
