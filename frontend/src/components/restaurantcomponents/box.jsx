import React from "react";
import "../../styles/restau-list.css";
import { Link } from "react-router-dom";
const Restaurant = ({ restaurant }) => {
  return (
    <div className="restaurant-box">
      <img
        src="https://marketplace.canva.com/EAFYecj_1Sc/1/0/1600w/canva-cream-and-black-simple-elegant-catering-food-logo-2LPev1tJbrg.jpg"
        alt={restaurant.name}
        className="restaurant-logo w-[50%]"
      />
      <div className="restaurant-details flex flex-col justify-center gap-5">
        <p className="flex">
          <strong>Name:</strong> {restaurant.nom}
        </p>
        <p className="flex">
          <strong> Address:</strong> {restaurant.address}
        </p>
        <p className="flex">
          <strong>Description:</strong> {restaurant.description}
        </p>
        <Link to={`/restaurants/${restaurant.id}`}>
          <button className="choose text-white">Choose the menu</button>
        </Link>
      </div>
    </div>
  );
};

export default Restaurant;
