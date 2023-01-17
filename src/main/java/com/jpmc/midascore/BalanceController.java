package com.jpmc.midascore;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;

@RestController
public class BalanceController {
    @Autowired
    private DatabaseConduit databaseConduit;

    @GetMapping("/balance")
    public Balance getUserBalance(@RequestParam(value = "userId", defaultValue = "0") String userId) {
    try{
        UserRecord user = databaseConduit.getUserById(Long.parseLong(userId));
        return new Balance(user.getBalance());
    }
    catch(NoSuchElementException e){
        return new Balance(0);
    }
}
}
