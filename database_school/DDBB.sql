-- Primero, eliminamos las tablas que dependen de otras (con claves foráneas)
DROP TABLE IF EXISTS Usuario_Evaluacion CASCADE;
DROP TABLE IF EXISTS Usuario_Curso CASCADE;

-- Luego, eliminamos las tablas principales
DROP TABLE IF EXISTS Curso CASCADE;
DROP TABLE IF EXISTS Usuario CASCADE;

-- Tabla Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id_usuario SERIAL PRIMARY KEY,
    dni VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apel1 VARCHAR(50) NOT NULL,
    apel2 VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('alumno', 'profesor')),
    activo BOOLEAN NOT NULL DEFAULT TRUE -- Campo para marcar si el usuario está activo
);

-- Tabla Curso
CREATE TABLE IF NOT EXISTS Curso (
    id_curso SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    duracion INT NOT NULL, -- Duración en horas
    nivel VARCHAR(20) NOT NULL CHECK (nivel IN ('básico', 'intermedio', 'avanzado')),
    id_usuario_profesor INT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE, -- Campo para marcar si el curso está activo
    FOREIGN KEY (id_usuario_profesor) REFERENCES Usuario(id_usuario)
    ON DELETE RESTRICT  -- No permitir eliminar un usuario que esté impartiendo un curso
    ON UPDATE CASCADE
);

-- Tabla Usuario_Curso
CREATE TABLE IF NOT EXISTS Usuario_Curso (
    id_usuario_curso SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_curso INT NOT NULL,
    fecha_inscripcion DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_fin DATE,
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('en_progreso', 'completado', 'abandonado')),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
    ON DELETE RESTRICT  -- No permitir eliminar un usuario si está inscrito en un curso
    ON UPDATE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES Curso(id_curso)
    ON DELETE RESTRICT  -- No permitir eliminar un curso si hay usuarios inscritos
    ON UPDATE CASCADE
);

-- Tabla Usuario_Evaluacion
CREATE TABLE IF NOT EXISTS Usuario_Evaluacion (
    id_usuario_evaluacion SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_curso INT NOT NULL,
    fecha DATE NOT NULL DEFAULT CURRENT_DATE,
    calificacion NUMERIC(5, 2) NOT NULL,
    aprobado BOOLEAN NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
    ON DELETE RESTRICT  -- No permitir eliminar un usuario si tiene evaluaciones
    ON UPDATE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES Curso(id_curso)
    ON DELETE RESTRICT  -- No permitir eliminar un curso si tiene evaluaciones
    ON UPDATE CASCADE
);