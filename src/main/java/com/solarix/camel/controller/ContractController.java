package com.solarix.camel.controller;

import com.solarix.camel.model.Contract;
import com.solarix.camel.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    ContractService service;

    @GetMapping("/owner")
    public String getOwnerAccount() {
        return service.getOwnerAccount();
    }

    @PostMapping
    public Contract createContract(@RequestBody Contract newContract) throws Exception {
        return service.createContract(newContract);
    }

}
