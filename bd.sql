-- phpMyAdmin SQL Dump
-- version 3.4.3.2
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le : Mar 17 Avril 2018 à 03:38
-- Version du serveur: 5.5.15
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `assurance`
--

CREATE TABLE IF NOT EXISTS `assurance` (
  `IdAss` varchar(10) NOT NULL DEFAULT '',
  `matricule` varchar(15) DEFAULT NULL,
  `DateDebutAss` date DEFAULT NULL,
  `DateFinAss` date DEFAULT NULL,
  PRIMARY KEY (`IdAss`),
  KEY `matricule` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `assurance`
--

INSERT INTO `assurance` (`IdAss`, `matricule`, `DateDebutAss`, `DateFinAss`) VALUES
('417852', '120tunis9900', '9999-12-31', '9999-12-31'),
('45661223', '120tunis9000', '9999-12-31', '9999-12-31');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `cin` varchar(10) NOT NULL DEFAULT '',
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`cin`, `nom`, `prenom`, `adresse`, `tel`) VALUES
('09748735', 'lahbib', 'fedi', '17 rue 9 avril beni khalled nabeul', '5555555');

-- --------------------------------------------------------

--
-- Structure de la table `covoituragee`
--

CREATE TABLE IF NOT EXISTS `covoituragee` (
  `IdCovoiturage` varchar(10) NOT NULL DEFAULT '',
  `IdLocation` varchar(10) DEFAULT NULL,
  `DateDebutLocation` date DEFAULT NULL,
  `DateFinLocation` date DEFAULT NULL,
  `lieuDepart` varchar(100) DEFAULT NULL,
  `lieuDestination` varchar(100) DEFAULT NULL,
  `nbPlaceDispo` int(6) DEFAULT NULL,
  PRIMARY KEY (`IdCovoiturage`),
  KEY `IdLocation` (`IdLocation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `covoituragee`
--

INSERT INTO `covoituragee` (`IdCovoiturage`, `IdLocation`, `DateDebutLocation`, `DateFinLocation`, `lieuDepart`, `lieuDestination`, `nbPlaceDispo`) VALUES
('Cov-1', 'Loc-1', '2018-04-17', '2018-04-17', 'nabeul', 'monastir', 3);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `IdLocation` varchar(10) NOT NULL DEFAULT '',
  `cin` varchar(10) DEFAULT NULL,
  `matricule` varchar(15) DEFAULT NULL,
  `DateDebutLocation` date DEFAULT NULL,
  `DateFinLocation` date DEFAULT NULL,
  `Montant` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`IdLocation`),
  KEY `matricule` (`matricule`),
  KEY `cin` (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `location`
--

INSERT INTO `location` (`IdLocation`, `cin`, `matricule`, `DateDebutLocation`, `DateFinLocation`, `Montant`) VALUES
('Loc-1', '09748735', '120tunis9000', '2018-04-18', '2018-04-26', '800.00000');

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

CREATE TABLE IF NOT EXISTS `marque` (
  `IdMarque` varchar(10) NOT NULL DEFAULT '',
  `caracteristiques` varchar(245) DEFAULT NULL,
  `tarifJour` float(10,3) DEFAULT NULL,
  `nomModele` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdMarque`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `marque`
--

INSERT INTO `marque` (`IdMarque`, `caracteristiques`, `tarifJour`, `nomModele`) VALUES
('Mar-1', '2 porte', 100.000, 'BMW'),
('Mar-2', '4 PORTE', 150.000, 'BMW');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE IF NOT EXISTS `vehicule` (
  `matricule` varchar(15) NOT NULL DEFAULT '',
  `IdMarque` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`matricule`),
  KEY `IdMarque` (`IdMarque`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `vehicule`
--

INSERT INTO `vehicule` (`matricule`, `IdMarque`) VALUES
('120tunis9000', 'Mar-1'),
('120tunis9900', 'Mar-1');

-- --------------------------------------------------------

--
-- Structure de la table `visitetechnique`
--

CREATE TABLE IF NOT EXISTS `visitetechnique` (
  `IdVT` varchar(10) NOT NULL DEFAULT '',
  `matricule` varchar(15) DEFAULT NULL,
  `DateDebutVisite` date DEFAULT NULL,
  `DateFinVisite` date DEFAULT NULL,
  PRIMARY KEY (`IdVT`),
  KEY `matricule` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `visitetechnique`
--

INSERT INTO `visitetechnique` (`IdVT`, `matricule`, `DateDebutVisite`, `DateFinVisite`) VALUES
('415263', '120tunis9000', '9999-12-31', '9999-12-31'),
('741852', '120tunis9900', '9999-12-31', '9999-12-31');

-- --------------------------------------------------------

--
-- Structure de la table `voyageur`
--

CREATE TABLE IF NOT EXISTS `voyageur` (
  `IdCovoiturage` varchar(10) NOT NULL DEFAULT '',
  `NomEtPrenom` varchar(100) DEFAULT NULL,
  `tel` varchar(22) NOT NULL DEFAULT '',
  PRIMARY KEY (`IdCovoiturage`,`tel`),
  KEY `IdCovoiturage` (`IdCovoiturage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `voyageur`
--

INSERT INTO `voyageur` (`IdCovoiturage`, `NomEtPrenom`, `tel`) VALUES
('Cov-1', 'fedi lahbib', '555555');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `assurance`
--
ALTER TABLE `assurance`
  ADD CONSTRAINT `assurance_ibfk_1` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `covoituragee`
--
ALTER TABLE `covoituragee`
  ADD CONSTRAINT `covoituragee_ibfk_1` FOREIGN KEY (`IdLocation`) REFERENCES `location` (`IdLocation`) ON DELETE CASCADE;

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_5` FOREIGN KEY (`cin`) REFERENCES `client` (`cin`) ON DELETE CASCADE,
  ADD CONSTRAINT `location_ibfk_6` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE;

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`IdMarque`) REFERENCES `marque` (`IdMarque`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `visitetechnique`
--
ALTER TABLE `visitetechnique`
  ADD CONSTRAINT `visitetechnique_ibfk_1` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `voyageur`
--
ALTER TABLE `voyageur`
  ADD CONSTRAINT `voyageur_ibfk_1` FOREIGN KEY (`IdCovoiturage`) REFERENCES `covoituragee` (`IdCovoiturage`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
