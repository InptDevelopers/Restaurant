const columns = [
  {name: "serial", uid: "serial",sortable: true},
  {name: "Name", uid: "name", sortable: true},
  {name: "Type", uid: "type", sortable: true},
  {name: "Marque", uid: "marque", sortable: true},
  {name: "Serie", uid: "serie", sortable: true},
  {name: "TokenId", uid: "tokenId"},
  {name: "STATUS", uid: "status", sortable: true},
  {name: "ACTIONS", uid: "actions"},
];

const statusOptions = [
  {name: "Active", uid: "active"},
  {name: "Paused", uid: "paused"},
  {name: "Vacation", uid: "vacation"},
];


export {columns, statusOptions};
