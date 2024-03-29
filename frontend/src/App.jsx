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
import Login from "./pages/login/Login";
import Navbar from "./components/restaurantcomponents/navbar";
import RequiredAuth from "./components/RequiredAuth/RequiredAuth";

function App() {
  return (
    <div className="h-screen w-screen ">
      <Navbar />
      <Router>
        <Routes>
          <Route path="/" element={<TablePage />} />
          <Route path="/login" element={<Login />} />

          <Route element={<RequiredAuth />}>
            <Route path="/admin/clients" element={<ClientPage />} />
            <Route path="/chefs" element={<ChefPage />} />
            <Route path="/waiters" element={<WaiterPage />} />

            <Route path="/restaurantadmin" element={<PaginationComponent />} />
            <Route
              path="/restaurant/:id"
              element={<RestaurantDetails></RestaurantDetails>}
            />
          </Route>

          <Route path="/restaurant" element={<RestauList />} />

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
