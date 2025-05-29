import React, { useState } from 'react';
import axios from 'axios';

function ValidarSolicitud() {
  const [id, setId] = useState('');
  const [mensaje, setMensaje] = useState('');

  const validar = () => {
    axios.post(`http://localhost:8080/solicitudes/${id}/validar`)
      .then(res => setMensaje(res.data))
      .catch(err => setMensaje(err.response?.data || "Error al validar"));
  };

  return (
    <div>
      <h2>Validar Solicitud</h2>
      <input value={id} onChange={e => setId(e.target.value)} placeholder="ID" />
      <button onClick={validar}>Validar</button>
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}

export default ValidarSolicitud;
