import { Input, Button } from "@nextui-org/react";

import { MailIcon } from "../../components/users/client/MailIcon.jsx";
import { LockIcon } from "../../components/users/client/LockIcon.jsx";
import { PlusIcon } from "../../components/users/client/PlusIcon.jsx";
import { EditIcon } from "../../components/users/client/EditIcon.jsx";
import RestaurantMenu from "../../components/Restaurant-components/RestaurantMenu.jsx";
import { useState, useRef, useEffect } from "react";
import instance from "../../../axios.js";

const RestaurantAdmin = () => {
  const [restaurant, setRestaurant] = useState({
    nom: "",
    address: "",
    description: "",
  });
  const [loading, setLoading] = useState(false);
  const [prevRestaurant, setPrevRestaurant] = useState({
    nom: "",
    address: "",
    description: "",
  });
  const inputRef = useRef(null);
  const [isEditing, setIsEditing] = useState(false);
  const restaurantId = JSON.parse(localStorage.getItem("user")).idRestaurant;
  useEffect(() => {
    const getRestaurant = async () => {
      const res = await instance.get(
        `/RESTAURANT-SERVICE/api/v1/restaurants/${restaurantId}`
      );

      setRestaurant(res.data);
      setPrevRestaurant(res.data);
    };
    getRestaurant();
  }, []);

  const handleEdit = async (e) => {
    inputRef.current.focus();
    setIsEditing(true);
  };

  const handleCancel = async (e) => {
    console.log(prevRestaurant);
    setRestaurant(prevRestaurant);
    setIsEditing(false);
  };

  const handleChange = async (e) => {
    setRestaurant((prev) => {
      return {
        ...prev,
        [e.target.name]: e.target.value,
      };
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await instance.put(
        `/RESTAURANT-SERVICE/api/v1/restaurants/${restaurantId}`,
        restaurant
      );
      setLoading(false);
      setIsEditing(false);
    } catch (error) {
      setLoading(false);
      console.log("error", error.message);
    }
  };

  return (
    <div className="m-5 md:w-[80%] md:mx-auto">
      <div className="w-[100%] flex flex-col gap-2">
        <div className="flex justify-between">
          <h1 className="flex flex-col gap-1 text-center text-2xl font-bold">
            My Restaurant
          </h1>
          <Button
            color="primary"
            className="bg-black text-white "
            isIconOnly
            onClick={handleEdit}
          >
            <EditIcon className="text-xl" />
          </Button>
        </div>
        <form className="m-5 md:w-[70%] mx-auto">
          <div className="flex flex-col gap-4">
            <Input
              required
              ref={inputRef}
              label="Name"
              name="nom"
              placeholder="Enter restaurant name"
              type="text"
              variant="bordered"
              value={restaurant.nom == null ? "" : restaurant.nom}
              onChange={handleChange}
              disabled={!isEditing}
            />
            <Input
              required
              label="Description"
              type="description"
              placeholder="Enter client description"
              variant="bordered"
              name="description"
              value={
                restaurant.description == null ? "" : restaurant.description
              }
              onChange={handleChange}
              disabled={!isEditing}
            />
            <Input
              required
              label="Address"
              placeholder="Enter restaurant Address"
              type="text"
              name="address"
              value={restaurant.address == null ? "" : restaurant.address}
              onChange={handleChange}
              variant="bordered"
              disabled={!isEditing}
            />
          </div>
          <div className="mt-4">
            {isEditing && (
              <>
                <Button
                  className="mr-1"
                  color="primary"
                  type="button"
                  onClick={handleCancel}
                >
                  Cancel
                </Button>
                <Button
                  color="success"
                  type="submit"
                  onClick={handleSubmit}
                  isLoading={loading}
                >
                  {loading ? "Loading" : "Submit"}
                </Button>
              </>
            )}
          </div>
        </form>
      </div>
        <RestaurantMenu />
    </div>
  );
};

export default RestaurantAdmin;
