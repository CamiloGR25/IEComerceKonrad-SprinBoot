import React, { useState } from "react";
import axios from "axios";

function ValidarSolicitud() {
  const [entrada, setEntrada] = useState("");
  const [mensaje, setMensaje] = useState("");
  const [error, setError] = useState("");
  const [detalle, setDetalle] = useState(null);

  const validar = async () => {
    setMensaje("");
    setError("");
    setDetalle(null);

    let id = null;

    try {
      // Primero, intentar como ID directamente
      const resById = await axios.get(
        `http://localhost:8080/solicitudes/${entrada}`
      );
      id = resById.data.id;
    } catch (_) {
      try {
        // Si no es un ID válido, buscar por número de identificación
        const resByIdent = await axios.get(
          `http://localhost:8080/solicitudes/identificacion/${entrada}`
        );
        id = resByIdent.data.id;
      } catch (err) {
        setError(
          "No se encontró ninguna solicitud con ese ID o número de identificación."
        );
        return;
      }
    }

    try {
      // Validar solicitud por ID
      await axios.post(`http://localhost:8080/solicitudes/${id}/validar`);
      setMensaje("Solicitud validada con éxito");

      // Obtener el detalle actualizado
      const detalleRes = await axios.get(
        `http://localhost:8080/solicitudes/${id}`
      );
      setDetalle(detalleRes.data);
    } catch (err) {
      setError(err.response?.data || "Error al validar la solicitud.");
    }
  };

  return (
    <div className="card">
      <h2>Validar Solicitud</h2>
      <input
        className="input"
        value={entrada}
        onChange={(e) => setEntrada(e.target.value)}
        placeholder="ID o número de identificación"
      />
      <button className="btn" onClick={validar}>
        Validar
      </button>

      {mensaje && <p className="success">{mensaje}</p>}
      {error && <p className="error">{error}</p>}

      {detalle && (
        <div className="detalle">
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
          {detalle && (
            <div className="correo-simulado">
              <h3>📧 Notificación simulada</h3>
              <pre>
                {`----------------------------------------
[NOTIFICACIÓN SIMULADA]
Para: ${detalle.correoElectronico}
Nombre: ${detalle.nombres} ${detalle.apellidos}
Estado: ${detalle.estado}
Motivo: ${detalle.motivoResultado || "Sin evaluación"}
----------------------------------------`}
              </pre>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default ValidarSolicitud;
