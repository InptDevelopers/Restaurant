import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import PaginationComponent from "./components/restaurant";
import RestauList from "./components/restaurantclient";
import RestaurantDetails from "./components/restaurantdetails";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<PaginationComponent />} />
        <Route
          path="/restaurant/:id"
          element={<RestaurantDetails></RestaurantDetails>}
        />
      </Routes>
    </Router>
  );
}

export default App;
