package com.jpmc.midascore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.foundation.Transaction;


@Component
public class IncentiveWebService{
    
    private RestTemplate restTemplate;

    public IncentiveWebService(){
        this.restTemplate = new RestTemplate();
    }

    public Balance getIncentive(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<Transaction>(transaction,headers);
        Balance resp= restTemplate.postForObject("http://localhost:8080/incentive",entity,  Balance.class);
        return resp;
    }
   
}