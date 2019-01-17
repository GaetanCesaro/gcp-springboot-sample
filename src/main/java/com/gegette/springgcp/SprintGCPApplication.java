package com.gegette.springgcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gegette.springgcp"})
@EnableSwagger2
@Slf4j
public class SprintGCPApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintGCPApplication.class, args);
        log.info("--Application Started--");
	}
/*
    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("Remove all users");
            this.userRepository.deleteAll();

            System.out.println("Save one user");
            this.userRepository.save(new UserDB("john.doe@gmail.com", "DOE", "John", null));

            System.out.println("Save a list of users");
            UserDB maryJane = new UserDB("mary.jane@hotmail.com", "JANE", "Mary", null);
            UserDB scottSmith = new UserDB("scott.smith@gmail.com", "SMITH", "Scott", null);
            this.userRepository.saveAll(Lists.newArrayList(maryJane, scottSmith));

            createRelationshipsInTransaction(maryJane, scottSmith);

            // The following line uses count(), which is a global-query in Datastore. This
            // has only eventual consistency.
            Thread.sleep(3000);

            retrieveAndPrintSingers();

            System.out.println("This concludes the sample.");
        };
    }

    private void retrieveAndPrintSingers() {
        System.out.println("The kind for users has been cleared and " + this.userRepository.count() + " new users have been inserted:");

        Iterable<UserDB> allUsers = this.userRepository.findAll();
        allUsers.forEach(System.out::println);

        System.out.println("You can also retrieve by keys for strong consistency: ");

        // Retrieving by keys or querying with a restriction to a single entity group
        // / family is strongly consistent.
        this.userRepository
                .findAllById(Lists.newArrayList("john.doe@gmail.com", "mary.jane@hotmail.com", "scott.smith@gmail.com"))
                .forEach((x) -> System.out.println("retrieved users: " + x));
    }

    private void createRelationshipsInTransaction(UserDB maryJane, UserDB scottSmith) {
        GroupDB group1 = new GroupDB("group1");
        GroupDB group2 = new GroupDB("group2");
        GroupDB group3 = new GroupDB("group3");

        // Creates the related Band and Instrument entities and links them to a Singer
        // in a single atomic transaction
        this.userRepositoryService.createAndSaveUserRelationshipsInTransaction(maryJane, Arrays.asList(group1, group2));

        // You can also execute code within a transaction directly using the
        // userRepository.
        // The following call also performs the creation and saving of relationships
        // in a single transaction.
        this.userRepository.performTransaction((transactionRepository) -> {
            scottSmith.setGroups(Arrays.asList(group3, group2));
            this.userRepository.save(scottSmith);
            return null;
        });
    }
*/

}

