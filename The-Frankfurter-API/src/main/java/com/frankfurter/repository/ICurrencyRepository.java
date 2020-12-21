/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frankfurter.repository;

import com.frankfurter.model.CurrencyModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurrencyRepository extends JpaRepository<CurrencyModel, Long>{
    List<CurrencyModel> findAllByOrderByRateDateDesc();
    @Query(value = "SELECT rate FROM Currency_Model WHERE rate_date = ?1", nativeQuery = true)
    String findByRateDate(String rateDate);
}
