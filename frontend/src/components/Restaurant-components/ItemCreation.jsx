import React, { useState } from "react";
import "../../styles/menu.css";
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import { useParams } from "react-router-dom";
import instance from "../../../axios.js";
import { EditIcon } from "../../components/users/client/EditIcon.jsx";

export default function ItemCreation({ content, title }) {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [formData, setFormData] = useState({
    idrestaurant: 1,
    items: [{ nom: "", price: 0 }],
  });
  const id = JSON.parse(localStorage.getItem("user")).idRestaurant;
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
      items: [...formData.items, { nom: "", price: 0, id: Date.now() }],
    });
  };
  const removeItem = (id) => {
    const updatedItems = formData.items.filter((item) => item.id !== id);
    setFormData({ ...formData, items: updatedItems });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const filteredItems = formData.items.map(({ nom, price }) => ({
        nom,
        price,
      }));
      // Include id from path and filtered items in the request data
      const requestData = {
        idrestaurant: id,
        items: filteredItems,
      };
      await instance.post(
        "/RESTAURANT-SERVICE/api/v1/restaurants/menu",
        requestData
      );
      window.location.reload();
      console.log("Menu creation successful!");
      // Optionally, you can redirect the user or show a success message
    } catch (error) {
      console.error("Error creating menu:", error);
    }
  };

  return (
    <div className="flex justify-center">
      <Button className="bg-black text-white " size="lg" onPress={onOpen}>
        {content}
      </Button>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">{title}</ModalHeader>
              <ModalBody>
                <div className="restaurant-form">
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
                              name="price"
                              value={item.price}
                              onChange={(e) => handleChange(index, e)}
                              required
                            />
                          </label>
                          <button
                            type="button"
                            className="remove-button mt-2"
                            onClick={() => removeItem(item.id)}
                          >
                            Remove
                          </button>
                        </div>
                      ))}
                      <button type="button" onClick={addNewItem}>
                        Add Item
                      </button>
                    </form>
                  </div>
                </div>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button color="primary" onClick={handleSubmit}>
                  {title}
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </div>
  );
}
