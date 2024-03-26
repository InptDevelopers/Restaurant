import './App.css'
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Reservation from './components/Reservation';

function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Reservation />} />
      </Routes>
    </Router>
  )
}

export default App
