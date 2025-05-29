import React, { useState } from "react";
import axios from "axios";

function DetalleSolicitud() {
  const [entrada, setEntrada] = useState("");
  const [detalle, setDetalle] = useState(null);
  const [error, setError] = useState("");

  const buscarDetalle = async () => {
    setError("");
    setDetalle(null);
    let id = null;

    try {
      // 1. Intentar como ID directamente
      const resById = await axios.get(
        `http://localhost:8080/solicitudes/${entrada}`
      );
      id = resById.data.id;
    } catch (_) {
      try {
        // 2. Intentar como número de identificación
        const resByIdent = await axios.get(
          `http://localhost:8080/solicitudes/identificacion/${entrada}`
        );
        id = resByIdent.data.id;
      } catch (err) {
        setError(
          "No se encontró la solicitud con ese ID o número de identificación."
        );
        return;
      }
    }

    try {
      // 3. Obtener detalles
      const detalleRes = await axios.get(
        `http://localhost:8080/solicitudes/${id}`
      );
      setDetalle(detalleRes.data);
    } catch (err) {
      setError("Error al obtener los detalles de la solicitud.");
    }
  };

  return (
    <div className="card">
      <h2>Detalle de Solicitud</h2>
      <input
        className="input"
        value={entrada}
        onChange={(e) => setEntrada(e.target.value)}
        placeholder="ID o número de identificación"
      />
      <button className="btn" onClick={buscarDetalle}>
        Buscar
      </button>

      {error && <p className="error">{error}</p>}

      {detalle && (
        <div className="detalle">
          <p>
            <strong>Identificación:</strong> {detalle.numeroIdentificacion}
          </p>
          <p>
            <strong>Nombre:</strong> {detalle.nombres} {detalle.apellidos}
          </p>
          <p>
            <strong>Correo:</strong> {detalle.correoElectronico}
          </p>
          <p>
            <strong>Estado:</strong> {detalle.estado}
          </p>
          <p>
            <strong>Motivo:</strong>{" "}
            {detalle.motivoResultado || "Sin evaluación"}
          </p>
        </div>
      )}
    </div>
  );
}

export default DetalleSolicitud;
