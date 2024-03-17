import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/restaurant.css";
import RestaurantForm from "./restaurantform";
const PaginationComponent = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);

  const [totalPages, setTotalPages] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [showForm, setShowForm] = useState(true);
  const toggleForm = () => {
    console.log("Toggling form visibility");
    setShowForm(!showForm);
  };
  const ResponseDTO = {
    currentPage: 0,
    totalPages: 0,
    pageSize: 0,
    restaurantsdto: null,
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
        <button onClick={toggleForm}>Add new restaurant</button>
      </div>

      {/* Conditionally render the RestaurantForm */}
      {/* Conditionally render the RestaurantForm */}
      <div
        className={showForm ? "restaurant-form show-form" : "restaurant-form"}
      >
        <RestaurantForm showForm={showForm} setShowForm={setShowForm} />
      </div>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            {/* Include other headers based on your data structure */}
          </tr>
        </thead>
        <tbody>
          {restaurants.map((restaurant) => (
            <tr key={restaurant.id}>
              <td>{restaurant.nom}</td>
              <td>{restaurant.address}</td>
              {/* Include other data cells based on your data structure */}
            </tr>
          ))}
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
        </tbody>
      </table>
    </div>
  );
};

export default PaginationComponent;
