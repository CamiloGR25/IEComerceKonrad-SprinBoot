import React, { useEffect, useState } from 'react';
import axios from 'axios';

function SolicitudesPendientes() {
  const [solicitudes, setSolicitudes] = useState([]);

  useEffect(() => {
  axios.get('http://localhost:8080/solicitudes/pendientes')
    .then(res => {
      console.log('Solicitudes pendientes:', res.data);
      setSolicitudes(res.data);
    })
    .catch(err => console.error('Error al cargar pendientes:', err));
}, []);

  return (
    <div>
      <h2>Solicitudes Pendientes</h2>
      <ul>
        {solicitudes.map(s => (
          <li key={s.id}>
            #{s.id} - {s.nombres} {s.apellidos} - Estado: {s.estado}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default SolicitudesPendientes;
