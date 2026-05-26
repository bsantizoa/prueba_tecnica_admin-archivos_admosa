import { useState } from "react";
import api from "../services/api";

function Login({ onLogin }) {
  const [usuario, setUsuario] = useState("");
  const [password, setPassword] = useState("");

  const login = async (e) => {
    e.preventDefault();

    try {
      const res = await api.post("/usuarios/login", {
        usuario,
        password,
      });

      // guardar sesión
      onLogin(res.data);

      alert("Login exitoso");
      console.log("Usuario logueado:", res.data);

    } catch (error) {
      alert("Credenciales incorrectas");
      console.log(error);
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Login</h2>

      <form onSubmit={login}>
        <input
          placeholder="Usuario"
          value={usuario}
          onChange={(e) => setUsuario(e.target.value)}
        />
        <br /><br />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br /><br />

        <button type="submit">Entrar</button>
      </form>
    </div>
  );
}

export default Login;