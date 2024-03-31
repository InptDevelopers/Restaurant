import React, { useState, useEffect } from "react";
import "../../styles/menu-details.css";
import Navbar from "../../components/restaurantcomponents/navbar";
import axios from "axios";
import { useParams } from "react-router-dom";
import MenuCreationForm from "../../components/restaurantcomponents/Menu";
import Restaurant from "../../components/restaurantcomponents/box";
const RestaurantDetails = () => {
  const { id } = useParams();
  const [menu, setMenu] = useState(null);
  const [existing, SetExisting] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const pageNumbers = [];
  const [totalPages, setTotalPages] = useState(0);
  const [show, setShow] = useState(false);
  const [pageSize, setPageSize] = useState(4);
  const close = () => {
    setShow(false);
  };
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };
  for (let i = 0; i <= totalPages - 1; i++) {
    pageNumbers.push(i);
  }
  useEffect(() => {
    const fetchMenu = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/restaurants/menu/${id}?page=${currentPage}&size=${pageSize}`
        );
        setMenu(response.data);
        console.log(response.data);
        setCurrentPage(response.data.currentPage);
        setPageSize(response.data.pageSize);
        setTotalPages(response.data.totalPages);
        if (response.data == null) {
          SetExisting(false);
        }
      } catch (error) {
        console.error("Error fetching menu:", error);
      }
    };

    //fetchMenu();
    setMenu({items: [
      {
        id: 1,
        nom: "ilyass",
        prix: 1200
      },
      {
        id: 1,
        nom: "ilyass",
        prix: 1200
      },
      {
        id: 1,
        nom: "ilyass",
        prix: 1200
      },
      {
        id: 1,
        nom: "ilyass",
        prix: 1200
      },
      {
        id: 1,
        nom: "ilyass",
        prix: 1200
      },
    ]});
    setCurrentPage(0);
    setPageSize(5);
    setTotalPages(1);
  }, [id, currentPage, pageSize]);

  return (
    <div>
      {existing && menu ? (
        <div className="restaurant-menu">
          <h1>Restaurant Menu</h1>

          <div className="menu-items">
            <ul>
              {menu.items.map((item) => (
                <li key={item.id} className="menu-item">
                  <div>
                    <h3>{item.nom}</h3>
                    <p>Price: ${item.prix}</p>
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
          <div>{show && <MenuCreationForm show={close} />}</div>
          <button className="Creation" onClick={() => setShow(true)}>
            Create your Menu
          </button>
        </div>
      )}
    </div>
  );
};
export default RestaurantDetails;
