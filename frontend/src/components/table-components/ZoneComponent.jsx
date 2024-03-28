// ZoneComponent.js
import React, { useEffect, useRef, useState } from "react";
import { MdCancelPresentation, MdDelete } from "react-icons/md";
import Button from "./Button";
import { IoMdSave } from "react-icons/io";
import { AiOutlineForm } from "react-icons/ai";

const ZoneComponent = ({ zone, onClick, onDelete, onUpdate }) => {
  const [isEditing, setIsEditing] = useState(false);

  const inputRefDescription = useRef(null);

  useEffect(() => {
    if (isEditing) {
      inputRefDescription.current.focus();
    } else {
      inputRefDescription.current.blur();
    }
  }, [isEditing]);
  const [updatedZone, setUpdatedZone] = useState({
    description: zone.description,
    maxSize: zone.maxSize,
  });
  const handleDelete = () => {
    onDelete(zone.id, false);
  };

  const handleUpdate = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    onUpdate(zone.id, updatedZone);
    setIsEditing(false);
  };

  const handleChange = (e) => {
    setUpdatedZone((prev) => {
      return {
        ...prev,
        [e.target.name]: e.target.value,
      };
    });
  };
  const handleCancel = () => {
    setUpdatedZone({
      description: zone.description,
      maxSize: zone.maxSize,
    });
    setIsEditing(false);
  };
  return (
    <tr
      onClick={() => onClick(zone)}
      className="cursor-pointer hover:bg-gray-100 text-center"
    >
      <td className="px-4 py-2">{zone.id}</td>
      <td className="px-4 py-2">
        <input
          type="text"
          name="description"
          disabled={!isEditing}
          value={updatedZone.description}
          onChange={handleChange}
          ref={inputRefDescription}
          className="bg-transparent"
        />
      </td>
      <td className="px-4 py-2">
        <input
          type="number"
          name="maxSize"
          disabled={!isEditing}
          value={updatedZone.maxSize}
          onChange={handleChange}
          className="bg-transparent "
        />
      </td>
      <td className="px-4 py-2 flex gap-2 flex justify-center items-center">
        {isEditing ? (
          <>
            <Button icon={<IoMdSave />} action={handleSave} />
            <Button icon={<MdCancelPresentation />} action={handleCancel} />
          </>
        ) : (
          <>
            <Button icon={<AiOutlineForm />} action={handleUpdate} />
            <Button
              icon={<MdDelete className="text-red-600" />}
              action={handleDelete}
            />
          </>
        )}
      </td>
    </tr>
  );
};

export default ZoneComponent;
