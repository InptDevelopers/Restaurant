import React from "react";
import TableClient from "../../components/users/client/TableClient";

const ClientPage = () => {
  return (
    <div className="flex flex-col gap-10 mt-10 justify-center items-center h-full">
      <h1 className="font-bold text-2xl text-black">CLIENTS</h1>
      <div className="lg:w-[1000px] w-full ">
        <TableClient />
      </div>
    </div>
  );
};

export default ClientPage;
