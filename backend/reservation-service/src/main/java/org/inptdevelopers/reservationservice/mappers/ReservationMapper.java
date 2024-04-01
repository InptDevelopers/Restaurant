package org.inptdevelopers.reservationservice.mappers;
import lombok.AllArgsConstructor;
import org.inptdevelopers.reservationservice.clients.RestaurantRestClient;
import org.inptdevelopers.reservationservice.dtos.ReservationCreationDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationRequestDTO;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.models.Command;
import org.inptdevelopers.reservationservice.models.Restaurant;
import org.inptdevelopers.reservationservice.models.ATable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class ReservationMapper {
    private final RestaurantRestClient restaurantRestClient;
  public ReservationDTO fromReservation(Reservation reservation,String accestoken) {
        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservation, reservationDTO);
        reservationDTO.setTableId(reservation.getTableId());
        reservationDTO.setItems(restaurantRestClient.getItems(reservation.getItemsId(),accestoken));
        return reservationDTO;



    }

 /*   public Reservation fromReservationDTO(ReservationDTO reservationDTO) {
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
    }*/
    public Reservation fromReservationCreationDTO(ReservationCreationDTO reservationCreationDTO) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationCreationDTO, reservation);
      /*  reservation.setCommandId(reservationDTO.getCommand().getId());
        List<Long> tablesIds = new ArrayList<>();
        reservationDTO.getTables().forEach((table -> {
            tablesIds.add(table.getId());
        }));*/
       /* reservation.setTableIds(tablesIds);
        reservation.setRestaurantId(reservationDTO.getRestaurant().getId());*/

        return reservation;
    }


    public ReservationRequestDTO fromReservationToReservationRequest(Reservation reservation) {
        ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
        BeanUtils.copyProperties(reservation, reservationRequestDTO);
        return reservationRequestDTO; 
    }

    public Reservation fromReservationRequestDTO(ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationRequestDTO, reservation);
        return reservation;
    }
    public Reservation fromReservationcreationDTO(Reservation reservation,ReservationCreationDTO reservationCreationDTO){
        BeanUtils.copyProperties(reservationCreationDTO,reservation);

            reservation.setItemsId(reservationCreationDTO.getItemsId());

        ;
        return reservation;
    }

}
