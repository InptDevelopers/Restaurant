import React, { useState } from "react";
import axios from "axios";
import "../../styles/menu.css";
import { useParams } from "react-router-dom";
const MenuCreationForm = ({ show }) => {
  const [formData, setFormData] = useState({
    idrestaurant: 1,
    items: [{ nom: "", prix: 0 }],
  });
  const { id } = useParams();
  const handleChange = (index, e) => {
    const { name, value } = e.target;
    const updatedItems = [...formData.items];
    updatedItems[index] = { ...updatedItems[index], [name]: value };
    setFormData({ ...formData, items: updatedItems });
  };
  const showform = () => {
    show();
  };
  const addNewItem = () => {
    setFormData({
      ...formData,
      items: [...formData.items, { nom: "", prix: 0, id: Date.now() }],
    });
  };
  const removeItem = (id) => {
    const updatedItems = formData.items.filter((item) => item.id !== id);
    setFormData({ ...formData, items: updatedItems });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const filteredItems = formData.items.map(({ nom, prix }) => ({
        nom,
        prix,
      }));
      // Include id from path and filtered items in the request data
      const requestData = {
        idrestaurant: id,
        items: filteredItems,
      };
      await axios.post(
        "http://localhost:8080/api/v1/restaurants/menu",
        requestData
      );
      window.location.reload();
      console.log("Menu creation successful!");
      // Optionally, you can redirect the user or show a success message
    } catch (error) {
      console.error("Error creating menu:", error);
      // Optionally, you can show an error message to the user
    }
  };

  const closeForm = () => {
    // Define your logic for closing the form (e.g., hide the form)
    console.log("Closing form...");
  };

  return (
    <div className="restaurant-form">
      <div className="form-header">
        <h2>Create Menu</h2>
        <button className="close-button" onClick={showform}>
          Close
        </button>
      </div>
      <div className="form-container">
        <form onSubmit={handleSubmit}>
          {formData.items.map((item, index) => (
            <div key={item.id} className="item">
              <label>
                Item Name:
                <input
                  type="text"
                  name="nom"
                  value={item.nom}
                  onChange={(e) => handleChange(index, e)}
                  required
                />
              </label>
              <label>
                Price:
                <input
                  type="number"
                  name="prix"
                  value={item.prix}
                  onChange={(e) => handleChange(index, e)}
                  required
                />
              </label>
              <button
                type="button"
                className="remove-button"
                onClick={() => removeItem(item.id)}
              >
                Remove
              </button>
            </div>
          ))}
          <button type="button" onClick={addNewItem}>
            Add Item
          </button>
          <div className="form-actions">
            <button type="submit">Create Menu</button>
            <button className="close-button" onClick={closeForm}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MenuCreationForm;
