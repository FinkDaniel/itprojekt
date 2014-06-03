-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Jun 2014 um 11:09
-- Server Version: 5.6.16
-- PHP-Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `socialmediapinnwand`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `abo`
--

CREATE TABLE IF NOT EXISTS `abo` (
  `id` int(11) NOT NULL,
  `sourcePinnwand` int(11) NOT NULL,
  `targetPinnwand` int(11) NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sourcePinnwand` (`sourcePinnwand`),
  KEY `targetPinnwand` (`targetPinnwand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten fÃ¼r Tabelle `abo`
--

INSERT INTO `abo` (`id`, `sourcePinnwand`, `targetPinnwand`, `erstellungsdatum`) VALUES
(1, 9, 8, '2014-06-02 16:02:28');

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `beitrag`
--

CREATE TABLE IF NOT EXISTS `beitrag` (
  `id` int(11) NOT NULL,
  `sourceUser` int(11) NOT NULL,
  `beitrag` text NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sourceUser` (`sourceUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten fÃ¼r Tabelle `beitrag`
--

INSERT INTO `beitrag` (`id`, `sourceUser`, `beitrag`, `erstellungsdatum`) VALUES
(1, 1, 'Habe heute eine Pizza gegessen!', '2014-04-22 15:18:30'),
(2, 2, 'Oh man....Was soll ich heute nur anziehen? Ein Kleid oder doch eine Hose?', '2014-04-22 15:20:20'),
(3, 2, 'Was wird wohl aus dem VFB wenn der wirklich absteigt?', '2014-04-22 15:21:28'),
(4, 2, 'Der Wasen ist erÃ¶ffnet!', '2014-04-25 10:14:02');

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `gelikt`
--

CREATE TABLE IF NOT EXISTS `gelikt` (
  `id` int(11) NOT NULL,
  `sourceUser` int(11) NOT NULL,
  `targetBeitrag` int(11) NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sourceUser` (`sourceUser`),
  KEY `targetBeitrag` (`targetBeitrag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten fÃ¼r Tabelle `gelikt`
--

INSERT INTO `gelikt` (`id`, `sourceUser`, `targetBeitrag`, `erstellungsdatum`) VALUES
(1, 2, 1, '2014-04-24 17:21:00');

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `kommentar`
--

CREATE TABLE IF NOT EXISTS `kommentar` (
  `id` int(11) NOT NULL,
  `sourceUser` int(11) NOT NULL,
  `targetBeitrag` int(11) NOT NULL,
  `kommentar` text NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sourceUser` (`sourceUser`),
  KEY `targetBeitrag` (`targetBeitrag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten fÃ¼r Tabelle `kommentar`
--

INSERT INTO `kommentar` (`id`, `sourceUser`, `targetBeitrag`, `kommentar`, `erstellungsdatum`) VALUES
(1, 2, 1, 'Der VfB steigt nicht ab, dafÃ¼r ist er zu stark. Lieber NÃ¼rnber :)', '2014-04-24 10:54:08');

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `pinnwand`
--

CREATE TABLE IF NOT EXISTS `pinnwand` (
  `id` int(11) NOT NULL DEFAULT '0',
  `sourceUser` int(11) NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sourceUser` (`sourceUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten fÃ¼r Tabelle `pinnwand`
--

INSERT INTO `pinnwand` (`id`, `sourceUser`, `erstellungsdatum`) VALUES
(2, 6, '2014-06-02 13:29:49'),
(3, 7, '2014-06-02 13:34:40'),
(4, 8, '2014-06-02 13:36:15'),
(5, 9, '2014-06-02 14:31:43'),
(6, 10, '2014-06-02 16:14:48'),
(7, 11, '2014-06-02 16:20:06'),
(8, 12, '2014-06-02 19:53:10'),
(9, 13, '2014-06-02 19:54:31'),
(10, 13, '2014-06-02 20:05:40'),
(11, 14, '2014-06-02 21:20:43'),
(12, 15, '2014-06-02 22:03:54'),
(13, 16, '2014-06-03 08:17:37');

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL DEFAULT '0',
  `vorname` varchar(100) NOT NULL,
  `nachname` varchar(100) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten fÃ¼r Tabelle `users`
--

INSERT INTO `users` (`id`, `vorname`, `nachname`, `nickname`, `email`, `erstellungsdatum`) VALUES
(6, 'lotz', 'lotzington', 'lotz@example.com', 'lotz@example.com', '2014-06-02 13:29:49'),
(7, 'asadad', 'adadad', 'saub?123@example.com', 'saub?123@example.com', '2014-06-02 13:34:40'),
(8, 'der', 'fotzkopf', 'fotzkopf@example.com', 'fotzkopf@example.com', '2014-06-02 13:36:14'),
(9, 'test', 'testtt', 'test@example.com', 'test@example.com', '2014-06-02 14:31:43'),
(10, 'Frosch', 'gruen', 'froschkopf@prell.com', 'froschkopf@prell.com', '2014-06-02 16:14:48'),
(11, 'VfB', 'Forever', 'vfbforever@vfb.de', 'vfbforever@vfb.de', '2014-06-02 16:20:06'),
(12, 'Daniel', 'Lepiorz', 'DanielL', 'daniel.lepiorz@gmail.com', '2014-06-02 19:53:10'),
(13, 'daniel', 'Lepiorz', 'd.lepiorz', 'd.lepiorz@web.de', '2014-06-02 20:05:40'),
(14, 'Daniel', 'Llelel', 'ftftf', 'dertestaccount@googlemail.com', '2014-06-02 21:20:43'),
(15, 'hhhh', 'dfffff', 'lotz', 'lotz@mww.com', '2014-06-02 22:03:54'),
(16, 'Peter', 'Pan', 'PeterP', 'peterpan@captainhook.com', '2014-06-03 08:17:37');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
