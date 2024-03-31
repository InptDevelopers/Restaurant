import { Input, Button } from "@nextui-org/react";

import { MailIcon } from "../../components/users/client/MailIcon.jsx";
import { LockIcon } from "../../components/users/client/LockIcon.jsx";
import { PlusIcon } from "../../components/users/client/PlusIcon.jsx";
import { EditIcon } from "../../components/users/client/EditIcon.jsx";
import { useState, useRef, useEffect } from "react";
import instance from "../../../axios.js";
import "../../styles/menu-details.css";
import axios from "axios";
import { useParams } from "react-router-dom";
import MenuCreationForm from "../../components/restaurantcomponents/Menu";
import Restaurant from "../../components/restaurantcomponents/box";
import ItemCreation from "./ItemCreation.jsx";

const RestaurantMenu = () => {
  const [menu, setMenu] = useState(null);
  const [existing, SetExisting] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const pageNumbers = [];
  const [totalPages, setTotalPages] = useState(0);
  const [show, setShow] = useState(false);
  const [pageSize, setPageSize] = useState(4);
  /* const close = () => {
    setShow(false);
  }; */
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };
  for (let i = 0; i <= totalPages - 1; i++) {
    pageNumbers.push(i);
  }
  const id = JSON.parse(localStorage.getItem("user")).idRestaurant;
  useEffect(() => {
    const fetchMenu = async () => {
      try {
        /* const response = await axios.get(
          `http://localhost:8080/api/v1/restaurants/menu/${id}?page=${currentPage}&size=${pageSize}`
        ); */
        const response = await instance.get(
          `/RESTAURANT-SERVICE/api/v1/restaurants/${id}/menu?page=${currentPage}&size=${pageSize}`
        );

        console.log(response.data);
        if (response.data) {
          setMenu(response.data);
          setCurrentPage(response.data.currentPage);
          setPageSize(response.data.pageSize);
          setTotalPages(response.data.totalPages);
        }

        if (response.data == null) {
          SetExisting(false);
        }
      } catch (error) {
        console.error("Error fetching menu:", error);
      }
    };

    fetchMenu();
  }, [id, currentPage, pageSize]);

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
    <div className=" md:mx-auto w-[100%] flex flex-col gap-2">
      <div className="flex justify-between">
        <h1 className="flex flex-col gap-1 text-center text-2xl font-bold">
          My Menu
        </h1>
        <Button
          color="primary"
          variant="light"
          isIconOnly
          onPress={() => {
            setShow(true);
          }}
        >
          <ItemCreation
            content={<EditIcon className="text-xl" />}
            title={"Add Items"}
          />
        </Button>
      </div>
      {existing && menu ? (
        /*  {false ? ( */
        <div className="restaurant-menu md:w-[70%]">
          <div className="menu-items">
            <ul>
              {menu.items.map((item) => (
                <li key={item.id} className="menu-item ">
                  <div>
                    <h3>{item.nom}</h3>
                    <p>Price: {item.price} $</p>
                  </div>
                </li>
              ))}
            </ul>
            <nav>
              <ul className="pagination">
                {pageNumbers.map((number) => (
                  <li
                    key={number}
                    className={`page-item ${
                      number === currentPage ? "active" : ""
                    }`}
                  >
                    <button
                      onClick={() => handlePageChange(number)}
                      className="page-link"
                    >
                      {number}
                    </button>
                  </li>
                ))}
              </ul>
            </nav>
          </div>
        </div>
      ) : (
        <div>
          {/* <div>{show && <MenuCreationForm show={close} />}</div>
          <button className="Creation" onClick={() => setShow(true)}>
            Create your Menu
          </button> */}
          <ItemCreation content={"Create Menu"} title={"Create Menu"} />
        </div>
      )}
    </div>
  );
};

export default RestaurantMenu;
