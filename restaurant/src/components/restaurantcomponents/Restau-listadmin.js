// RestaurantList.js
import React from "react";

import "../../styles/restau-list.css";
import RestaurantAdmin from "./box2";

const RestaurantListAdmin = ({ restaurants }) => {
  return (
    <div className="restaurant-list">
      {restaurants.map((restaurant, index) => (
        <RestaurantAdmin key={index} restaurant={restaurant} />
      ))}
    </div>
  );
};

export default RestaurantListAdmin;
