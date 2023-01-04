-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 20-12-2022 a las 00:08:10
-- Versión del servidor: 8.0.31-0ubuntu0.20.04.2
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dogfriendly`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` bigint NOT NULL,
  `fecha` date NOT NULL,
  `iva` int NOT NULL,
  `pagado` bit(1) NOT NULL,
  `id_paseo` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fecha`, `iva`, `pagado`, `id_paseo`) VALUES
(1, '2022-12-19', 21, b'1', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paseo`
--

CREATE TABLE `paseo` (
  `id` bigint NOT NULL,
  `fecha` date NOT NULL,
  `lugar` varchar(255) NOT NULL,
  `precio` double(10,2) NOT NULL,
  `id_tipopaseo` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  `id_perro` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `paseo`
--

INSERT INTO `paseo` (`id`, `fecha`, `lugar`, `precio`, `id_tipopaseo`, `id_usuario`, `id_perro`) VALUES
(1, '2022-12-19', 'valencia', 20.00, 1, 1, 1),
(2, '2022-12-18', 'castellon', 20.00, 1, 1, 1),
(3, '2022-12-18', 'Parque Central (Valencia)', 20.00, 1, 1, 1),
(4, '2022-12-18', 'Parque Central (Valencia)', 20.00, 1, 1, 1),
(5, '2022-12-18', 'El rio', 20.00, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perro`
--

CREATE TABLE `perro` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `genero` int NOT NULL,
  `imagen` longblob,
  `peso` int NOT NULL,
  `sociable` bit(1) NOT NULL,
  `puedeIrSuelto` bit(1) NOT NULL,
  `esJugueton` bit(1) NOT NULL,
  `id_raza` bigint NOT NULL,
  `id_usuario` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `perro`
--

INSERT INTO `perro` (`id`, `nombre`, `fechaNacimiento`, `genero`, `imagen`, `peso`, `sociable`, `puedeIrSuelto`, `esJugueton`, `id_raza`, `id_usuario`) VALUES
(1, 'camilo', '2022-12-19', 1, NULL, 20, b'1', b'1', b'1', 1, 1),
(2, 'Sr.Wilson', '2022-12-18', 1, NULL, 20, b'1', b'1', b'1', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `raza`
--

CREATE TABLE `raza` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tamanyo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `raza`
--

INSERT INTO `raza` (`id`, `nombre`, `tamanyo`) VALUES
(1, 'Bulterrier', 'mediano'),
(2, 'San Bernardo', 'Grande'),
(3, 'Golden Retriever', 'Grande');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipopaseo`
--

CREATE TABLE `tipopaseo` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `duracion` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipopaseo`
--

INSERT INTO `tipopaseo` (`id`, `nombre`, `duracion`) VALUES
(1, 'basico', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `nombre`) VALUES
(1, 'administrador'),
(2, 'usuario'),
(3, 'paseador'),
(4, 'voluntario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_tipousuario` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido1`, `apellido2`, `email`, `fechaNacimiento`, `login`, `password`, `id_tipousuario`) VALUES
(1, 'raimon', 'vilar', 'morera', 'raimonv@gmail.com', '2022-12-13', 'admin', '73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6', 1),
(3, 'Josefina', 'Velzquez', 'Garcia', 'rodrigarcia@gmail.com', '2022-12-12', 'josefina', '73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6', 1),
(6, 'Rodrigo', 'Velzquez', 'Garcia', 'rodrigarcia@gmail.com', '2022-12-12', 'rodrigo', '73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6', 1),
(7, 'Marisa Perez', 'Velzquez', 'Garcia', 'rodrigarcia@gmail.com', '2022-12-12', 'marisa', '73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6', 1),
(8, 'Antonia', 'Lagarto', 'Casanova', 'rodrigarcia@gmail.com', '2022-12-12', 'antonio', '73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `paseo`
--
ALTER TABLE `paseo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `perro`
--
ALTER TABLE `perro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `raza`
--
ALTER TABLE `raza`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipopaseo`
--
ALTER TABLE `tipopaseo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `paseo`
--
ALTER TABLE `paseo`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `perro`
--
ALTER TABLE `perro`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `raza`
--
ALTER TABLE `raza`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipopaseo`
--
ALTER TABLE `tipopaseo`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
