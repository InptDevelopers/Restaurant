import React, { useState } from 'react';
import { addReservation } from '../api.js';

const ReservationForm = () => {
  const [reservationData, setReservationData] = useState({
    restaurant: '',
    numGuests: '',
    commandId: '',
    reservationTime: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const reservation = {
        ...reservationData,
        restaurantId: 1,
        tableIds: [1],

      }
      const newReservation = await addReservation(reservationData);
      console.log("New reservation:", newReservation);
      // Reset form fields
      setReservationData({
        restaurant: '',
        numGuests: '',
        commandId: '',
        reservationTime: ''
      });
    } catch (error) {
      console.error("Error adding reservation:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-8 bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
      <div className="grid grid-cols-1 gap-4">
        <label className="block text-gray-700 text-sm font-bold mb-2">
          Restaurant Name:
          <input
            type="text"
            name="restaurant"
            value={reservationData.restaurant}
            onChange={(e) => setReservationData({ ...reservationData, restaurant: e.target.value })}
            className="form-input mt-1 block w-full border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </label>
        <label className="block text-gray-700 text-sm font-bold mb-2">
          Number of Guests:
          <input
            type="number"
            name="numGuests"
            value={reservationData.numGuests}
            onChange={(e) => setReservationData({ ...reservationData, numGuests: e.target.value })}
            className="form-input mt-1 block w-full border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </label>
        <label className="block text-gray-700 text-sm font-bold mb-2">
          Command ID:
          <input
            type="text"
            name="commandId"
            value={reservationData.commandId}
            onChange={(e) => setReservationData({ ...reservationData, commandId: e.target.value })}
            className="form-input mt-1 block w-full border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </label>
        <label className="block text-gray-700 text-sm font-bold mb-2">
          Reservation Time:
          <input
            type="datetime-local"
            name="reservationTime"
            value={reservationData.reservationTime}
            onChange={(e) => setReservationData({ ...reservationData, reservationTime: e.target.value })}
            className="form-input mt-1 block w-full border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </label>
      </div>
      <button type="submit" className="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
        Submit
      </button>
    </form>
  );
};

export default ReservationForm;
