import React from "react";

function Button({ icon, action }) {
  return (
    <button className="  text-black text-[25px]" onClick={action}>
      {icon}
    </button>
  );
}

export default Button;
