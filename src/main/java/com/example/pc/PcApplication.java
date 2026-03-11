package com.example.pc;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.pc.entities.Pc;
import com.example.pc.service.PcService;

@SpringBootApplication
public class PcApplication implements CommandLineRunner {

    @Autowired
    PcService pcService;

    public static void main(String[] args) {
        SpringApplication.run(PcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //pcService.savePc(new Pc("PC Asus N7", 2000.0, new Date()));
        //pcService.savePc(new Pc("Huawei Matebook", 2500.0, new Date()));
        //pcService.savePc(new Pc("MacBook Pro", 3500.0, new Date()));
    }
}