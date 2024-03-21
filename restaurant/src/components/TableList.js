// components/TableList.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import TableComponent from "./TableComponent";
import ZoneComponent from "./ZoneComponent";
import "../styles/table.css";
const TableList = () => {
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

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/api/v1/zones/?page=${
          zonePage - 1
        }&size=${pageSize}&restaurantId=1`
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
    axios
      .delete(`http://localhost:8080/api/v1/tables/${tableId}`)
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
        setErrorMessage("Failed to delete table.");
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        console.error("Error deleting table:", error);
      });
  };

  const handleAddTableToZone = () => {
    setLoading(true);
    axios
      .post(`http://localhost:8080/api/v1/zones/${selectedZone.id}/tables`)
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
        setErrorMessage(`Failed to add table, ${error}`);
        setTimeout(() => {
          setSuccessMessage("");
        }, 1000);
        console.error("Error adding table:", error);
      });
  };
  return (
    <div className="md:flex justify-center p-6  min-h-[100vh] items-center">
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
      <div className="w-full md:w-1/2 px-4">
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
              </tr>
            </thead>

            <tbody>
              {zones.map((zone) => (
                <ZoneComponent
                  key={zone.id}
                  zone={zone}
                  onClick={handleZoneClick}
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

      {selectedZone && (
        <div className="w-full md:w-1/2 px-4 mt-8 md:mt-0 ">
          <div className="flex justify-between  ">
            {" "}
            <h1 className="text-2xl font-bold mb-4">Tables</h1>
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

export default TableList;
