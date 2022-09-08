package com.example.envers;

import java.util.Optional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class SpringBootHibernateEnversApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHibernateEnversApplication.class, args);
    }

    @Bean
    ApplicationRunner init(UserDetailsRepository userRepository) {
        return (ApplicationArguments args) -> dataSetup(userRepository);
    }
    private void dataSetup(UserDetailsRepository userRepository) {

        UserDetails userDetails = new UserDetails(1, "Felix", "Muster");

        userRepository.save(userDetails);      // Create

        userDetails.setFirstName("Updated Name");
        userDetails = userRepository.save(userDetails); // Update-1

        userDetails.setLastName("Updated Last name"); // Update-2
        userDetails = userRepository.save(userDetails);

        userRepository.delete(userDetails); // Delete

        userDetails = new UserDetails(2, "Felix", "Muster");

        userDetails = userRepository.save(userDetails);
        userDetails.setFirstName("Max");// Create
        userDetails = userRepository.save(userDetails);
        userDetails.setActive(false);
        userDetails = userRepository.save(userDetails);


        userDetails = new UserDetails(3, "Max", "Mustermann");
        userDetails = userRepository.save(userDetails);

        userRepository.findRevisions(1).stream().forEach(e -> {
            Optional<Integer> nr = e.getRevisionNumber();
            UserDetails entity = e.getEntity();
            System.out.println(nr.get() + " " + entity.toString());
        });
    }

}
