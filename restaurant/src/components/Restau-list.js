// RestaurantList.js
import React from "react";
import Restaurant from "./box";
import "../styles/restau-list.css";

const RestaurantList = ({ restaurants }) => {
  return (
    <div className="restaurant-list">
      {restaurants.map((restaurant, index) => (
        <Restaurant key={index} restaurant={restaurant} />
      ))}
    </div>
  );
};

export default RestaurantList;
