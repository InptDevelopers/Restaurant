import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import PaginationComponent from "./components/restaurant";
import RestauList from "./components/restaurantclient";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<RestauList />} />
      </Routes>
    </Router>
  );
}

export default App;
