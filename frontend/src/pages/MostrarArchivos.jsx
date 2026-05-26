import UploadFile from "../componentes/subirArchivo";
import { useEffect, useState } from "react";
import api from "../services/api";
import Historial from "./Historial";

function Dashboard({ user, onLogout }) {

  const [archivos, setArchivos] = useState([]);

    useEffect(() => {
        cargarArchivos();
    }, []);

    const cargarArchivos = async () => {
        try {
            //const res = await api.get("/archivos");
            const res = await api.get(`/archivos/usuario/${user.idUsuario}`);
            setArchivos(res.data);
        } catch (error) {
            console.log("Error cargando archivos", error);
        }
    };

    const visualizarArchivo = (id) => {
        window.open(
        `http://localhost:8081/api/archivos/ver/${id}`,
        "_blank"
        );
    };

    const eliminarArchivo = async (id) => {
        try {
            await api.delete(`/archivos/eliminar/${id}`);
            cargarArchivos();
        } catch (error) {
            console.log("Error eliminando archivo", error);
        }
    };

    const descargarArchivo = async (id, nombre) => {
        try {

            const response = await api.get(
            `/archivos/descargar/${id}`,
            {
                responseType: "blob",
            }
            );

            // crear URL temporal
            const url = window.URL.createObjectURL(
            new Blob([response.data])
            );

            // crear link invisible
            const link = document.createElement("a");

            link.href = url;
            link.setAttribute("download", nombre);

            document.body.appendChild(link);

            // descargar
            link.click();

            // limpiar
            link.remove();

        } catch (error) {
            console.log("Error descargando archivo", error);
        }
    };

  return (
    <div style={{ padding: 20 }}>
      <h2>Dashboard</h2>

      <button onClick={onLogout} style={{ marginBottom: 10 }}>
        Cerrar sesión
      </button>

      <p>Usuario: {user?.txtUsuario}</p>

      {/* SOLO SI NO ES ADMIN */}
      {Number(user?.rol?.idRol) !== 4 && (
        <UploadFile onUpload={cargarArchivos} />
      )}

      <h3>Archivos</h3>

      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre original</th>
            <th>Tipo</th>
            <th>Peso</th>
            <th>Acciones</th>
          </tr>
        </thead>

        <tbody>
          {archivos.map((a) => (
            <tr key={a.idArchivo}>
              <td>{a.idArchivo}</td>
              <td>{a.txtNomOri}</td>
              <td>{a.txtTipoMime}</td>
              <td>{a.pesoByte}</td>

              <td>
                {(

                    // ADMIN
                    Number(user?.rol?.idRol) === 4 ||

                    // GERENTE
                    Number(user?.rol?.idRol) === 3 ||

                    // dueño archivo
                    user?.idUsuario === a.usuario?.idUsuario

                ) && (

                    <button onClick={() => eliminarArchivo(a.idArchivo)}>
                    Eliminar
                    </button>

                )}


                <button onClick={() => descargarArchivo(a.idArchivo, a.txtNomOri)}>
                    Descargar
                </button>

                <button onClick={() => visualizarArchivo(a.idArchivo)}>
                    Ver
                </button>

              </td>
            </tr>
          ))}
        </tbody>
      </table>
    
    {(user?.rol?.idRol === 3 ||
         user?.rol?.idRol === 4) && (
        <Historial user={user} />

    )}


    </div>
  );
}

export default Dashboard;