import React, { useState } from 'react';
import axios from 'axios';

function DetalleSolicitud() {
  const [id, setId] = useState('');
  const [detalle, setDetalle] = useState(null);

  const buscarDetalle = () => {
    axios.get(`http://localhost:8080/solicitudes/${id}`)
      .then(res => setDetalle(res.data))
      .catch(() => setDetalle(null));
  };

  return (
    <div>
      <h2>Detalle de Solicitud</h2>
      <input value={id} onChange={e => setId(e.target.value)} placeholder="ID" />
      <button onClick={buscarDetalle}>Buscar</button>
      {detalle && (
        <pre>{JSON.stringify(detalle, null, 2)}</pre>
      )}
    </div>
  );
}

export default DetalleSolicitud;
