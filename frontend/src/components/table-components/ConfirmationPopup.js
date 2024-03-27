import React from "react";

const ConfirmationPopup = ({ message, onConfirm, onCancel }) => {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-md">
        <p className="mb-4">{message}</p>
        <div className="flex justify-center">
          <button
            onClick={onConfirm}
            className="px-4 py-2 mr-4 bg-blue-500 text-white rounded"
          >
            Confirm
          </button>
          <button
            onClick={onCancel}
            className="px-4 py-2 bg-gray-500 text-white rounded"
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmationPopup;
