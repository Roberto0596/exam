-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-04-2023 a las 02:25:23
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `test`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnquestionanswer`
--

CREATE TABLE `alumnquestionanswer` (
  `id` bigint(20) NOT NULL,
  `answer` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `test_assignation_id` bigint(20) NOT NULL,
  `test_question_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `age` varchar(255) NOT NULL,
  `created_at` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `timezone_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `test`
--

CREATE TABLE `test` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `testassignation`
--

CREATE TABLE `testassignation` (
  `id` bigint(20) NOT NULL,
  `application_date` datetime DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `general_qualification` int(11) DEFAULT 0,
  `sended` int(11) DEFAULT 0,
  `student_id` bigint(20) NOT NULL,
  `test_id` bigint(20) NOT NULL,
  `timezone_id` bigint(20) DEFAULT NULL,
  `alumn_aplication_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `testquestion`
--

CREATE TABLE `testquestion` (
  `id` bigint(20) NOT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `answer4` varchar(255) DEFAULT NULL,
  `correctOption` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `test_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zonetime`
--

CREATE TABLE `zonetime` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnquestionanswer`
--
ALTER TABLE `alumnquestionanswer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo5xk3rwb9b37l6rd0t7s0cipo` (`test_assignation_id`),
  ADD KEY `FKyqavj3bbjgb3c5oaf8t9wkt6` (`test_question_id`);

--
-- Indices de la tabla `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh4b7oi857uxt2tg9279qhdlvh` (`timezone_id`);

--
-- Indices de la tabla `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `testassignation`
--
ALTER TABLE `testassignation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcwwqrv46ssajhjwk49i3n1a3n` (`student_id`),
  ADD KEY `FK1xhtcklxradiqfqiewn9q8xr8` (`test_id`),
  ADD KEY `FK4jphlj25b6yulbchioc1xasig` (`timezone_id`);

--
-- Indices de la tabla `testquestion`
--
ALTER TABLE `testquestion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrt39bdhh0uc2k9tpv8l8sshpj` (`test_id`);

--
-- Indices de la tabla `zonetime`
--
ALTER TABLE `zonetime`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnquestionanswer`
--
ALTER TABLE `alumnquestionanswer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `test`
--
ALTER TABLE `test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `testassignation`
--
ALTER TABLE `testassignation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `testquestion`
--
ALTER TABLE `testquestion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `zonetime`
--
ALTER TABLE `zonetime`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
