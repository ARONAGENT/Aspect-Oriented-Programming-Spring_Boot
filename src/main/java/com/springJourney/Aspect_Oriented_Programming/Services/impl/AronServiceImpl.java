package com.springJourney.Aspect_Oriented_Programming.Services.impl;

import com.springJourney.Aspect_Oriented_Programming.Services.AronServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AronServiceImpl implements AronServices {
    @Override
    public String orderPackage(Long Id) {
        try{
            Thread.sleep(1000);
            log.info("Order is Processing ...");
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "Order Packaged Successfully with Id : "+Id;
    }
    @Override
    public String trackPackage(Long Id) {
        try{
           Thread.sleep(500);
           log.info("Tracking is processing");
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "Track Package Successfully with id : "+Id;
    }
}
