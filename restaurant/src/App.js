import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TableList from "./components/TableList";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<TableList />} />
      </Routes>
    </Router>
  );
}

export default App;
