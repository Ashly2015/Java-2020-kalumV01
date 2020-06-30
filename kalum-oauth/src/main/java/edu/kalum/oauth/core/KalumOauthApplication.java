package edu.kalum.oauth.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.tools.jar.CommandLine;

@SpringBootApplication
public class KalumOauthApplication implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(KalumOauthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("Guatemala"));
        System.out.println(passwordEncoder.encode("sabado"));
    }
}
