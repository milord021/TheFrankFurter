/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.model;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author USER
 */
@Data
@Entity
public class CurrencyModel {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotNull
    private String rate;
    private String rateDate;
    private String requestDate;
    
}
