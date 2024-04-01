import React, { useState, useEffect } from "react";
import instance from "../../../axios";
export default function Reservation() {
  const [reservations, setReservations] = useState([
    {
      id: 0,
      numGuests: 1,
      reservationTime: "",
      reservationPrice: "$120",
      items: [
        {
          id: 1,
          nom: "Item 1",
          price: "$10",
        },
      ],
      tableId: 1,
    },
    /* {
      id: 2,
      numGuests: 2,
      reservationTime: '',
      reservationPrice: '$150',
      items: [
        {
          id: 2,
          name: "Item 2", 
          price: "$15"
        }
      ],
      tableId: 2,
    },
    {
      id: 3,
      numGuests: 3,
      reservationTime: '',
      reservationPrice: '$180',
      items: [
        {
          id: 3,
          name: "Item 3", 
          price: "$20"
        }
      ],
      tableId: 3,
    }, */
  ]);

  useEffect(() => {
    const id = JSON.parse(localStorage.getItem("user")).id;
    const fetchData = async () => {
      const res = await instance.get(
        `/RESERVATION-SERVICE/api/v1/reservations/client/${id}`
      );
      console.log(res.data);
      setReservations(res.data);
    };
    fetchData();
  }, []);

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-bold mb-4">Reservations</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {reservations.map((reservation) => (
          <div
            key={reservation.id}
            className="bg-white shadow-md rounded-lg p-6"
          >
            <p className="text-lg font-bold mb-2">Reservation Details</p>
            <p>
              <span className="font-semibold">Number of Guests:</span>{" "}
              {reservation.numGuests}
            </p>
            <p>
              <span className="font-semibold">Reservation Time:</span>{" "}
              {reservation.reservationTime || "Not specified"}
            </p>
            <p>
              <span className="font-semibold">Reservation Price:</span>{" "}
              {reservation.reservationPrice}
            </p>
            <p className="font-semibold">Items:</p>
            <ul>
              {reservation.items.map((item) => (
                <li key={item.id}>
                  {item.nom} - {item.price}
                </li>
              ))}
            </ul>
            <p>
              <span className="font-semibold">Table ID:</span>{" "}
              {reservation.tableId}
            </p>
            {/* Add more fields as needed */}
          </div>
        ))}
      </div>
    </div>
  );
}
