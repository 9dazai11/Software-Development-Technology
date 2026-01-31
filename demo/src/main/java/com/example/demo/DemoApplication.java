package com.example.demo;

import com.example.demo.model.Patient;
import com.example.demo.model.Sex;
import com.example.demo.repo.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PatientRepository patientRepo) {
        return args -> {
            System.out.println("Patients in DB: " + patientRepo.count());

            // INSERT (если хочешь наглядно)
            Patient p = new Patient("Иванов Иван", Sex.MALE);
            patientRepo.save(p);

            // SELECT
            patientRepo.findAll().forEach(x ->
                    System.out.println(x.getPatientId() + " | " + x.getFullName() + " | " + x.getSex())
            );

            System.out.println("Done.");
        };
    }
}
