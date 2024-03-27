import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../styles/restaurant.css";
import RestaurantListAdmin from "../../components/restaurantcomponents/Restau-listadmin.js";
import Navbar from "../../components/restaurantcomponents/navbar.js";
import "../../styles/restau-list.css";
import ConfirmationPopup from "../../components/restaurantcomponents/delete.js";
import RestaurantForm from "../../components/restaurantcomponents/restaurantform.js";
import RestaurantAdmin from "../../components/restaurantcomponents/box2.js";
const PaginationComponent = () => {
  const [totalPages, setTotalPages] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [showForm, setShowForm] = useState(true);
  const [show, setShow] = useState(false);
  const [restaurants, setRestaurants] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const pageNumbers = [];
  const [id, setId] = useState(null);
  const toggleForm = () => {
    console.log("Toggling form visibility");
    setShowForm(!showForm);
    setShow(!show);
  };
  const close = () => {
    setShow(false);
  };
  const [showConfirmation, setShowConfirmation] = useState(false);
  const ResponseDTO = {
    currentPage: 0,
    totalPages: 0,
    pageSize: 0,
    restaurantsdto: null,
  };
  const handleDelete = (id) => {
    setShowConfirmation(!showConfirmation);
    setId(id);
    console.log(id);
    console.log(showConfirmation);
  };
  const handleConfirmDelete = () => {
    setShowConfirmation(false); // Close the confirmation popup
  };

  const handleCancelDelete = () => {
    setShowConfirmation(false); // Close the confirmation popup
  };
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/restaurants?page=${currentPage}&size=${pageSize}`
        );
        setRestaurants(response.data.restaurantsdto);

        setTotalPages(response.data.totalPages);
        setPageSize(response.data.pageSize);

        console.log(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [currentPage, pageSize]);
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  for (let i = 0; i <= totalPages - 1; i++) {
    pageNumbers.push(i);
  }

  return (
    <div>
      <Navbar></Navbar>
      <div className="head">My restaurants</div>
      <div className="add">
        <button onClick={toggleForm}>
          {" "}
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-plus-circle-fill"
            viewBox="0 0 16 16"
          >
            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z" />
          </svg>
          Add new restaurant
        </button>
      </div>

      <div>{show && <RestaurantForm show={close} />}</div>
      <div className="main-container">
        <RestaurantListAdmin restaurants={restaurants} />
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
  );
};

export default PaginationComponent;
