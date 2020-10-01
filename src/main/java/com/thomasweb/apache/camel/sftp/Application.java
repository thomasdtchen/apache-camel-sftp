package com.thomasweb.apache.camel.sftp;
 

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import java.util.concurrent.TimeUnit;
 
@SpringBootApplication
@Slf4j
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
        log.info("OK!");
        runAsService();
    }
 
    private static synchronized void runAsService() {
        while (true) {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                log.error("Running error!", e);
            }
        }
    }
}