import { useEffect, useState } from "react";
import api from "../services/api";

function Historial({ user }) {

  const [historial, setHistorial] = useState([]);

  useEffect(() => {
    cargarHistorial();
  }, []);

  const cargarHistorial = async () => {

    try {

      const res = await api.get("/historial");

      setHistorial(res.data);

    } catch (error) {

      console.log(error);
    }
  };

  return (

    <div style={{ marginTop: 30 }}>

      <h2>Historial</h2>

      <table border="1" cellPadding="8">

        <thead>
          <tr>
            <th>ID</th>
            <th>Archivo</th>
            <th>Usuario</th>
            <th>Acción</th>
            <th>Comentario</th>
          </tr>
        </thead>

        <tbody>

          {historial.map((h) => (

            <tr key={h.idHistorial}>

              <td>{h.idHistorial}</td>

              <td>{h.archivo?.txtNomOri}</td>

              <td>{h.usuario?.txtUsuario}</td>

              <td>{h.accion?.txtAccion}</td>

              <td>{h.txtComentario}</td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>
  );
}

export default Historial;