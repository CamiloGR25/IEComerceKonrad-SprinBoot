import React from 'react';
import './App.css';
import SolicitudesPendientes from './components/SolicitudesPendientes';
import DetalleSolicitud from './components/DetalleSolicitud';
import ValidarSolicitud from './components/ValidarSolicitud';

function App() {
  return (
    <div className="container">
      <h1>Gestión de Solicitudes</h1>
      <SolicitudesPendientes />
      <DetalleSolicitud />
      <ValidarSolicitud />
    </div>
  );
}

export default App;
