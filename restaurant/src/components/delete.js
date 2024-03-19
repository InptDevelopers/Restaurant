import React, { useState, useRef } from "react";
import "../styles/delete.css";
const ConfirmationPopup = ({ onConfirm, onCancel, id }) => {
  const handleConfirm = () => {
    onConfirm();
  };

  const handleCancel = () => {
    onCancel();
  };
  const deleteres = async (e) => {
    e.preventDefault();

    const response = await fetch(
      `http://localhost:8080/api/v1/restaurants/${id}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (response.ok) {
      console.log("Form submitted successfully");
    } else {
      console.error("Form submission failed");
    }
    window.location.reload();
  };

  return (
    <div className="delete-form">
      <p>Are you sure you want to delete this restaurant?</p>
      <button className="confirm" onClick={deleteres}>
        Confirm
      </button>
      <button className="cancel" onClick={handleCancel}>
        Cancel
      </button>
    </div>
  );
};
export default ConfirmationPopup;
