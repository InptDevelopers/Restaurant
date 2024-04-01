import React, { useState } from "react";
/* import { addReservation } from '../api.js'; */
import instance from "../../../axios";

const ReservationForm = ({ setNumGuests, setReservationTime, numGuests, reservationTime }) => {
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
    } catch (error) {
      console.error("Error adding reservation:", error);
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="max-w-[800px] mx-auto mt-8 bg-white px-8 pt-6 pb-8 mb-4 basket rounded-[10px]"
    >
      <div className="grid grid-cols-1 gap-4">
        <label className="block text-gray-700 text-sm font-bold mb-2">
          Number of Guests:
          <input
            type="number"
            name="numGuests"
            min={1}
            value={numGuests}
            onChange={(e) => setNumGuests(e.target.value)}
            className="form-input mt-1 block w-full border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </label>
        <label className="block text-gray-700 text-sm font-bold mb-2">
          Reservation Time:
          <input
            type="datetime-local"
            name="reservationTime"
            value={reservationTime}
            onChange={(e) => setReservationTime(e.target.value)}
            className="form-input mt-1 block w-full border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </label>
      </div>
      {/* <button
        type="submit"
        className="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
      >
        Submit
      </button> */}
    </form>
  );
};

export default ReservationForm;
