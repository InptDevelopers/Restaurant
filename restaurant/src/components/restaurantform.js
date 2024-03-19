// RestaurantForm.js

import React, { useState, useRef, useEffect } from "react";
import "../styles/restaurantform.css";

const RestaurantForm = ({ show }) => {
  const [formData, setFormData] = useState({
    nom: "",
    address: "",
    description: "",
  });
  const showform = () => {
    show();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };
  const handleSubmit = async (e) => {
    console.log(formData);
    e.preventDefault();

    const response = await fetch("http://localhost:8080/api/v1/restaurants", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (response.ok) {
      console.log("Form submitted successfully");
    } else {
      console.error("Form submission failed");
    }
    window.location.reload();
  };

  return (
    <div className="restaurant-form">
      <div className="form-header">
        <h2>Add New Restaurant</h2>
        <button className="close-button" onClick={showform}>
          Close
        </button>
      </div>
      <form onSubmit={handleSubmit}>
        <label htmlFor="nom">Name:</label>
        <input
          type="text"
          id="nom"
          name="nom"
          value={formData.nom}
          onChange={handleChange}
        />

        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          name="address"
          value={formData.address}
          onChange={handleChange}
        />

        <label htmlFor="description">Description:</label>
        <textarea
          id="description"
          name="description"
          value={formData.description}
          onChange={handleChange}
        ></textarea>

        <div className="form-actions">
          <button type="submit">Submit</button>
          <button className="close-button" type="button">
            Close
          </button>
        </div>
      </form>
    </div>
  );
};

export default RestaurantForm;
