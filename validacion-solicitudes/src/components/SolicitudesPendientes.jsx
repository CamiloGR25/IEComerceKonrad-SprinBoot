import React, { useEffect, useState } from "react";
import axios from "axios";

function SolicitudesPendientes() {
  const [solicitudes, setSolicitudes] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/solicitudes/pendientes")
      .then((res) => setSolicitudes(res.data))
      .catch((err) => console.error("Error al cargar pendientes:", err));
  }, []);

  return (
    <div className="card">
      <h2>Solicitudes Pendientes</h2>
      <table className="tabla">
        <thead>
          <tr>
            <th>ID</th>
            <th>Identificación</th>
            <th>Apellidos</th>
            <th>Nombres</th>
            <th>Correo</th>
            <th>Estado</th>
          </tr>
        </thead>
        <tbody>
          {solicitudes.map((s) => (
            <tr key={s.id}>
              <td>
                <strong className="resaltado">#{s.id}</strong>
              </td>
              <td>{s.numeroIdentificacion}</td>
              <td>{s.apellidos}</td>
              <td>{s.nombres}</td>
              <td>{s.correoElectronico}</td>
              <td>{s.estado}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default SolicitudesPendientes;
