CREATE DATABASE  IF NOT EXISTS `filmfestival` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `filmfestival`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: filmfestival
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festival`
--

LOCK TABLES `festival` WRITE;
/*!40000 ALTER TABLE `festival` DISABLE KEYS */;
INSERT INTO `festival` VALUES (7,'47. Medjunarodni festival FEST',6,'2019-08-05','2019-10-05','Ljubitelji sedme umetnosit imace priliku da nadoknade propusteno sa ovogodisnjeg 47.festivala.',1900),(8,'Dunav festival',7,'2019-08-11','2019-09-11','Ljubitelji filma bice u mogucnosti da pogledaju tri nagradjena filma na evropskim festivalima',3000),(9,'SOFEST',8,'2019-08-20','2019-09-20','Sloboarske filmske svecanosti bile su festival filmskog stvaralastva koji se po karaktreru i ciljevima razlikovao od vecine tada postojecih',2500),(10,'Magnificent Seven',6,'2019-07-08','2019-10-20','I have often been accused of being too enthusiastic, when talking or writing about documentary films, but when producer Atanas Georgiev approached me with ”Honeyland” saying ”Tue, I think we have made a masterpiece”, I was the one who was sceptical.',1200),(11,'European Film Festival Palic',9,'2019-06-01','2019-11-01','The International Film Festival Palic was founded in 1992 by the Municipality of Subotica. From the earliest small and unpretentious film screenings in Palic, through the persistent attempts to create a serious regional film festival in the hard times of isolation, and the first attempts at getting closer to the cinematography of the former Yugoslav republics then being at war, it has grown into an important spot on the map of the European film festivals',2100);
/*!40000 ALTER TABLE `festival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `idFilm` int(11) NOT NULL AUTO_INCREMENT,
  `originalniNaziv` varchar(45) DEFAULT NULL,
  `nazivNaSrpskom` varchar(45) DEFAULT NULL,
  `godinaIzdanja` int(4) DEFAULT NULL,
  `filmOpis` varchar(1000) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (8,'The Favourite','Miljenica',2018,'Radnja filma prati kraljicu Anne koja je valdala pocetekom 18. veka.',4,5,119,'https://www.imdb.com/title/tt5083738/','https://m.media-amazon.com/images/M/MV5BMTg1NzQwMDQxNV5BMl5BanBnXkFtZTgwNDg2NDYyNjM@._V1_SY1000_CR0,0,670,1000_AL_.jpg',8,1),(9,'All alone','Sam samcat',2018,'Mark je razvedeni otac, gotovo nikada nije sam. Okruzen je porodicom, prijateljima, drustvom iz kraja, ali najmanje kontakta ima s osobom koju najvise voli.',5,6,87,'https://www.imdb.com/title/tt8761382/','https://m.media-amazon.com/images/M/MV5BZjI5ZDEzYjMtZDIxMC00MmY0LTg0YTctOTU2NjViM2Q4NzM5XkEyXkFqcGdeQXVyOTEwNDEzNzM@._V1_SY1000_SX750_AL_.jpg',7,1),(10,'Birds of Passage','Ptice selice',2018,'U pustinji La Guahira u Severnoj Kolumbiji tumacenje znakova i snova, moci predkzaznja, talismani i vrlo jake tradicije koje odrzavaju kulturu Vaju naroda.',6,7,125,'https://www.imdb.com/title/tt6386748/?ref_=ttrel_rel_tt','https://m.media-amazon.com/images/M/MV5BMjUxODM5ODUyM15BMl5BanBnXkFtZTgwNzA3Nzg3NjM@._V1_SY1000_CR0,0,674,1000_AL_.jpg',7,1),(11,'NECH JE SVETLO','Neka bude svetlo',2019,'Cetrdesetogodisnji Milan, otac troje dece, radi u Nemackoj kako bi obezbedio bolji zivot svojoj deci',7,8,93,'https://www.imdb.com/title/tt7689186/','https://m.media-amazon.com/images/M/MV5BMjJiZGJmNzktMTA0OC00MjAxLWI5YzktY2ZmODkwZGQ5MTM0XkEyXkFqcGdeQXVyNzcwMzQwODI@._V1_SY1000_CR0,0,706,1000_AL_.jpg',7,1),(12,'Little Joe','Mali Dzo',2019,'Zivot tinejdzera Dzoa sa svojom samohranom majkom',8,11,100,'https://www.imdb.com/title/tt9204204/?ref_=fn_al_tt_1','https://m.media-amazon.com/images/M/MV5BOTg5NjAyNWYtMzQwZi00ZTczLWEzMzQtODBkMmE2MzBiMzc3XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg',6,1),(13,'System Crasher','Izvan sistema',2019,'Prica o devetogodisnjoj devojcici koja divlja i ne postuje pravila sistema',9,9,122,'https://www.imdb.com/title/tt8535968/','https://m.media-amazon.com/images/M/MV5BNDVmYWM4ZDItODliOC00NDVkLThmYmItNTU4N2NlYTY4NTIyXkEyXkFqcGdeQXVyMjIxMDAyNzY@._V1_SY1000_CR0,0,707,1000_AL_.jpg',8,1),(14,'Juzni vetar','Juzni vetar',2018,'Mladi clan Beogradskog podzemlja dovesce u  opasnos svoju porodicu i sebe zamerivsi se prkogranicnom nakro dileru koji saradjuje sa lokalnim sefom policije',10,12,98,'https://www.imdb.com/title/tt5207158/','https://m.media-amazon.com/images/M/MV5BNzc4NjNhMmYtZGQ2YS00MTIwLTk4NjktZGNmNDZiNTExODhjXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SY1000_CR0,0,562,1000_AL_.jpg',8,1),(15,'Kralj Petar 1','Kralj Petar 1',2018,'Mladi Kralj Petar Prvi se posle dugo godina izgnanstva vraca u svoju rodnu zemlju da liberalizuje svoj narod  i obezbedi demokratiju i rekonstrukciju zemlje.',11,12,119,'https://www.imdb.com/title/tt8161166/?ref_=nv_sr_1?ref_=nv_sr_1','https://m.media-amazon.com/images/M/MV5BNWU1MmJiZWUtMWIzZi00NTgxLTllMzAtMTFiNDMxYjg4ZmZlXkEyXkFqcGdeQXVyNjI4ODg5MzU@._V1_SY1000_CR0,0,699,1000_AL_.jpg',9,1),(16,'Blakanska medja','Blakanska medja',2019,'Rusko-Srpska ratna drama posvecena prezuimanju aerodroma Slatina na Kosovu tokom NATO bombardovanja',12,12,131,'https://www.imdb.com/title/tt5951188/','https://m.media-amazon.com/images/M/MV5BZTViMGFjZGMtN2M4Yy00ZDYwLWIzNDQtYTFhOTg2YjA2MmQyXkEyXkFqcGdeQXVyNTE1MDE2MzY@._V1_.jpg',7,1),(17,'The Magnificent Seven','Sedam velicanstvenih',1960,'Sedam uvbica je angazovano od strane Meskickih seljaka da oslobode njigovo selo od bandita',13,5,166,'https://www.imdb.com/title/tt0054047/','https://m.media-amazon.com/images/M/MV5BMzYyNzU0MTM1OF5BMl5BanBnXkFtZTcwMzE1ODE1NA@@._V1_SY1000_CR0,0,666,1000_AL_.jpg',8,1),(18,'I Had a Dream','Ja sam imao san',2019,'The short-feature documentary, I Had a Dream, is an ironic portrait of the life of self-described \"Dream Expert\" and so-called \"talented person\" Michael Lennox.',14,13,98,'https://www.imdb.com/title/tt8040236/?ref_=nv_sr_1?ref_=nv_sr_1','https://m.media-amazon.com/images/M/MV5BNGY4MDUyMTAtYTE2OC00MWY5LWIxZjAtNDYwYzQ0NWExODJlXkEyXkFqcGdeQXVyMDQ3MzY5NA@@._V1_SY1000_SX675_AL_.jpg',6,1),(19,'Avengers: Endgame','Osvernici: kraj igre',2019,'After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.',15,13,182,'https://www.imdb.com/title/tt4154796/','https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SY1000_CR0,0,674,1000_AL_.jpg',9,1),(20,'Poison rose','Otrovna ruza',2019,'Inspired by classic film noir, Carson Phillips, an ex-football star turned PI, has a soft spot for a lady in distress.',16,13,98,'https://www.imdb.com/title/tt5862166/?ref_=nv_sr_1?ref_=nv_sr_1','https://m.media-amazon.com/images/M/MV5BMTU2NDkwNDQ5N15BMl5BanBnXkFtZTgwOTM2NTcwODM@._V1_.jpg',8,1),(21,'Fast & Furious Present: Hobbs & Shaw','Paklene ulice: Hobs i So',2019,'Lawman Luke Hobbs and outcast Deckard Shaw form an unlikely alliance when a cyber-genetically enhanced villain threatens the future of humanity.',17,13,138,'https://www.imdb.com/title/tt6806448/?ref_=nv_sr_1?ref_=nv_sr_1','https://m.media-amazon.com/images/M/MV5BOTIzYmUyMmEtMWQzNC00YzExLTk3MzYtZTUzYjMyMmRiYzIwXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_SY1000_CR0,0,685,1000_AL_.jpg',9,1);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glumci`
--

DROP TABLE IF EXISTS `glumci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `glumci` (
  `idGlumac` int(11) NOT NULL AUTO_INCREMENT,
  `imeGlumci` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGlumac`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glumci`
--

LOCK TABLES `glumci` WRITE;
/*!40000 ALTER TABLE `glumci` DISABLE KEYS */;
INSERT INTO `glumci` VALUES (5,'Carmina Martinez'),(6,'Jose Acosta'),(7,'Natalia Reyes'),(8,'Miki Manojlovic'),(9,'Snjezana Sinovic'),(10,'Jagoda Kralj'),(11,'Olivia Colman'),(12,'Ema Stone'),(13,'Rachel Weisz'),(14,'Andreas Nickl'),(15,'Ingrid Timkova'),(16,'Ljubomir Paulovic'),(17,'Emily Beeckam'),(18,'Ben Whishaw'),(19,'Kerry Fox'),(20,'Helena Zengel '),(21,'Albrecht Schuch'),(22,'Gabriela Mara Schmeide'),(23,'Milos Bikovic'),(24,'Miodrag Radonjic'),(25,'Dragan Bjelogrlic'),(26,'Lazar Ristovski'),(27,'Radovan Vujovic'),(28,'Milan Kolak'),(29,'Anton Pampushnyy'),(30,'Milena Radulovic'),(31,'Yuriy Kutsenko'),(32,'Yul Brynner'),(33,'Steve McQueen'),(34,'Charles Bronson'),(35,'Cathrine Ross Allee'),(36,'John Allee'),(37,'Michael Lennox'),(38,'Robert Downey Jr.'),(39,'Chris Evans'),(40,'Mark Ruffalo'),(41,'John Travolta'),(42,'Brendan Fraser'),(43,'Morgan Freeman'),(44,'Dwayne Johnson'),(45,'Jason Statham'),(46,'Idris Elba');
/*!40000 ALTER TABLE `glumci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (44,'n','OAmupUtpzwCqeQ4PEXPo6w==','Nesa','Mit','1993-01-01','0655544999','nekitamo@nesto.com',2,0),(45,'v','dbLVf6Zu+8jQyY4TS72tng==','Vv','VVV','1993-08-01','0658899558','nekatamo@nestotamo.com',4,0),(46,'Ivana','jMSxgd2ODYi7BHqW9CYkBQ==','Ivanka','Ari','1970-11-18','062555222','nekatamovanka@nestotamo.com',5,0);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupovina`
--

DROP TABLE IF EXISTS `kupovina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lokacija` (
  `idLokacija` int(11) NOT NULL AUTO_INCREMENT,
  `idMesto` int(11) DEFAULT NULL,
  `imeLokacija` varchar(45) DEFAULT NULL,
  `nazivSale` varchar(45) DEFAULT NULL,
  `imeLokacijaNazivSale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLokacija`),
  KEY `idMestoLokacija_idx` (`idMesto`),
  CONSTRAINT `idMestoLokacija` FOREIGN KEY (`idMesto`) REFERENCES `mesto` (`idMesto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lokacija`
--

LOCK TABLES `lokacija` WRITE;
/*!40000 ALTER TABLE `lokacija` DISABLE KEYS */;
INSERT INTO `lokacija` VALUES (11,7,'Tvrdjava','Na otvorenom','Tvrdjava Na otvorenom'),(12,6,'Kombank Arena','Mala sala','Kombank Arena Mala sala'),(13,8,'Centar za kulturu','Glavna sala','Centar za kulturu Glavna sala'),(14,8,'Centar za kulturu','Pozorisna dvorana','Centar za kulturu Pozorisna dvorana'),(15,8,'Dom omladine','Sala 1','Dom omladine Sala 1'),(16,6,'Kombank Dvorana','Sala 1','Kombank Dvorana Sala 1 '),(17,6,'Kombank Dvoarna','Sala 3','Kombank Dvorana Sala 13'),(18,6,'Kombank Dvoarna','Sala 5','Kombank Dvorana Sala 15'),(19,9,'Dom kulutre','Glavna sala','Dom kulutre Glavna sala'),(20,9,'Bioskop','Bioskopska sala 1','Bioskop Bioskopska sala 1'),(21,9,'Bioskop','Bioskopska sala 2','Bioskop Bioskopska sala 2');
/*!40000 ALTER TABLE `lokacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesto`
--

DROP TABLE IF EXISTS `mesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesto` (
  `idMesto` int(11) NOT NULL AUTO_INCREMENT,
  `imeMesto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesto`
--

LOCK TABLES `mesto` WRITE;
/*!40000 ALTER TABLE `mesto` DISABLE KEYS */;
INSERT INTO `mesto` VALUES (6,'Beograd'),(7,'Smederevo'),(8,'Sopot'),(9,'Subotica'),(10,'');
/*!40000 ALTER TABLE `mesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projekcija`
--

DROP TABLE IF EXISTS `projekcija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projekcija`
--

LOCK TABLES `projekcija` WRITE;
/*!40000 ALTER TABLE `projekcija` DISABLE KEYS */;
INSERT INTO `projekcija` VALUES (10,7,8,700,12,'2019-08-22','20:00:00',400),(11,7,9,700,12,'2019-08-23','21:00:00',500),(12,7,10,700,12,'2019-08-24','18:00:00',1000),(16,8,11,650,11,'2019-09-01','20:00:00',1000),(17,8,12,800,11,'2019-09-02','21:00:00',1000),(18,8,13,300,11,'2019-09-03','22:00:00',1000),(19,9,14,800,13,'2019-08-29','22:00:00',800),(20,9,15,650,14,'2019-09-03','19:00:00',800),(21,9,16,350,15,'2019-09-08','13:00:00',900),(22,10,17,250,16,'2019-10-09','13:00:00',600),(23,10,18,880,17,'2019-10-19','19:00:00',600),(24,11,19,1000,19,'2019-10-10','22:00:00',700),(25,11,20,980,20,'2019-10-11','22:00:00',700),(26,11,21,690,21,'2019-10-12','22:00:00',700);
/*!40000 ALTER TABLE `projekcija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  UNIQUE KEY `jedinstveniKod_UNIQUE` (`jedinstveniKod`),
  KEY `idP_idx` (`idProjekcija`),
  KEY `idStatusRezervacijeRezervacija_idx` (`idStatusRezervacije`),
  KEY `idKorisnikaRezervacija_idx` (`idKorisnika`),
  CONSTRAINT `idKorisnikaRezervacija` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnik` (`idKorisnik`),
  CONSTRAINT `idProjekcijaRezervacija` FOREIGN KEY (`idProjekcija`) REFERENCES `projekcija` (`idProjekcija`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idStatusRezervacijeRezervacija` FOREIGN KEY (`idStatusRezervacije`) REFERENCES `status_rezervacija` (`idStatusaRezervacije`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (14,25,'DBFVHKMKLC',1,'2019-08-25','2019-09-01',2,44),(15,25,'MHTZKFXZKM',1,'2019-08-25','2019-09-01',3,44),(16,25,'ZPFCYTMCCE',1,'2019-08-25','2019-09-01',2,44),(17,24,'DYBUJDZTEC',1,'2019-08-25','2019-09-01',5,44),(18,26,'PQMCGYQJBG',1,'2019-08-31','2019-09-07',2,44);
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reziseri`
--

DROP TABLE IF EXISTS `reziseri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reziseri` (
  `idReziser` int(11) NOT NULL AUTO_INCREMENT,
  `imeReziseri` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idReziser`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reziseri`
--

LOCK TABLES `reziseri` WRITE;
/*!40000 ALTER TABLE `reziseri` DISABLE KEYS */;
INSERT INTO `reziseri` VALUES (4,'Yorgos Lanthimos'),(5,'Bobo Jelicic'),(6,'Ciro Guerra, Cristina Gallego'),(7,'Marco Scop'),(8,'Jessica Hausner'),(9,'Nora Fingscheidt'),(10,'Milos Avramovic'),(11,'Petar Ristovski'),(12,'Andrey Volgin'),(13,'John Sturges'),(14,'Tim Atzinger'),(15,'Anthony Russo, Joe Russo'),(16,'Francesco Cinquemani, George Gallo'),(17,'David Leitch');
/*!40000 ALTER TABLE `reziseri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slike_festivala`
--

DROP TABLE IF EXISTS `slike_festivala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_rezervacija` (
  `idStatusaRezervacije` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStatusaRezervacije`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_rezervacija`
--

LOCK TABLES `status_rezervacija` WRITE;
/*!40000 ALTER TABLE `status_rezervacija` DISABLE KEYS */;
INSERT INTO `status_rezervacija` VALUES (1,'Na cekanju rezervacija'),(2,'Odobrena Rezervacija'),(3,'Odbijena Rezervacija');
/*!40000 ALTER TABLE `status_rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_korisnika`
--

DROP TABLE IF EXISTS `tip_korisnika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tip_korisnika` (
  `idTipKorisnika` int(11) NOT NULL AUTO_INCREMENT,
  `nazivTipa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipKorisnika`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_korisnika`
--

LOCK TABLES `tip_korisnika` WRITE;
/*!40000 ALTER TABLE `tip_korisnika` DISABLE KEYS */;
INSERT INTO `tip_korisnika` VALUES (1,'Neregistrovan'),(2,'Registrovan'),(3,'Prodavac'),(4,'Admin'),(5,'Banovan');
/*!40000 ALTER TABLE `tip_korisnika` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uloge`
--

DROP TABLE IF EXISTS `uloge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
INSERT INTO `uloge` VALUES (8,11),(8,12),(8,13),(9,8),(9,9),(9,10),(10,5),(10,6),(10,7),(11,14),(11,15),(11,16),(12,17),(12,18),(12,19),(13,20),(13,21),(13,22),(14,23),(14,24),(14,25),(15,26),(15,27),(15,28),(16,29),(16,30),(16,31),(17,32),(17,33),(17,34),(18,35),(18,36),(18,37),(19,38),(19,39),(19,40),(20,41),(20,42),(20,43),(21,44),(21,45),(21,46);
/*!40000 ALTER TABLE `uloge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zemlje_porekla`
--

DROP TABLE IF EXISTS `zemlje_porekla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zemlje_porekla` (
  `idZemljePorekla` int(11) NOT NULL AUTO_INCREMENT,
  `imeZemljaPorekla` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idZemljePorekla`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zemlje_porekla`
--

LOCK TABLES `zemlje_porekla` WRITE;
/*!40000 ALTER TABLE `zemlje_porekla` DISABLE KEYS */;
INSERT INTO `zemlje_porekla` VALUES (5,'United Kingdom'),(6,'Croatia'),(7,'Spanija'),(8,'Slovacka'),(9,'Nemacka'),(10,'Austrija'),(11,'Francuska'),(12,'Srbija'),(13,'Amerika');
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

-- Dump completed on 2019-08-31 11:02:48
