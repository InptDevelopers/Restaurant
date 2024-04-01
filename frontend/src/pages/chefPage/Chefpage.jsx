import React from "react";
import TableChef from "../../components/users/chef/TableChef";

const ChefPage = () => {
  return (
    <div className="flex flex-col gap-10 mt-10 justify-center items-center h-full">
      <h1 className="font-bold text-2xl text-black">CHEFS</h1>
      <div className="lg:w-[1000px] w-full">
        <TableChef />
      </div>
    </div>
  );
};

export default ChefPage;
