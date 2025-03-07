-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-02-2025 a las 18:04:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `diccionariodb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `definicion`
--

CREATE TABLE `definicion` (
  `id` bigint(20) NOT NULL,
  `descripcion` text NOT NULL,
  `ejemplo` text NOT NULL,
  `palabra_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `definicion`
--

INSERT INTO `definicion` (`id`, `descripcion`, `ejemplo`, `palabra_id`) VALUES
(1, 'Relación de afecto y confianza entre personas.', 'La amistad verdadera es invaluable.', 1),
(2, 'Que brilla con intensidad.', 'El diamante es una piedra brillante.', 2),
(3, 'Moverse rápidamente con los pies.', 'Me gusta correr por las mañanas.', 3),
(4, 'Arte de moverse al ritmo de la música.', 'La danza es una expresión cultural.', 4),
(5, 'Superficie reflectante.', 'Me miré en el espejo antes de salir.', 5),
(6, 'Capacidad de resistencia o vigor.', 'La fuerza del atleta es impresionante.', 6),
(7, 'Sentimiento de agradecimiento.', 'Expresó su gratitud con una carta.', 7),
(8, 'Que tiene belleza.', 'El paisaje era realmente hermoso.', 8),
(9, 'Estación fría del año.', 'El invierno trae nieve y bajas temperaturas.', 9),
(10, 'Realizar una actividad lúdica.', 'A los niños les encanta jugar al aire libre.', 10),
(11, 'Unidad de masa equivalente a mil gramos.', 'Compré un kilo de manzanas.', 11),
(12, 'Energía visible que ilumina.', 'La luz del sol es vital para la vida.', 12),
(13, 'Elevación natural del terreno.', 'Escalamos la montaña más alta.', 13),
(14, 'Masa de vapor de agua en el cielo.', 'Las nubes anuncian la lluvia.', 14),
(15, 'Que carece de luz.', 'El sótano era un lugar oscuro.', 15),
(16, 'Representación artística con colores.', 'Admiro la pintura de ese museo.', 16),
(17, 'Ser mitológico con partes de diferentes animales.', 'La quimera es una criatura fascinante.', 17),
(18, 'Corriente de agua que fluye naturalmente.', 'El río desemboca en el océano.', 18),
(19, 'Serie de imágenes o pensamientos al dormir.', 'Tuve un sueño muy extraño anoche.', 19),
(20, 'Que tiene calma o serenidad.', 'El lago estaba tranquilo al amanecer.', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `palabra`
--

CREATE TABLE `palabra` (
  `id` bigint(20) NOT NULL,
  `termino` varchar(255) NOT NULL,
  `categoria_gramatical` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `palabra`
--

INSERT INTO `palabra` (`id`, `termino`, `categoria_gramatical`) VALUES
(1, 'Amistad', 'Sustantivo'),
(2, 'Brillante', 'Adjetivo'),
(3, 'Correr', 'Verbo'),
(4, 'Danza', 'Sustantivo'),
(5, 'Espejo', 'Sustantivo'),
(6, 'Fuerza', 'Sustantivo'),
(7, 'Gratitud', 'Sustantivo'),
(8, 'Hermoso', 'Adjetivo'),
(9, 'Invierno', 'Sustantivo'),
(10, 'Jugar', 'Verbo'),
(11, 'Kilo', 'Sustantivo'),
(12, 'Luz', 'Sustantivo'),
(13, 'Montaña', 'Sustantivo'),
(14, 'Nube', 'Sustantivo'),
(15, 'Oscuro', 'Adjetivo'),
(16, 'Pintura', 'Sustantivo'),
(17, 'Quimera', 'Sustantivo'),
(18, 'Río', 'Sustantivo'),
(19, 'Sueño', 'Sustantivo'),
(20, 'Tranquilo', 'Adjetivo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `definicion`
--
ALTER TABLE `definicion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `palabra_id` (`palabra_id`);

--
-- Indices de la tabla `palabra`
--
ALTER TABLE `palabra`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `termino` (`termino`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `definicion`
--
ALTER TABLE `definicion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `palabra`
--
ALTER TABLE `palabra`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `definicion`
--
ALTER TABLE `definicion`
  ADD CONSTRAINT `definicion_ibfk_1` FOREIGN KEY (`palabra_id`) REFERENCES `palabra` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
