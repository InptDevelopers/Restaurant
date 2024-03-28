import React from "react";
import "../../styles/restau-list.css";
import { Link } from "react-router-dom";
const Restaurant = ({ restaurant }) => {
  return (
    <div className="restaurant-box">
      <img
        src="https://marketplace.canva.com/EAFYecj_1Sc/1/0/1600w/canva-cream-and-black-simple-elegant-catering-food-logo-2LPev1tJbrg.jpg"
        alt={restaurant.name}
        className="restaurant-logo"
      />
      <div className="restaurant-details">
        <p>
          <strong>Name:</strong> {restaurant.nom}
        </p>
        <p>
          <strong>
            {" "}
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-geo-alt"
              viewBox="0 0 16 16"
            >
              <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10" />
              <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6" />
            </svg>
            Address:
          </strong>{" "}
          {restaurant.address}
        </p>
        <p>
          <strong>Description:</strong> {restaurant.description}
        </p>
        <Link to={`/restaurantclient/${restaurant.id}`}>
          <button className="choose">Choose the menu</button>
        </Link>
      </div>
    </div>
  );
};

export default Restaurant;