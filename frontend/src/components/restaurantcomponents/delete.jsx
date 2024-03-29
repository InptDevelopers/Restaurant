import React, { useState, useRef } from "react";
import "../../styles/delete.css";
import instance from "../../../axios";

const ConfirmationPopup = ({ onConfirm, onCancel, id }) => {
  const handleConfirm = () => {
    onConfirm();
  };

  const handleCancel = () => {
    onCancel();
  };

  const deleteres = async (e) => {
    e.preventDefault();

    const response = await instance.delete(
      `/RESTAURANT-SERVICE/api/v1//restaurants/${id}`
    );

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
