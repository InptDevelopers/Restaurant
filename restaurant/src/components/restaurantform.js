// RestaurantForm.js

import React, { useState, useRef } from "react";
import "../styles/restaurantform.css";

const RestaurantForm = () => {
  const [showForm, setShowForm] = useState(false);
  const formRef = useRef(null);

  const handleClickOutside = (e) => {
    if (formRef.current && !formRef.current.contains(e.target)) {
      setShowForm(false);
    }
  };

  const toggleForm = () => {
    setShowForm(!showForm);
  };
  const [formData, setFormData] = useState({
    nom: "",
    address: "",
    description: "",
  });
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

    // Assuming your backend API endpoint is '/api/restaurants'
    const response = await fetch("http://localhost:8080/api/v1/restaurants", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (response.ok) {
      // Handle success, e.g., show a success message
      console.log("Form submitted successfully");
      // Optionally, you can close the form after successful submission
      setShowForm(false);
    } else {
      // Handle error, e.g., show an error message
      console.error("Form submission failed");
    }
  };

  return (
    <div className="restaurant-form" ref={formRef}>
      <div className="form-header">
        <h2>Add New Restaurant</h2>
        <button onClick={toggleForm} className="close-button">
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
          <button className="close-button" type="button" onClick={toggleForm}>
            Close
          </button>
        </div>
      </form>
    </div>
  );
};

export default RestaurantForm;
