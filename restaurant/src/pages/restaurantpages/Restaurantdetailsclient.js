import React, { useState, useEffect } from "react";
import "../../styles/menu-details.css";
import Navbar from "../../components/restaurantcomponents/navbar";
import axios from "axios";
import { useParams } from "react-router-dom";
import MenuCreationForm from "../../components/restaurantcomponents/Menu";
import Restaurant from "../../components/restaurantcomponents/box";
import Basket from "../../components/restaurantcomponents/basket";
const RestaurantDetailsclient = () => {
  const [items, setItems] = useState([]);
  const { id } = useParams();
  const [menu, setMenu] = useState(null);
  const [existing, SetExisting] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const pageNumbers = [];
  const [totalPages, setTotalPages] = useState(0);
  const [show, setShow] = useState(false);
  const [pageSize, setPageSize] = useState(4);
  const [itemQuantities, setItemQuantities] = useState({});
  const [basket, setBasket] = useState([]);
  const removeFromBasket = (indexToRemove) => {
    setBasket((prevBasket) =>
      prevBasket.filter((_, index) => index !== indexToRemove)
    );
  };

  const close = () => {
    setShow(false);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    const fetchMenu = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/restaurants/menu/${id}?page=${currentPage}&size=${pageSize}`
        );
        setMenu(response.data);
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

    fetchMenu();
  }, [id, currentPage, pageSize]);

  const handleQuantityChange = (itemId, quantity) => {
    setItemQuantities({ ...itemQuantities, [itemId]: quantity });
  };

  const addToBasket = () => {
    const itemsToAdd = [];
    Object.keys(itemQuantities).forEach((itemId) => {
      const item = menu.items.find((item) => item.id === parseInt(itemId));
      if (item) {
        itemsToAdd.push({ ...item, quantity: itemQuantities[itemId] });
      }
    });
    setBasket([...basket, ...itemsToAdd]);
    setItemQuantities({}); // Reset item quantities after adding to basket
  };

  return (
    <div>
      <Navbar></Navbar>

      <div className="restaurant-menu">
        <h1>Restaurant Menu</h1>

        <div className="menu-items">
          <ul>
            {menu &&
              menu.items.map((item) => (
                <li key={item.id} className="menu-item">
                  <div>
                    <h3>{item.nom}</h3>
                    <p>Price: ${item.prix}</p>
                    <input
                      type="number"
                      min="0"
                      value={itemQuantities[item.id] || ""}
                      onChange={(e) =>
                        handleQuantityChange(item.id, parseInt(e.target.value))
                      }
                    />
                  </div>
                </li>
              ))}
          </ul>
          <nav>
            <ul className="pagination">
              {[...Array(totalPages).keys()].map((number) => (
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
                    {number + 1}
                  </button>
                </li>
              ))}
            </ul>
          </nav>
          <button onClick={addToBasket}>Add to Basket</button>
        </div>
      </div>

      <Basket items={basket} removeFromBasket={removeFromBasket} />
      <button className="paiement">Passer au paiment</button>
    </div>
  );
};

export default RestaurantDetailsclient;
