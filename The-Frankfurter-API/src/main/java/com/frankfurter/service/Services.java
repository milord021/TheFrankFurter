/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.service;

import com.frankfurter.model.CurrencyModel;
import com.frankfurter.repository.ICurrencyRepository;
import java.util.List;
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
    
    public void save(CurrencyModel model) {
        repository.save(model);
    }
    
    public List<CurrencyModel> listAll() {
        return repository.findAllByOrderByRateDateDesc() ;
    }
    
    public String findByDate(String s){
        return repository.findByRateDate(s);
    }
}
