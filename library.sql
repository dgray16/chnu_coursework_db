-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2015 at 07:51 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
`id` smallint(5) NOT NULL COMMENT 'ID of author in DB',
  `name` varchar(20) NOT NULL COMMENT 'First name of author',
  `surname` varchar(25) NOT NULL COMMENT 'Last name of author',
  `birth` smallint(4) NOT NULL COMMENT 'Birthday of author',
  `death` smallint(4) NOT NULL COMMENT 'Death of author'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='This table cointains authors of books';

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `name`, `surname`, `birth`, `death`) VALUES
(1, 'Daniel', 'Defoe', 1800, 1900),
(2, 'Test', 'Test1', 1935, 1254);

-- --------------------------------------------------------

--
-- Table structure for table `binding`
--

CREATE TABLE IF NOT EXISTS `binding` (
`id` tinyint(3) NOT NULL COMMENT 'ID of binding of book',
  `type` varchar(45) NOT NULL COMMENT 'Type-name of binding of book'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `binding`
--

INSERT INTO `binding` (`id`, `type`) VALUES
(1, 'Random');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `isbn` varchar(17) NOT NULL COMMENT 'Unique number of every book',
  `name` varchar(70) NOT NULL COMMENT 'Name of book',
  `publisher_id` smallint(5) NOT NULL COMMENT 'ID of publisher of book',
  `author_id` smallint(5) NOT NULL COMMENT 'ID of author of book',
  `price` decimal(10,0) NOT NULL COMMENT 'Price of new book',
  `binding_id` tinyint(3) NOT NULL COMMENT 'ID of binding type of book',
  `year` smallint(4) NOT NULL COMMENT 'Year of publishment of book',
  `pages` smallint(5) NOT NULL COMMENT 'Number of pages in book',
  `language_id` smallint(5) NOT NULL COMMENT 'Language ID of book',
  `number_of_books` tinyint(3) NOT NULL COMMENT 'Quantity of books in library',
  `income_date` date NOT NULL COMMENT 'Date when book income to library'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`isbn`, `name`, `publisher_id`, `author_id`, `price`, `binding_id`, `year`, `pages`, `language_id`, `number_of_books`, `income_date`) VALUES
('978-5-7502-0064-1', 'Code complete', 1, 2, '300', 1, 2010, 800, 1, 2, '2015-04-01');

-- --------------------------------------------------------

--
-- Table structure for table `circulation`
--

CREATE TABLE IF NOT EXISTS `circulation` (
`id` mediumint(8) NOT NULL COMMENT 'ID of circulation',
  `book_id` varchar(16) NOT NULL COMMENT 'ISBN of book',
  `client_id` smallint(5) NOT NULL COMMENT 'ID of client',
  `giving_time` date NOT NULL COMMENT 'Time when library gives it to client',
  `receiving_time` date DEFAULT NULL COMMENT 'Time when client returns it to library',
  `rent_time` tinyint(3) NOT NULL DEFAULT '30' COMMENT 'Time in days that client can keep book in his hands'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `circulation`
--

INSERT INTO `circulation` (`id`, `book_id`, `client_id`, `giving_time`, `receiving_time`, `rent_time`) VALUES
(1, '1', 1, '2015-04-09', NULL, 30),
(2, '1', 1, '2015-04-01', '2015-04-22', 30);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
`id` smallint(5) NOT NULL COMMENT 'ID of client',
  `name` varchar(65) NOT NULL COMMENT 'Full name of client',
  `birth` smallint(4) NOT NULL COMMENT 'Birthday of client',
  `banned` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Status of client'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `name`, `birth`, `banned`) VALUES
(1, 'Vova Perebykivskiy', 1995, 0);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
`id` tinyint(3) NOT NULL COMMENT 'ID of genre',
  `name` varchar(30) NOT NULL COMMENT 'Name of genre'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(1, 'Adventure'),
(2, 'IT');

-- --------------------------------------------------------

--
-- Table structure for table `genres_of_book`
--

CREATE TABLE IF NOT EXISTS `genres_of_book` (
`id` mediumint(8) NOT NULL COMMENT 'ID of genre relation',
  `book_isbn` varchar(17) NOT NULL COMMENT 'ISBN of book',
  `genre_id` tinyint(3) NOT NULL COMMENT 'ID of genre'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `genres_of_book`
--

INSERT INTO `genres_of_book` (`id`, `book_isbn`, `genre_id`) VALUES
(1, '978-5-7502-0064-1', 1),
(2, '978-5-7502-0064-1', 2);

-- --------------------------------------------------------

--
-- Table structure for table `language`
--

CREATE TABLE IF NOT EXISTS `language` (
`id` smallint(5) NOT NULL COMMENT 'ID of language',
  `language` varchar(50) NOT NULL COMMENT 'Name of language'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `language`
--

INSERT INTO `language` (`id`, `language`) VALUES
(1, 'Ukrainian');

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE IF NOT EXISTS `publisher` (
`id` smallint(5) NOT NULL COMMENT 'ID of publisher',
  `name` varchar(50) NOT NULL COMMENT 'Name of publisher'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`id`, `name`) VALUES
(1, 'A-Ba-Ba-Ga-La-Ma-Ga');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `binding`
--
ALTER TABLE `binding`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
 ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `circulation`
--
ALTER TABLE `circulation`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `genres_of_book`
--
ALTER TABLE `genres_of_book`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `language`
--
ALTER TABLE `language`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
MODIFY `id` smallint(5) NOT NULL AUTO_INCREMENT COMMENT 'ID of author in DB',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `binding`
--
ALTER TABLE `binding`
MODIFY `id` tinyint(3) NOT NULL AUTO_INCREMENT COMMENT 'ID of binding of book',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `circulation`
--
ALTER TABLE `circulation`
MODIFY `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT 'ID of circulation',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
MODIFY `id` smallint(5) NOT NULL AUTO_INCREMENT COMMENT 'ID of client',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
MODIFY `id` tinyint(3) NOT NULL AUTO_INCREMENT COMMENT 'ID of genre',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `genres_of_book`
--
ALTER TABLE `genres_of_book`
MODIFY `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT 'ID of genre relation',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `language`
--
ALTER TABLE `language`
MODIFY `id` smallint(5) NOT NULL AUTO_INCREMENT COMMENT 'ID of language',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
MODIFY `id` smallint(5) NOT NULL AUTO_INCREMENT COMMENT 'ID of publisher',AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
