import React from "react";
import TableWaiter from "../../components/users/waiter/TableWaiter";

const WaiterPage = () => {
  return (
    <div className="flex flex-col gap-10 mt-10 justify-center items-center h-full">
      <h1 className="font-bold text-2xl text-black">WAITERS</h1>
      <div className="lg:w-[1000px] w-full">
        <TableWaiter />
      </div>
    </div>
  );
};

export default WaiterPage;
