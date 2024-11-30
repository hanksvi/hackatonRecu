import React, { useState } from 'react';
import { registerService } from "../services";

const Register: React.FC = () => {
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState(''); 
  const [correo, setCorreo] = useState('');
  const [contraseña, setContraseña] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    // Validaciones básicas
    if (!nombre || !apellido || !correo || !contraseña) {
      setError('Por favor, completa todos los campos.');
      return;
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)) {
      setError('El correo no es válido.');
      return;
    }
    if (contraseña.length < 6) {
      setError('La contraseña debe tener al menos 6 caracteres.');
      return;
    }

    try {
      // Llamar al servicio de registro
      await registerService(nombre, apellido, correo, contraseña);
      setSuccess(true);
      setError('');
    } catch (err: any) {
      setError(err.message);
    }
  };

  return (
    <div>
      <h1>Registro</h1>
      {success ? (
        <p style={{ color: 'green' }}>Registro exitoso. Ahora puedes iniciar sesión.</p>
      ) : (
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="nombre">Nombre:</label>
            <input
              type="text"
              id="nombre"
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
            />
          </div>
          <div>
            <label htmlFor="apellido">Apellido:</label>
            <input
              type="text"
              id="apellido"
              value={apellido}
              onChange={(e) => setApellido(e.target.value)}
            />
          </div>
          <div>
            <label htmlFor="correo">Correo:</label>
            <input
              type="email"
              id="correo"
              value={correo}
              onChange={(e) => setCorreo(e.target.value)}
            />
          </div>
          <div>
            <label htmlFor="contraseña">Contraseña:</label>
            <input
              type="password"
              id="contraseña"
              value={contraseña}
              onChange={(e) => setContraseña(e.target.value)}
            />
          </div>
          {error && <p style={{ color: 'red' }}>{error}</p>}
          <button type="submit">Registrarse</button>
        </form>
      )}
    </div>
  );
};

export default Register;
