// MainComponent.js
import React, { useState, useEffect } from "react";
import RestaurantList from "./Restau-list";
import axios from "axios";
import "../styles/restau-list.css";
const RestauList = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const pageNumbers = [];
  const [id, setId] = useState(null);
  const [totalPages, setTotalPages] = useState(0);
  const [pageSize, setPageSize] = useState(4);
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
  for (let i = 0; i <= totalPages - 1; i++) {
    pageNumbers.push(i);
  }

  return (
    <div className="main-container">
      <h1>Restaurant List</h1>
      <RestaurantList restaurants={restaurants} />
      <nav>
        <ul className="pagination">
          {pageNumbers.map((number) => (
            <li
              key={number}
              className={`page-item ${number === currentPage ? "active" : ""}`}
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
  );
};

export default RestauList;
