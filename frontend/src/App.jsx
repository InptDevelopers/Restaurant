import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TablePage from "./pages/table-page/TablePage";
import PaginationComponent from "./pages/restaurantpages/restaurant";
import RestauList from "./pages/restaurantpages/restaurantclient";
import RestaurantDetails from "./pages/restaurantpages/restaurantdetails";
import RestaurantDetailsclient from "./pages/restaurantpages/Restaurantdetailsclient";
import ClientPage from "./pages/clientPage/ClientPage";
import ChefPage from "./pages/chefPage/Chefpage";
import WaiterPage from "./pages/waiterPage/WaiterPage";
import Login from "./pages/login/Login";
import RequiredAuth from "./components/RequiredAuth/RequiredAuth";
import SignUp from "./pages/SignUp/SignUp";
import RestaurantAdmin from "./pages/restaurantpages/RestaurantAdmin";
import Reservation from "./pages/reservation/Reservation";
import Error403Page from "./pages/Error403Page";

function App() {
  return (
    <div className="min-h-screen max-w-screen flex flex-col">
      <Router>
        <Routes>
          <Route element={<RequiredAuth />}>
            <Route path="/register" element={<SignUp />} />
            <Route path="/login" element={<Login />} />

            {/* clients routes */}
            <Route path="/restaurants" element={<RestauList />} />
            <Route
              path="/restaurants/:id"
              element={<RestaurantDetailsclient />}
            />
            <Route path="/reservations" element={<Reservation />} />

            {/* admin routes */}
            <Route path="/" element={<RestaurantAdmin />} />
            <Route path="/admin/restaurant" element={<RestaurantAdmin />} />
            <Route path="/admin/tables" element={<TablePage />} />
            <Route path="/admin/waiters" element={<WaiterPage />} />
            <Route path="/admin/chefs" element={<ChefPage />} />

            {/* 
            <Route path="/admin/clients" element={<ClientPage />} />
            <Route path="/restaurantadmin" element={<PaginationComponent />} />
            <Route path="/restaurant/:id" element={<RestaurantDetails />} /> 
            */}
          </Route>

          <Route path="/403" element={<Error403Page />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
