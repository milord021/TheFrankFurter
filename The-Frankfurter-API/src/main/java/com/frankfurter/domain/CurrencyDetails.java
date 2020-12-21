/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.domain;

import com.frankfurter.enums.ResultCode;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author USER
 */
@Data
public class CurrencyDetails {
    
    private int amount;
    private String base ;
    private String date;
    private String requestDate;
    private ResultCode code;
    private Map<String, Object> rates;

    public CurrencyDetails(ResultCode code) {
        this.code = code;
    }

    public CurrencyDetails() {
    }

    
}
