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

export default function ItemCreation() {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
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
    }
  };

  return (
    <div className="flex justify-center">
      <Button className="bg-black text-white "size="lg" onPress={onOpen}>
        Create your Menu
      </Button>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">
                Modal Title
              </ModalHeader>
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
                              name="prix"
                              value={item.prix}
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
                <Button color="primary" onPress={handleSubmit}>
                  Create Menu
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </div>
  );
}
