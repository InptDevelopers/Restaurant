import React, { useState, useEffect } from "react";
import "../../styles/menu-details.css";
import Navbar from "../../components/restaurantcomponents/navbar";
import axios from "axios";
import { useParams } from "react-router-dom";
import MenuCreationForm from "../../components/restaurantcomponents/Menu";
import Restaurant from "../../components/restaurantcomponents/box";
import Basket from "../../components/restaurantcomponents/basket";
import instance from "../../../axios";
import { Button } from "@nextui-org/react";
import ZoneComponent from "../../components/table-components/ZoneComponent";
import ReservationForm from "../../components/reservation/ReservationForm";

const RestaurantDetailsclient = () => {
  /* const [items, setItems] = useState([]); */
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
  const [selectedZone, setSelectedZone] = useState(null);
  const [zones, setZones] = useState([]);
  const [zonePage, setZonePage] = useState(1);
  const [numGuests, setNumGuests] = useState(1);
  const [reservationTime, setReservationTime] = useState();
  const [totalPrice, setTotalPrice] = useState(0);
  const removeFromBasket = (indexToRemove) => {
    setBasket((prevBasket) =>
      prevBasket.filter((_, index) => index !== indexToRemove)
    );
  };

  useEffect(() => {
    instance
      .get(
        `/TABLE-SERVICE/api/v1/zones/?page=${
          zonePage - 1
        }&size=${pageSize}&restaurantId=${id}`
      )
      .then((response) => {
        setZones(response.data.content);
      })
      .catch((error) => console.error("Error fetching zones:", error));
  }, [zonePage]);
  const close = () => {
    setShow(false);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    const fetchMenu = async () => {
      try {
        const response = await instance.get(
          `/RESTAURANT-SERVICE/api/v1/restaurants/${id}/menu?page=${currentPage}&size=${pageSize}`
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
    setItemQuantities({});
  };
  const handleZonePagination = (newPage) => {
    setZonePage(newPage);
  };
  useEffect(() => {
    setTotalPrice(() => {
      let price = 0;
      for (let index = 0; index < basket.length; index++) {
        price += basket[index].price * basket[index].quantity;
      }
      setTotalPrice(price);
    });
  }, [basket]);

  console.log("price");
  console.log(totalPrice);
  const makeReservation = async () => {
    const clientId = JSON.parse(localStorage.getItem("user")).id;
    const itemsId = menu.items.map((item) => {
      return item.id;
    });
    const body = {
      numGuests: numGuests,
      reservationTime: reservationTime,
      reservationPrice: totalPrice,
      itemsId: itemsId,
      clientId: clientId,
    };
    console.log("body");
    const res = await instance.post(
      `/RESERVATION-SERVICE/api/v1/reservations/restaurants/${id}/zones/${selectedZone}`,
      body
    );
    console.log("reservation", res.data);
  };
  return (
    <div>
      <div className="restaurant-menu">
        <h1 className="text-xl font-bold">Restaurant Menu</h1>

        <div className="menu-items">
          <ul>
            {menu &&
              menu.items.map((item) => (
                <li key={item.id} className="menu-item">
                  <div className="flex justify-between items-center">
                    <div>
                      <h3>{item.nom}</h3>
                      <p>Price: {item.price}$</p>
                    </div>
                    <input
                      type="number"
                      min="0"
                      value={itemQuantities[item.id] || 0}
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
          <Button
            className="bg-black text-white text-medium"
            onClick={addToBasket}
          >
            Add to Basket
          </Button>
        </div>
      </div>

      <Basket items={basket} removeFromBasket={removeFromBasket} />
      <div className="basket">
        <div>
          <h1 className="text-xl font-bold">Choose a Zone</h1>

          {zones.map((zone) => (
            <div
              onClick={() => onClick(zone)}
              className="flex max-w-[800px] mx-auto "
            >
              <div className="flex-1 px-4 py-2">{zone.description}</div>
              <div className="flex-1 px-4 py-2">{zone.maxSize}</div>
              <div className="flex-1 px-4 py-2 flex justify-center items-center">
                <div></div>
                {selectedZone == zone.id ? (
                  <Button
                    onClick={() => {
                      setSelectedZone(zone.id);
                    }}
                    color="success"
                  >
                    Chosen
                  </Button>
                ) : (
                  <Button
                    onClick={() => {
                      setSelectedZone(zone.id);
                    }}
                    color="primary"
                  >
                    Choose
                  </Button>
                )}
              </div>
            </div>
          ))}
        </div>
        <div className="flex justify-between mt-4">
          <button
            onClick={() => handleZonePagination(zonePage - 1)}
            disabled={zonePage === 1}
            className={`px-4 py-2 bg-blue-500 text-white rounded ${
              zonePage === 1 ? "opacity-50 cursor-not-allowed" : ""
            }`}
          >
            Previous
          </button>

          <button
            onClick={() => handleZonePagination(zonePage + 1)}
            disabled={zones.length < pageSize}
            className={`px-4 py-2 bg-blue-500 text-white rounded ${
              zones.length < pageSize ? "opacity-50 cursor-not-allowed" : ""
            }`}
          >
            Next
          </button>
        </div>
      </div>

      <ReservationForm
        setNumGuests={setNumGuests}
        setReservationTime={setReservationTime}
        numGuests={numGuests}
        reservationTime={reservationTime}
      />
      <button className="paiement" onClick={makeReservation}>
        Make reservation
      </button>
    </div>
  );
};

export default RestaurantDetailsclient;
