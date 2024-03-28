import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TablePage from "./pages/table-page/TablePage";
import PaginationComponent from "./pages/restaurantpages/restaurant";
import RestauList from "./pages/restaurantpages/restaurantclient";
import RestaurantDetails from "./pages/restaurantpages/restaurantdetails";
import Menu from "./components/restaurantcomponents/Menu";
import RestaurantDetailsclient from "./pages/restaurantpages/Restaurantdetailsclient";
import ClientPage from "./pages/clientPage/ClientPage";
import ChefPage from "./pages/chefPage/Chefpage";
import WaiterPage from "./pages/waiterPage/WaiterPage";

function App() {
  return (
    <div className="h-screen w-screen ">
      <Router>
        <Routes>
          <Route path="/" element={<TablePage />} />
          <Route path="/clients" element={< ClientPage/>} />
          <Route path="/chefs" element={<ChefPage />} />
          <Route path="/waiters" element={<WaiterPage />} />


          <Route path="/restaurantadmin" element={<PaginationComponent />} />
          <Route path="/restaurant" element={<RestauList />} />
          <Route
            path="/restaurant/:id"
            element={<RestaurantDetails></RestaurantDetails>}
          />
          <Route
            path="/restaurantclient/:id"
            element={<RestaurantDetailsclient></RestaurantDetailsclient>}
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
