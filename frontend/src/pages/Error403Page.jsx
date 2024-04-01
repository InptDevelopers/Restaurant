import React from "react";

const Error403Page = () => {
  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <img
        src="../images/403.png"
        alt="403 Forbidden"
        className="w-40 h-auto mb-6"
      />
      <h1 className="text-3xl font-bold mb-4">403 - Forbidden</h1>
      <p className="text-lg text-center">
        Oops! It seems you don&apos;t have permission to access this page.
      </p>
    </div>
  );
};

export default Error403Page;
