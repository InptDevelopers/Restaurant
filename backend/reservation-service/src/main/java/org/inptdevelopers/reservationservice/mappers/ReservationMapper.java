package org.inptdevelopers.reservationservice.mappers;
import org.inptdevelopers.reservationservice.dto.ReservationDTO;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.models.Command;
import org.inptdevelopers.reservationservice.models.Restaurant;
import org.inptdevelopers.reservationservice.models.Table;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationMapper {
    public ReservationDTO fromReservation(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservation, reservationDTO);
        if(reservationDTO.getRestaurant() == null) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(reservation.getRestaurantId());
            reservationDTO.setRestaurant(restaurant);
        }
        if(reservationDTO.getTables() == null) {
            List<Table> tables = new ArrayList<>();
            reservationDTO.setTables(tables);
            reservation.getTableIds().forEach(tableId -> {
                Table table = new Table();
                table.setId(tableId);
                reservationDTO.getTables().add(table);
            });
        }
        if(reservationDTO.getCommand() == null) {
            Command command = new Command();
            command.setId(reservation.getCommandId());
            reservationDTO.setCommand(command);
        }
        return reservationDTO;
    }

    public Reservation fromReservationDTO(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDTO, reservation);
        reservation.setCommandId(reservationDTO.getCommand().getId());
        List<Long> tablesIds = new ArrayList<>();
        reservationDTO.getTables().forEach((table -> {
            tablesIds.add(table.getId());
        }));
        reservation.setTableIds(tablesIds);
        reservation.setRestaurantId(reservationDTO.getRestaurant().getId());
        return reservation;
    }

}
