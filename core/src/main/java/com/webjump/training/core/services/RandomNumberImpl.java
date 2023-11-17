package com.webjump.training.core.services;
import org.osgi.service.component.annotations.Component;

import java.util.Random;

@Component(service = RandomNumber.class, immediate = true)
public class RandomNumberImpl implements RandomNumber {

    @Override
    public Integer getRandomNumber(){
        Random random = new Random();
        return random.nextInt(100) + 1;

    }
}
