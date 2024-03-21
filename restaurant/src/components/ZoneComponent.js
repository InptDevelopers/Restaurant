// ZoneComponent.js
import React from "react";

const ZoneComponent = ({ zone, onClick }) => {
  return (
    <tr
      onClick={() => onClick(zone)}
      className="cursor-pointer hover:bg-gray-100 text-center"
    >
      <td className="px-4 py-2">{zone.id}</td>
      <td className="px-4 py-2">{zone.description}</td>
      <td className="px-4 py-2">{zone.maxSize}</td>
    </tr>
  );
};

export default ZoneComponent;
