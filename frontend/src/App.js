import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TablePage from "./pages/table-page/TablePage";
import PaginationComponent from "./pages/restaurantpages/restaurant";
import RestauList from "./pages/restaurantpages/restaurantclient";
import RestaurantDetails from "./pages/restaurantpages/restaurantdetails";
import Menu from "./components/restaurantcomponents/Menu";
import RestaurantDetailsclient from "./pages/restaurantpages/Restaurantdetailsclient";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<TablePage />} />

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
  );
}

export default App;
