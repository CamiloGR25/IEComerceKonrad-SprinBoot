import React, { useState } from "react";
import axios from "axios";

function SolicitudesPorEstado() {
  const [estado, setEstado] = useState("");
  const [solicitudes, setSolicitudes] = useState([]);

  const cargarSolicitudes = async (nuevoEstado) => {
    try {
      setEstado(nuevoEstado);
      const res = await axios.get(
        `http://localhost:8080/solicitudes/estado/${nuevoEstado}`
      );
      setSolicitudes(res.data);
    } catch (err) {
      console.error("Error al cargar solicitudes:", err);
      setSolicitudes([]);
    }
  };

  return (
    <div className="card">
      <h2>Solicitudes por Estado</h2>
      <div className="btn-group centrado">
        <button
          className="btn btn-aprobada"
          onClick={() => cargarSolicitudes("APROBADA")}
        >
          Ver Aprobadas
        </button>
        <button
          className="btn btn-devuelta"
          onClick={() => cargarSolicitudes("DEVUELTA")}
        >
          Ver Devueltas
        </button>
        <button
          className="btn btn-rechazada"
          onClick={() => cargarSolicitudes("RECHAZADA")}
        >
          Ver Rechazadas
        </button>
      </div>

      {estado && (
        <>
          <h3>Estado: {estado}</h3>
          <table className="tabla">
            <thead>
              <tr>
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
                  <td>{s.numeroIdentificacion}</td>
                  <td>{s.apellidos}</td>
                  <td>{s.nombres}</td>
                  <td>{s.correoElectronico}</td>
                  <td>{s.estado}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
    </div>
  );
}

export default SolicitudesPorEstado;
