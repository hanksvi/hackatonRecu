import axios from 'axios';

// Servicio de autenticación
export const loginService = async (email: string, password: string): Promise<string> => {
  try {
    const response = await axios.post('/api/login', { email, password });
    const { token } = response.data;

    // Guardar token en localStorage
    localStorage.setItem('authToken', token);
    return token;
  } catch (error: any) {
    throw new Error(
      error.response?.data?.message || 'Error al iniciar sesión. Inténtalo nuevamente.'
    );
  }
};


// Servicio de registro
export const registerService = async (
    nombre: string,
    apellido: string,
    correo: string,
    contraseña: string
  ): Promise<void> => {
    try {
      await axios.post('/api/register', {
        nombre,
        apellido,
        correo,
        contraseña,
      });
    } catch (error: any) {
      throw new Error(
        error.response?.data?.message || 'Error al registrar. Inténtalo nuevamente.'
      );
    }
  };