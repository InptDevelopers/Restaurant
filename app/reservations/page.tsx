"use client"
import React, { useEffect } from 'react';
import {Accounts} from '@/components/accounts';
import TableWrapper from '@/components/tableReservations/Table';
import axios from 'axios';

const accounts = () => {
   const [reservations, setReservations] = React.useState("");
   useEffect(()=>{
         const getReservations=async ()=>{
               const res=await axios.get("http://localhost:8080/api/v1/reservations?restaurantId=1")
               const receivedReservations = res.data.reservations;
               const list = [];
               res.data.reservations.forEach((reservation) => {
                  list.push({
                     restaurantId: reservation.restaurant.id,
                     ...reservation
                  })
               })
               console.log(list)
               //receivedReservations.restaurantId= res.data.reservations.restaurant.id;
               //console.log(receivedReservations);
               setReservations(list)

         }
         getReservations()
   },[])

   return <div className='flex justify-center items-center pt-3'>
      <div className='w-[90%]'>
         <TableWrapper reservations={reservations}/>
      </div>
      
   </div>;
};

export default accounts;
