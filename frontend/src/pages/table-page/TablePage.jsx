// components/TableList.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import TableComponent from "../../components/table-components/TableComponent";
import ZoneComponent from "../../components/table-components/ZoneComponent";
import "../../styles/table-styles/table.css";
import ConfirmationPopup from "../../components/table-components/ConfirmationPopup";
import instance from "../../../axios";
const TablePage = () => {
  const [zones, setZones] = useState([]);
  const [selectedZone, setSelectedZone] = useState(null);
  const [tables, setTables] = useState([]);
  const [occupiedFilter, setOccupiedFilter] = useState("all");
  const [zonePage, setZonePage] = useState(1);
  const [tablePage, setTablePage] = useState(1);
  const [pageSize] = useState(7);
  const [loading, setLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [showAddZoneForm, setShowAddZoneForm] = useState(false);
  const [newZoneDescription, setNewZoneDescription] = useState("");
  const restaurantId = JSON.parse(localStorage.getItem("user")).idRestaurant;

  const [newZone, setNewZone] = useState({
    description: "",
    maxSize: 1,
    restaurantId: restaurantId,
  });
  const [showConfirmationPopup, setShowConfirmationPopup] = useState(false);
  const [deleteZoneId, setDeleteZoneId] = useState(null);
  useEffect(() => {
    instance
      .get(
        `/TABLE-SERVICE/api/v1/zones/?page=${
          zonePage - 1
        }&size=${pageSize}&restaurantId=${restaurantId}`
      )
      .then((response) => {
        setZones(response.data.content);
      })
      .catch((error) => console.error("Error fetching zones:", error));
  }, [zonePage]);

  const handleZoneClick = (zone) => {
    setSelectedZone(zone);
    setTables(
      zone.tables.slice((tablePage - 1) * pageSize, tablePage * pageSize)
    );
  };

  useEffect(() => {
    if (selectedZone) {
      setTables(
        selectedZone.tables
          .filter(
            (table) =>
              occupiedFilter === "all" ||
              (occupiedFilter === "occupied" && table.status === "OCCUPIED") ||
              (occupiedFilter === "notOccupied" &&
                table.status === "NOT_OCCUPIED")
          )
          .slice((tablePage - 1) * pageSize, tablePage * pageSize)
      );
    }
  }, [tablePage]);
  const handleOccupiedFilterChange = (e) => {
    setOccupiedFilter(e.target.value);
    setTables(
      selectedZone.tables
        .filter(
          (table) =>
            e.target.value === "all" ||
            (e.target.value === "occupied" && table.status === "OCCUPIED") ||
            (e.target.value === "notOccupied" &&
              table.status === "NOT_OCCUPIED")
        )
        .slice((tablePage - 1) * pageSize, tablePage * pageSize)
    );
  };

  const handleZonePagination = (newPage) => {
    setZonePage(newPage);
  };
  const handleTablePagination = (newPage) => {
    setTablePage(newPage);
  };

  const handleDeleteTable = (tableId) => {
    setLoading(true);
    instance
      .delete(`/TABLE-SERVICE/api/v1/tables/${tableId}`)
      .then((response) => {
        setLoading(false);
        setSuccessMessage("Table deleted successfully.");
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        setTables(tables.filter((table) => table.id !== tableId));
      })
      .catch((error) => {
        setLoading(false);
        setErrorMessage("Failed to delete table.", error.response.data);
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        console.error("Error deleting table:", error);
      });
  };

  const handleAddTableToZone = () => {
    setLoading(true);
    axios
      .post(`/TABLE-SERVICE/api/v1/zones/${selectedZone.id}/tables`)
      .then((response) => {
        setLoading(false);
        setSuccessMessage("Table added successfully.");
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        setTables((prev) => [...prev, response.data]);
      })
      .catch((error) => {
        setLoading(false);
        console.log(error);
        setErrorMessage(`Failed to add table, ${error.response.data}`);
        setTimeout(() => {
          setErrorMessage("");
        }, 1000);
        console.error("Error adding table:", error);
      });
  };

  const handleAddZoneClick = () => {
    setShowAddZoneForm(!showAddZoneForm);
  };

  const handleAddZone = (e) => {
    e.preventDefault();
    if (newZone.description.trim() === "") {
      setErrorMessage("Description cannot be empty.");
      setTimeout(() => {
        setErrorMessage("");
      }, 1000);
      return;
    }
    setLoading(true);
    axios
      .post("/TABLE-SERVICE/api/v1/zones", newZone)
      .then((response) => {
        setLoading(false);
        setSuccessMessage("Zone added successfully.");
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        setShowAddZoneForm(false);
        setZones((prevZones) => [...prevZones, response.data]);
        setNewZone((prev) => {
          return {
            ...prev,
            description: "",
          };
        });
        setSelectedZone(response.data);
      })
      .catch((error) => {
        setLoading(false);
        console.error("Error adding zone:", error.response.data);
        setErrorMessage("Failed to add zone.");
        setTimeout(() => {
          setErrorMessage("");
        }, 1000);
      });
  };

  const handleNewZone = (e) => {
    setNewZone((prev) => {
      return {
        ...prev,

        [e.target.id]: e.target.value,
      };
    });
  };

  const handleDeleteZone = (zoneId, forceDelete) => {
    setLoading(true);
    axios
      .delete(
        `/TABLE-SERVICE/api/v1/zones/${zoneId}?forceDelete=${forceDelete}`
      )
      .then((response) => {
        setLoading(false);
        setSuccessMessage("Zone deleted successfully.");
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        setZones(zones.filter((zone) => zone.id !== zoneId));
        if (selectedZone.id == zoneId) {
          setSelectedZone(null);
        }
      })
      .catch((error) => {
        setLoading(false);
        if (error.response && error.response.status === 409) {
          setErrorMessage(
            "Zone is not empty. Do you want to delete all tables?"
          );

          setShowConfirmationPopup(true);
          setDeleteZoneId(zoneId);
        } else {
          setErrorMessage("Failed to delete zone.");
          setTimeout(() => {
            setErrorMessage("");
          }, 1000);
        }
      });
  };
  const handleUpdateZone = (zoneId, updatedData) => {
    setLoading(true);
    axios
      .put(`/TABLE-SERVICE/api/v1/zones/${zoneId}`, updatedData)
      .then((response) => {
        setLoading(false);
        const zone = zones.find((zone) => zone == zoneId);
        setZones((prevZones) => {
          return prevZones.map((zone) => {
            if (zone.id === zoneId) {
              return {
                ...zone,
                description: updatedData.description,
                maxSize: updatedData.maxSize,
              };
            } else {
              return zone;
            }
          });
        });
        setSuccessMessage("Zone updated successfully");
      })
      .catch((error) => {
        setLoading(false);
        setErrorMessage("Failed to update zone.");
        console.error("Error updating zone:", error);
      });
  };
  return (
    <div className="md:flex justify-center p-6  flex-1 items-center">
      <div className="absolute right-0 top-0 mr-5 mt-5">
        {loading && (
          <div className="flex justify-center">
            <div className="spinner"></div>
          </div>
        )}
        {successMessage && (
          <div className="text-green-600">{successMessage}</div>
        )}
        {errorMessage && <div className="text-red-600">{errorMessage}</div>}
      </div>
      <div className="w-full md:w-1/2 flex-1 px-4 ">
        <h1 className="text-2xl font-bold mb-4">Zones</h1>
        <div className="overflow-x-auto shadow-md rounded-xl">
          <table className="w-full table-auto border-collapse border border-gray-300 ">
            <thead>
              <tr className="bg-black text-white">
                <th className="px-4 py-2 border border-gray-300">ID</th>
                <th className="px-4 py-2 border border-gray-300">
                  Description
                </th>
                <th className="px-4 py-2 border border-gray-300">Max Size</th>
                <th className="px-4 py-2 border border-gray-300">Actions</th>
              </tr>
            </thead>

            <tbody>
              {zones.map((zone) => (
                <ZoneComponent
                  key={zone.id}
                  zone={zone}
                  onClick={handleZoneClick}
                  onDelete={handleDeleteZone}
                  onUpdate={handleUpdateZone}
                />
              ))}
            </tbody>
          </table>
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
            onClick={() => handleAddZoneClick()}
            className={`px-4 py-2 bg-blue-500 text-white rounded `}
          >
            Add New Zone
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
      {showAddZoneForm && (
        <div className="w-full md:w-1/2 px-4 mt-8 md:mt-0 absolute bg-white  shadow-md rounded-xl  z-10 border-collapse border border-gray-300 p-7">
          <h1 className="text-2xl font-bold mb-4 text-center">Add Zone</h1>
          <form>
            <div className="mb-4">
              <label
                htmlFor="description"
                className="block text-sm font-bold mb-2"
              >
                Description
              </label>
              <input
                type="text"
                id="description"
                value={newZone.description}
                placeholder="Write a description"
                onChange={(e) => handleNewZone(e)}
                className="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
            <div className="mb-4">
              <label htmlFor="maxSize" className="block text-sm font-bold mb-2">
                Max Size
              </label>
              <input
                type="number"
                id="maxSize"
                value={newZone.maxSize}
                min={1}
                onChange={(e) => handleNewZone(e)}
                className="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
            <div className="flex justify-between mt-4">
              <button
                onClick={(e) => handleAddZone(e)}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              >
                Add Zone
              </button>
              <button
                onClick={handleAddZoneClick}
                className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}
      {showConfirmationPopup && (
        <ConfirmationPopup
          message={errorMessage}
          onConfirm={() => {
            setErrorMessage("");
            handleDeleteZone(deleteZoneId, true);
            setShowConfirmationPopup(false);
          }}
          onCancel={() => {
            setShowConfirmationPopup(false);
            setErrorMessage("");
          }}
        />
      )}
      {selectedZone && (
        <div className="w-full md:w-1/2 px-4 mt-8 md:mt-0 ">
          <div className="flex justify-between  ">
            <h1 className="text-2xl font-bold mb-4">
              Tables Of Zone {selectedZone.id}
            </h1>
            <div className="flex justify-between mb-4 ">
              <div className="flex items-center">
                <label className="mr-2">Filter:</label>
                <select
                  value={occupiedFilter}
                  onChange={handleOccupiedFilterChange}
                  className="px-2 py-1 border border-gray-300 rounded"
                >
                  <option value="all">All</option>
                  <option value="occupied">Occupied</option>
                  <option value="notOccupied">Not Occupied</option>
                </select>
              </div>
            </div>
          </div>

          <div className="overflow-x-auto shadow-md rounded-xl">
            <table className="w-full table-auto border-collapse border border-gray-300">
              <thead>
                <tr className="bg-black text-white">
                  <th className="px-4 py-2 border border-gray-300">ID</th>
                  <th className="px-4 py-2 border border-gray-300">Status</th>
                  <th className="px-4 py-2 border border-gray-300">
                    Reservation IDs
                  </th>
                  <th className="px-4 py-2 border border-gray-300">Actions</th>
                </tr>
              </thead>

              <tbody>
                {tables.map((table) => (
                  <TableComponent
                    key={table.id}
                    table={table}
                    onDelete={handleDeleteTable}
                  />
                ))}
              </tbody>
            </table>
          </div>

          <div className="flex justify-between mt-4">
            <button
              onClick={() => handleTablePagination(tablePage - 1)}
              disabled={tablePage === 1}
              className={`px-4 py-2 bg-blue-500 text-white rounded ${
                tablePage === 1 ? "opacity-50 cursor-not-allowed" : ""
              }`}
            >
              Previous
            </button>
            <button
              onClick={() => handleAddTableToZone()}
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            >
              Add Table
            </button>
            <button
              onClick={() => handleTablePagination(tablePage + 1)}
              disabled={tables.length < pageSize}
              className={`px-4 py-2 bg-blue-500 text-white rounded ${
                tables.length < pageSize ? "opacity-50 cursor-not-allowed" : ""
              }`}
            >
              Next
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TablePage;
