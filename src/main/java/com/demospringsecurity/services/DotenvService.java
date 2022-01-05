package com.demospringsecurity.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class DotenvService {
    public String getValueDotenv(String keyName){
        Dotenv dotenv = Dotenv.configure()
                .directory("E:\\Java\\Java5\\Project\\SpringSecurity")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        return dotenv.get(keyName);
    }
}
