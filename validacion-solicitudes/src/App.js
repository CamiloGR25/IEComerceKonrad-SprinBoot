import React, { useState } from 'react';
import './App.css';
import SolicitudesPendientes from './components/SolicitudesPendientes';
import DetalleSolicitud from './components/DetalleSolicitud';
import ValidarSolicitud from './components/ValidarSolicitud';
import SolicitudesPorEstado from './components/SolicitudesPorEstado';

function App() {
  const [vista, setVista] = useState('pendientes');

  return (
    <div className="container">
      <h1>Gestión de Solicitudes</h1>

      <nav className="menu centrado">
        <button className="btn" onClick={() => setVista('pendientes')}>Pendientes</button>
        <button className="btn" onClick={() => setVista('detalle')}>Detalle</button>
        <button className="btn" onClick={() => setVista('validar')}>Validar</button>
        <button className="btn" onClick={() => setVista('estado')}>Por Estado</button>
      </nav>

      {vista === 'pendientes' && <SolicitudesPendientes />}
      {vista === 'detalle' && <DetalleSolicitud />}
      {vista === 'validar' && <ValidarSolicitud />}
      {vista === 'estado' && <SolicitudesPorEstado />}
    </div>
  );
}


export default App;