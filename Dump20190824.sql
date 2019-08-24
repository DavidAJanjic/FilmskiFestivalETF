-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: filmfestival
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `festival`
--

DROP TABLE IF EXISTS `festival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `festival` (
  `idFestival` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `idMesto` int(11) DEFAULT NULL,
  `datumOd` date DEFAULT NULL,
  `datumDo` date DEFAULT NULL,
  `festivalOpis` varchar(500) DEFAULT NULL,
  `maxUlaznicaF` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFestival`),
  KEY `idMesto_idx` (`idMesto`),
  CONSTRAINT `idMesto` FOREIGN KEY (`idMesto`) REFERENCES `mesto` (`idMesto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festival`
--

LOCK TABLES `festival` WRITE;
/*!40000 ALTER TABLE `festival` DISABLE KEYS */;
INSERT INTO `festival` VALUES (1,'Exit',3,'2019-05-05','2019-12-07','blablalba',50000),(2,'Jazz',4,'2019-03-03','2019-08-09','blablalba',20000),(3,'Beer Fest',1,'2019-08-15','2019-08-20','blablalba',50000),(4,'Osam',5,'2019-09-01','2019-09-07','blablalba',10000),(5,'Rock Village',2,'2019-08-10','2019-08-10','blablalba',5000),(6,'Kan',1,'2019-09-10','2019-10-21','blablalba',15000);
/*!40000 ALTER TABLE `festival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `film` (
  `idFilm` int(11) NOT NULL AUTO_INCREMENT,
  `originalniNaziv` varchar(45) DEFAULT NULL,
  `nazivNaSrpskom` varchar(45) DEFAULT NULL,
  `godinaIzdanja` int(4) DEFAULT NULL,
  `filmOpis` varchar(500) DEFAULT NULL,
  `idReziser` int(11) DEFAULT NULL,
  `idZemljePorekla` int(11) DEFAULT NULL,
  `trajanjeFilma` int(11) DEFAULT NULL,
  `imdbLink` varchar(500) DEFAULT NULL,
  `poster` varchar(500) DEFAULT NULL,
  `ocenaSUM` int(11) DEFAULT NULL,
  `ocenaCOUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFilm`),
  KEY `idReziser_idx` (`idReziser`),
  KEY `idZemljePorekla_idx` (`idZemljePorekla`),
  CONSTRAINT `idReziser` FOREIGN KEY (`idReziser`) REFERENCES `reziseri` (`idReziser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idZemljePorekla` FOREIGN KEY (`idZemljePorekla`) REFERENCES `zemlje_porekla` (`idZemljePorekla`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Lord of the Rings','Gospodar Prstenova',2000,'trailer',2,1,250,'https://www.imdb.com/title/tt0120737/','lotrPoster',NULL,NULL),(2,'Star Wars','Ratovi Zvezda',1977,'Sci-Fi',1,2,130,'https://www.imdb.com/title/tt0076759/?ref_=nv_sr_1?ref_=nv_sr_1','stwPoster',NULL,NULL),(3,'Shrek','Srek',2006,'Comedy',3,3,110,'https://www.imdb.com/title/tt0126029/?ref_=nv_sr_1?ref_=nv_sr_1','shrekPoster',NULL,NULL);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glumci`
--

DROP TABLE IF EXISTS `glumci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `glumci` (
  `idGlumac` int(11) NOT NULL AUTO_INCREMENT,
  `imeGlumci` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGlumac`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glumci`
--

LOCK TABLES `glumci` WRITE;
/*!40000 ALTER TABLE `glumci` DISABLE KEYS */;
INSERT INTO `glumci` VALUES (1,'Harrison Ford'),(2,'Marc Hamil'),(3,'Viggo Mortensen'),(4,'Chris Rock');
/*!40000 ALTER TABLE `glumci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnik` (
  `idKorisnik` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `datumRodjenja` date DEFAULT NULL,
  `kontaktMob` varchar(45) DEFAULT 'Nije upisan br',
  `email` varchar(45) DEFAULT NULL,
  `idTipKorisnika` int(11) DEFAULT '1',
  `brojPrekrsaja` int(11) DEFAULT '0',
  PRIMARY KEY (`idKorisnik`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `idTipKorisnikaKorisnik_idx` (`idTipKorisnika`),
  CONSTRAINT `idTipKorisnikaKorisnik` FOREIGN KEY (`idTipKorisnika`) REFERENCES `tip_korisnika` (`idTipKorisnika`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (14,'username','password','ime','prezime','1997-11-23','kontaktMob','email',5,0),(19,'nrte','nerfgd','ntrfdb','ndefb','1997-11-23','65693874','nekaadresatamo@gmail.com',3,0),(20,'eqagbws','sedgbewg','egedsg','esdwbwse','1997-11-23','65693874','nekaadresatamo@gmail.com',2,0),(22,'zdsndeb\\s','egwsegw','wewg','ewg','1997-11-23','65693874','nekaadresatamo@gmail.com',1,0),(23,'awfqwagfqwgqw','wfqwfq','qwfqw','fqwfqw','1997-11-23','0695566888','nekaadresatamo@gmail.com',1,0),(24,'agqagqehbwqg','qgqwgq','gqwgqwg','qgwqwgq','1997-11-23','kontaktbroj','nekaadresatamo@gmail.com',3,0),(25,'ProbaIme','ProvaPass','ImeProba','prezimeproba','1997-11-23','0658877444','nekaadresatamo@gmail.com',1,0),(26,'sdbdbsadba','baabsa','ababa','aba','1997-11-23','nekibroj','nekaadresatamo@gmail.com',1,0),(27,'a','a','afsafasf','asfasf','1997-11-23','0695566888','nekaadresatamo@gmail.com',1,0),(28,'v','vv','qwgqwgq','wgqwqgq','2019-08-13','0695566888','nekaadresatamo@gmail.com',2,0),(29,'p','p2','Petar','Petrovic','2007-01-01','0685544123','peraprea@gmail.com',3,0),(30,'admin','admin','imeprobaNesa','prezimeprobaNesa','2003-02-05','0655898988','adresaadresa@hotmail.com',4,0),(31,'n','n','Nenad','Mitrovic','1989-04-04','+3815566987','mojaAdresa@nekaadresa.com',5,0),(36,'pera111','nn123','nn','nn','2019-08-04','0655588999','nn@gmail.com',1,0),(37,'pera12345','123456','nn','nn','2019-08-04','0658899774','123124@gmail.com',1,0);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupovina`
--

DROP TABLE IF EXISTS `kupovina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `kupovina` (
  `idKupovina` int(11) NOT NULL AUTO_INCREMENT,
  `idRezervacija` int(11) DEFAULT NULL,
  `komentar` varchar(200) DEFAULT NULL,
  `ocena` int(11) DEFAULT NULL,
  PRIMARY KEY (`idKupovina`),
  KEY `idRKupovina_idx` (`idRezervacija`),
  CONSTRAINT `idRKupovina` FOREIGN KEY (`idRezervacija`) REFERENCES `rezervacija` (`idRezervacija`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupovina`
--

LOCK TABLES `kupovina` WRITE;
/*!40000 ALTER TABLE `kupovina` DISABLE KEYS */;
/*!40000 ALTER TABLE `kupovina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lokacija`
--

DROP TABLE IF EXISTS `lokacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lokacija` (
  `idLokacija` int(11) NOT NULL AUTO_INCREMENT,
  `idMesto` int(11) DEFAULT NULL,
  `imeLokacija` varchar(45) DEFAULT NULL,
  `nazivSale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLokacija`),
  KEY `idMestoLokacija_idx` (`idMesto`),
  CONSTRAINT `idMestoLokacija` FOREIGN KEY (`idMesto`) REFERENCES `mesto` (`idMesto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lokacija`
--

LOCK TABLES `lokacija` WRITE;
/*!40000 ALTER TABLE `lokacija` DISABLE KEYS */;
INSERT INTO `lokacija` VALUES (1,1,'Kombak arena','Velika sala'),(2,1,'Kombank arena','Mala sala'),(3,1,'Kombank arena','Konferencijska sala'),(4,2,'Dom kulutre Cacak','Sala 1'),(5,2,'Dom kulture Cacak','Sala 2'),(6,3,'Pionir','Sala ulaz'),(7,3,'Pionir','Sala izlaz');
/*!40000 ALTER TABLE `lokacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesto`
--

DROP TABLE IF EXISTS `mesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mesto` (
  `idMesto` int(11) NOT NULL AUTO_INCREMENT,
  `imeMesto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesto`
--

LOCK TABLES `mesto` WRITE;
/*!40000 ALTER TABLE `mesto` DISABLE KEYS */;
INSERT INTO `mesto` VALUES (1,'Beograd'),(2,'Cacak'),(3,'Novi Sad'),(4,'Nis'),(5,'Leskovac');
/*!40000 ALTER TABLE `mesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projekcija`
--

DROP TABLE IF EXISTS `projekcija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `projekcija` (
  `idProjekcija` int(11) NOT NULL AUTO_INCREMENT,
  `idFestival` int(11) DEFAULT NULL,
  `idFilm` int(11) DEFAULT NULL,
  `cena` int(11) DEFAULT NULL,
  `idLokacija` int(11) DEFAULT NULL,
  `datumProjekcije` date DEFAULT NULL,
  `vremeProjekcije` time DEFAULT NULL,
  `maxUlaznicaP` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProjekcija`),
  KEY `idFilm_idx` (`idFilm`),
  KEY `idFestivalProjekcija_idx` (`idFestival`),
  KEY `idLokacijaProjekcija_idx` (`idLokacija`),
  CONSTRAINT `idFestivalProjekcija` FOREIGN KEY (`idFestival`) REFERENCES `festival` (`idFestival`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idFilmProjekcija` FOREIGN KEY (`idFilm`) REFERENCES `film` (`idFilm`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idLokacijaProjekcija` FOREIGN KEY (`idLokacija`) REFERENCES `lokacija` (`idLokacija`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projekcija`
--

LOCK TABLES `projekcija` WRITE;
/*!40000 ALTER TABLE `projekcija` DISABLE KEYS */;
INSERT INTO `projekcija` VALUES (3,1,2,250,1,'2019-09-09','20:05:00',100),(4,2,1,320,2,'2019-08-09','19:31:00',2),(5,3,3,100,3,'2019-08-14','14:20:00',40),(6,1,3,300,2,'2019-09-08','18:30:00',200);
/*!40000 ALTER TABLE `projekcija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rezervacija` (
  `idRezervacija` int(11) NOT NULL AUTO_INCREMENT,
  `idProjekcija` int(11) DEFAULT NULL,
  `jedinstveniKod` varchar(10) DEFAULT NULL,
  `idStatusRezervacije` int(11) DEFAULT '1',
  `datumRezervacije` date DEFAULT NULL,
  `datumIsteka` date DEFAULT NULL,
  `brojUlaznica` int(11) DEFAULT NULL,
  `idKorisnika` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRezervacija`),
  KEY `idP_idx` (`idProjekcija`),
  KEY `idStatusRezervacijeRezervacija_idx` (`idStatusRezervacije`),
  KEY `idKorisnikaRezervacija_idx` (`idKorisnika`),
  CONSTRAINT `idKorisnikaRezervacija` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnik` (`idKorisnik`),
  CONSTRAINT `idProjekcijaRezervacija` FOREIGN KEY (`idProjekcija`) REFERENCES `projekcija` (`idProjekcija`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idStatusRezervacijeRezervacija` FOREIGN KEY (`idStatusRezervacije`) REFERENCES `status_rezervacija` (`idStatusaRezervacije`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (1,3,'NEMA',1,'2019-09-05','2019-10-05',12,20),(2,3,'NEMA',2,'2019-10-05','2019-10-05',13,20),(3,3,'NEMA',2,'2019-10-06','2019-10-08',14,27),(4,3,'NEMA',1,'2019-10-07','2019-10-09',15,28);
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reziseri`
--

DROP TABLE IF EXISTS `reziseri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reziseri` (
  `idReziser` int(11) NOT NULL AUTO_INCREMENT,
  `imeReziseri` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idReziser`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reziseri`
--

LOCK TABLES `reziseri` WRITE;
/*!40000 ALTER TABLE `reziseri` DISABLE KEYS */;
INSERT INTO `reziseri` VALUES (1,'Tarantino'),(2,'Piter Jackosn'),(3,'James Gunn');
/*!40000 ALTER TABLE `reziseri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slike_festivala`
--

DROP TABLE IF EXISTS `slike_festivala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `slike_festivala` (
  `idSlikeFestivala` int(11) NOT NULL AUTO_INCREMENT,
  `idFestivala` int(11) DEFAULT NULL,
  `slika` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idSlikeFestivala`),
  KEY `idFestivalSlikeFestivala_idx` (`idFestivala`),
  CONSTRAINT `idFestivalSlikeFestivala` FOREIGN KEY (`idFestivala`) REFERENCES `festival` (`idFestival`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slike_festivala`
--

LOCK TABLES `slike_festivala` WRITE;
/*!40000 ALTER TABLE `slike_festivala` DISABLE KEYS */;
/*!40000 ALTER TABLE `slike_festivala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slike_filmova`
--

DROP TABLE IF EXISTS `slike_filmova`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `slike_filmova` (
  `idS` int(11) NOT NULL AUTO_INCREMENT,
  `idFilm` int(11) DEFAULT NULL,
  `nazivSlike` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idS`),
  KEY `idFilmSlikeFilmova_idx` (`idFilm`),
  CONSTRAINT `idFilmSlikeFilmova` FOREIGN KEY (`idFilm`) REFERENCES `film` (`idFilm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slike_filmova`
--

LOCK TABLES `slike_filmova` WRITE;
/*!40000 ALTER TABLE `slike_filmova` DISABLE KEYS */;
/*!40000 ALTER TABLE `slike_filmova` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_rezervacija`
--

DROP TABLE IF EXISTS `status_rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status_rezervacija` (
  `idStatusaRezervacije` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStatusaRezervacije`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_rezervacija`
--

LOCK TABLES `status_rezervacija` WRITE;
/*!40000 ALTER TABLE `status_rezervacija` DISABLE KEYS */;
INSERT INTO `status_rezervacija` VALUES (1,'NEMA'),(2,'Odobrena'),(3,'Odbijena');
/*!40000 ALTER TABLE `status_rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_korisnika`
--

DROP TABLE IF EXISTS `tip_korisnika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tip_korisnika` (
  `idTipKorisnika` int(11) NOT NULL AUTO_INCREMENT,
  `nazivTipa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipKorisnika`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_korisnika`
--

LOCK TABLES `tip_korisnika` WRITE;
/*!40000 ALTER TABLE `tip_korisnika` DISABLE KEYS */;
INSERT INTO `tip_korisnika` VALUES (1,'nov zahtev za registraciju'),(2,'registrovan korisnik'),(3,'prodavac'),(4,'admin'),(5,'banovan');
/*!40000 ALTER TABLE `tip_korisnika` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uloge`
--

DROP TABLE IF EXISTS `uloge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uloge` (
  `idFilm` int(11) NOT NULL,
  `idGlumac` int(11) NOT NULL,
  PRIMARY KEY (`idFilm`,`idGlumac`),
  KEY `idFilmUloge_idx` (`idFilm`),
  KEY `idGlumacUloge_idx` (`idGlumac`),
  CONSTRAINT `idFilmUloge` FOREIGN KEY (`idFilm`) REFERENCES `film` (`idFilm`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idGlumacUloge` FOREIGN KEY (`idGlumac`) REFERENCES `glumci` (`idGlumac`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uloge`
--

LOCK TABLES `uloge` WRITE;
/*!40000 ALTER TABLE `uloge` DISABLE KEYS */;
INSERT INTO `uloge` VALUES (1,2),(2,3),(3,1);
/*!40000 ALTER TABLE `uloge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zemlje_porekla`
--

DROP TABLE IF EXISTS `zemlje_porekla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `zemlje_porekla` (
  `idZemljePorekla` int(11) NOT NULL AUTO_INCREMENT,
  `imeZemljaPorekla` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idZemljePorekla`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zemlje_porekla`
--

LOCK TABLES `zemlje_porekla` WRITE;
/*!40000 ALTER TABLE `zemlje_porekla` DISABLE KEYS */;
INSERT INTO `zemlje_porekla` VALUES (1,'Amerika'),(2,'Francuska'),(3,'Danska'),(4,'Italija');
/*!40000 ALTER TABLE `zemlje_porekla` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-24 12:33:01
