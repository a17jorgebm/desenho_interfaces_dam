-- Insertar usuarios de ejemplo
INSERT INTO Usuario (dni, nombre, apel1, apel2, email, tipo, activo)
VALUES
  ('12345678A', 'Juan', 'Pérez', 'García', 'juan.perez@email.com', 'alumno', TRUE),
  ('23456789B', 'Ana', 'Lopez', 'Martínez', 'ana.lopez@email.com', 'alumno', TRUE),
  ('34567890C', 'Luis', 'Sánchez', 'Fernández', 'luis.sanchez@email.com', 'alumno', TRUE),
  ('45678901D', 'Maria', 'Rodríguez', 'Gómez', 'maria.rodriguez@email.com', 'alumno', TRUE),
  ('56789012E', 'Carlos', 'Martín', 'Ruiz', 'carlos.martin@email.com', 'profesor', TRUE),
  ('67890123F', 'Laura', 'Hernández', 'López', 'laura.hernandez@email.com', 'profesor', TRUE),
  ('78901234G', 'Pedro', 'González', 'Pérez', 'pedro.gonzalez@email.com', 'alumno', TRUE),
  ('89012345H', 'Marta', 'García', 'Jiménez', 'marta.garcia@email.com', 'alumno', TRUE),
  ('90123456I', 'Santiago', 'Díaz', 'Álvarez', 'santiago.diaz@email.com', 'alumno', TRUE),
  ('11223344J', 'Clara', 'Torres', 'Serrano', 'clara.torres@email.com', 'profesor', TRUE);

-- Insertar cursos de ejemplo
INSERT INTO Curso (nombre, descripcion, duracion, nivel, id_usuario_profesor, activo)
VALUES
  ('Curso de Java Básico', 'Introducción a la programación en Java', 30, 'básico', 5, TRUE),
  ('Curso de Python Intermedio', 'Conceptos intermedios de Python', 40, 'intermedio', 6, TRUE),
  ('Curso de JavaScript Avanzado', 'Desarrollo web con JavaScript', 50, 'avanzado', 7, TRUE),
  ('Curso de SQL Básico', 'Fundamentos de bases de datos SQL', 25, 'básico', 8, TRUE),
  ('Curso de PHP Intermedio', 'Desarrollo de aplicaciones web con PHP', 35, 'intermedio', 9, TRUE),
  ('Curso de Ruby Avanzado', 'Desarrollo web con Ruby on Rails', 60, 'avanzado', 10, TRUE),
  ('Curso de HTML y CSS Básico', 'Diseño web básico con HTML y CSS', 20, 'básico', 5, TRUE),
  ('Curso de C# Intermedio', 'Desarrollo de aplicaciones en C#', 45, 'intermedio', 6, TRUE),
  ('Curso de Swift Avanzado', 'Desarrollo de aplicaciones iOS', 55, 'avanzado', 7, TRUE),
  ('Curso de Kotlin Básico', 'Introducción a la programación con Kotlin', 30, 'básico', 8, TRUE);

-- Insertar inscripciones de usuarios en cursos (Usuario_Curso)
INSERT INTO Usuario_Curso (id_usuario, id_curso, fecha_inscripcion, fecha_fin, estado)
VALUES
  (1, 1, '2024-01-15', '2024-02-15', 'completado'),
  (2, 2, '2024-02-10', NULL, 'en_progreso'),
  (3, 3, '2024-03-01', NULL, 'en_progreso'),
  (4, 4, '2024-01-10', '2024-01-25', 'completado'),
  (5, 5, '2024-02-01', NULL, 'en_progreso'),
  (6, 6, '2024-03-05', NULL, 'en_progreso'),
  (7, 7, '2024-02-15', '2024-03-15', 'completado'),
  (8, 8, '2024-03-10', NULL, 'en_progreso'),
  (9, 9, '2024-01-20', '2024-02-20', 'completado'),
  (10, 10, '2024-02-25', NULL, 'en_progreso');

-- Insertar evaluaciones de usuarios en cursos (Usuario_Evaluacion)
INSERT INTO Usuario_Evaluacion (id_usuario, id_curso, fecha, calificacion, aprobado)
VALUES
  (1, 1, '2024-02-15', 9.5, TRUE),
  (2, 2, '2024-03-20', 7.8, TRUE),
  (3, 3, '2024-04-01', 4.5, FALSE),
  (4, 4, '2024-01-25', 8.0, TRUE),
  (5, 5, '2024-02-15', 8.7, TRUE),
  (6, 6, '2024-03-10', 7.0, TRUE),
  (7, 7, '2024-03-15', 9.0, TRUE),
  (8, 8, '2024-04-05', 3.0, FALSE),
  (9, 9, '2024-02-20', 9.3, TRUE),
  (10, 10, '2024-03-25', 1.8, FALSE);