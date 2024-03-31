package org.developpers.tableservice;

import org.developpers.tableservice.entities.ATable;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.enums.TableStatus;
import org.developpers.tableservice.repositories.TableRepository;
import org.developpers.tableservice.repositories.ZoneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TableServiceApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TableServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ZoneRepository zoneRepository, TableRepository tableRepository) {
        return args -> {
            // Create zones
            Zone zone1 = new Zone();
            zone1.setDescription("Zone 1");
            zone1.setMaxSize(10);
            zone1.setRestaurantId(1L);
            zoneRepository.save(zone1);

            Zone zone2 = new Zone();
            zone2.setDescription("Zone 2");
            zone2.setMaxSize(8);
            zone2.setRestaurantId(1L);
            zoneRepository.save(zone2);

            // Create tables and associate them with zones
            ATable table1 = new ATable();
            table1.setStatus(TableStatus.NOT_OCCUPIED);

            ATable table3 = new ATable();
            table1.setStatus(TableStatus.NOT_OCCUPIED);
            table1.setZone(zone1);
            tableRepository.save(table1);

            ATable table2 = new ATable();
            table2.setStatus(TableStatus.NOT_OCCUPIED);

            table2.setZone(zone1);
            tableRepository.save(table2);
        };
    }
}
