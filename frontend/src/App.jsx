import { useState } from "react";
import Login from "./pages/Login";
import Dashboard from "./pages/MostrarArchivos";

function App() {
  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem("user"))
  );

  const handleLogin = (data) => {
    localStorage.setItem("user", JSON.stringify(data));
    setUser(data); 
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  return user ? (
    <Dashboard user={user} onLogout={handleLogout} />
  ) : (
    <Login onLogin={handleLogin} />
  );

}

export default App;