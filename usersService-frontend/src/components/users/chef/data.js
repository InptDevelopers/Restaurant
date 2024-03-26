import React from "react";
const columns = [
  {name: "ID", uid: "id", sortable: true},
  {name: "NAME", uid: "name", sortable: true},
  {name: "EMAIL", uid: "email"},
  {name: "BANKACCOUNT", uid: "bankAccount", sortable: true},
  {name: "COMMANDS", uid: "commands"},
  {name: "STATUS", uid: "status", sortable: true},
  {name: "ACTIONS", uid: "actions"},
  
];

const statusOptions = [
  {name: "Active", uid: "active"},
  {name: "Paused", uid: "paused"},
  {name: "Vacation", uid: "vacation"},
];

export {columns, statusOptions};
