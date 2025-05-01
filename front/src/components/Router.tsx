import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Books from "./Books.tsx";

const RouterComponent: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Books />} />
      </Routes>
    </Router>
  );
};

export default RouterComponent;
