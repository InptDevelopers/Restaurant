// TableComponent.js
import React from "react";

const TableComponent = ({ table, onDelete }) => {
  const handleDelete = () => {
    onDelete(table.id);
  };
  return (
    <tr className="border-b border-gray-200 text-center">
      <td className="px-4 py-2">{table.id}</td>
      <td className="px-4 py-2">{table.status}</td>
      <td className="px-4 py-2">{table.reservationIds?.join(", ")}</td>
      <td className="px-4 py-2">
        <button
          onClick={handleDelete}
          className="px-4 py-2 bg-red-500 text-white rounded "
        >
          Delete
        </button>
      </td>
    </tr>
  );
};

export default TableComponent;
