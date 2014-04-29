-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 29. Apr 2014 um 12:25
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
-- Tabellenstruktur für Tabelle `abo`
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

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `beitrag`
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
-- Daten für Tabelle `beitrag`
--

INSERT INTO `beitrag` (`id`, `sourceUser`, `beitrag`, `erstellungsdatum`) VALUES
(1, 1, 'Habe heute eine Pizza gegessen!', '2014-04-22 15:18:30'),
(2, 2, 'Oh man....Was soll ich heute nur anziehen? Ein Kleid oder doch eine Hose?', '2014-04-22 15:20:20'),
(3, 2, 'Was wird wohl aus dem VFB wenn der wirklich absteigt?', '2014-04-22 15:21:28'),
(4, 2, 'Der Wasen ist eröffnet!', '2014-04-25 10:14:02');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gelikt`
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
-- Daten für Tabelle `gelikt`
--

INSERT INTO `gelikt` (`id`, `sourceUser`, `targetBeitrag`, `erstellungsdatum`) VALUES
(1, 2, 1, '2014-04-24 17:21:00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kommentar`
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
-- Daten für Tabelle `kommentar`
--

INSERT INTO `kommentar` (`id`, `sourceUser`, `targetBeitrag`, `kommentar`, `erstellungsdatum`) VALUES
(1, 2, 1, 'Der VfB steigt nicht ab, dafür ist er zu stark. Lieber Nürnber :)', '2014-04-24 10:54:08');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pinnwand`
--

CREATE TABLE IF NOT EXISTS `pinnwand` (
  `id` int(11) NOT NULL DEFAULT '0',
  `sourceUser` int(11) NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sourceUser` (`sourceUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `pinnwand`
--

INSERT INTO `pinnwand` (`id`, `sourceUser`, `erstellungsdatum`) VALUES
(1, 5, '2014-04-28 22:13:52'),
(2, 6, '2014-04-28 22:15:09'),
(3, 7, '2014-04-29 09:53:04'),
(4, 8, '2014-04-29 10:16:24');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL DEFAULT '0',
  `vorname` varchar(100) NOT NULL,
  `nachname` varchar(100) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `erstellungsdatum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`id`, `vorname`, `nachname`, `nickname`, `erstellungsdatum`) VALUES
(1, 'Daniel', 'Lepiorz', 'Lepiorzddssddhh', '2014-04-28 20:32:47'),
(2, 'Test', 'Tester', 'adads', '2014-04-28 21:51:19'),
(3, 'Test', 'Tester', 'adadsxcx', '2014-04-28 21:52:37'),
(4, 'Test', 'Tester', 'adadsxgfhgfcx', '2014-04-28 22:03:24'),
(5, 'Daniel', 'Fink', 'Der Lotz', '2014-04-28 22:13:52'),
(6, 'Patrick', 'Preller', 'Der Preller', '2014-04-28 22:15:09'),
(7, 'Patrick', 'Preller', 'Mongo', '2014-04-29 09:53:04'),
(8, 'Isabell', 'Feininger', 'Isabelle', '2014-04-29 10:16:24');

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `abo`
--
ALTER TABLE `abo`
  ADD CONSTRAINT `abo_ibfk_1` FOREIGN KEY (`sourcePinnwand`) REFERENCES `pinnwand` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `abo_ibfk_2` FOREIGN KEY (`targetPinnwand`) REFERENCES `pinnwand` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `pinnwand`
--
ALTER TABLE `pinnwand`
  ADD CONSTRAINT `pinnwand_ibfk_1` FOREIGN KEY (`sourceUser`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
