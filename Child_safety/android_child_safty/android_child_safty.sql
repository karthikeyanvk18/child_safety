-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 09, 2022 at 12:20 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `android_child_safty`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_mobile`
--

CREATE TABLE `admin_mobile` (
  `mobile` text NOT NULL,
  `status` varchar(100) NOT NULL,
  `report` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_mobile`
--


-- --------------------------------------------------------

--
-- Table structure for table `parent_detail`
--

CREATE TABLE `parent_detail` (
  `id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `child` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parent_detail`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_location`
--

CREATE TABLE `user_location` (
  `first` varchar(100) NOT NULL,
  `second` varchar(100) NOT NULL,
  `l1` varchar(100) NOT NULL,
  `l2` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `report` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_location`
--

