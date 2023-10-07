package com.ihvncr.ihvncrapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.ihvncr.ihvncrapi.service.PatientService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
@SecurityScheme(name="IHVN_CB", scheme="basic", type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@EnableCaching
public class IhvncrApiApplication extends SpringBootServletInitializer {
    @Autowired
    PatientService patientService;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IhvncrApiApplication.class);
    }

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = IhvncrApiApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("ecews-db.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        SpringApplication.run(IhvncrApiApplication.class, args);
    }
}
