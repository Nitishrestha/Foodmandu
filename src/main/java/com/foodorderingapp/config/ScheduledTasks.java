package com.foodorderingapp.config;

import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void reportCurrentTime() {
        userService.updateBalance();
        System.out.println("Balance Updated Successfully");
    }
}
