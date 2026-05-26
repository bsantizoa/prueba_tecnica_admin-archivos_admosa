import { useState } from "react";
import api from "../services/api";

function UploadFile({ onUpload }) {
  const [file, setFile] = useState(null);

  const user = JSON.parse(localStorage.getItem("user"));

  const subirArchivo = async () => {
    if (!file) return alert("Selecciona un archivo");

    const formData = new FormData();
    formData.append("archivo", file);
    formData.append("idUsuario", user.idUsuario);

    try {
      await api.post("/archivos/subir", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      alert("Archivo subido correctamente");

      setFile(null);
      onUpload(); // recargar lista

    } catch (error) {
      console.log(error);
      alert("Error al subir archivo");
    }
  };

  return (
    <div style={{ marginBottom: 20 }}>
      <h3>Subir archivo</h3>

      <input
        type="file"
        onChange={(e) => setFile(e.target.files[0])}
      />

      <button onClick={subirArchivo}>
        Subir
      </button>
    </div>
  );
}

export default UploadFile;