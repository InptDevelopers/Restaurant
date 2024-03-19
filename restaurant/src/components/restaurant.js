import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/restaurant.css";
import ConfirmationPopup from "./delete.js";
import RestaurantForm from "./restaurantform";
const PaginationComponent = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);

  const [totalPages, setTotalPages] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [showForm, setShowForm] = useState(true);
  const [show, setShow] = useState(false);
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

        console.log(response.data.restaurantsdto);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [currentPage, pageSize]);
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div>
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
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Adress</th>
            <th>Description</th>
            <th></th>
            {/* Include other headers based on your data structure */}
          </tr>
        </thead>
        <tbody className="table-row">
          {restaurants.map((restaurant) => (
            <tr key={restaurant.id}>
              <td>{restaurant.nom}</td>
              <td>{restaurant.address}</td>
              <td>{restaurant.description}</td>
              <td onClick={() => handleDelete(restaurant.id)} className="trash">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-trash"
                  viewBox="0 0 16 16"
                >
                  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z" />
                  <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z" />
                </svg>
              </td>
              {/* Include other data cells based on your data structure */}
            </tr>
          ))}
          {showConfirmation && (
            <ConfirmationPopup
              onConfirm={handleConfirmDelete}
              onCancel={handleCancelDelete}
              id={id}
            />
          )}
        </tbody>
      </table>
      <div className="pagination">
        <button
          className="pagination-button"
          onClick={() => handlePageChange(currentPage - 1)}
          disabled={currentPage + 1 === 1}
        >
          Previous
        </button>
        <span>{currentPage + 1}</span> / <span>{totalPages}</span>
        <button
          className="pagination-button"
          onClick={() => handlePageChange(currentPage + 1)}
          disabled={currentPage + 1 === totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default PaginationComponent;
