package org.developpers.tableservice.repositories;


import org.developpers.tableservice.entities.ATable;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.enums.TableStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TableRepository extends JpaRepository<ATable,Long> {
    Page<ATable> findByZoneId(Long zoneId , Pageable pageable) ;

    List<ATable> findByStatusAndZoneId(TableStatus status, Long zoneId);

}
