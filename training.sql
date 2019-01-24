-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 24, 2019 at 06:08 AM
-- Server version: 5.7.19
-- PHP Version: 7.1.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `training`
--
DROP DATABASE IF EXISTS `training`;
CREATE DATABASE IF NOT EXISTS `training` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `training`;

-- --------------------------------------------------------

--
-- Table structure for table `a_training`
--

CREATE TABLE `a_training` (
  `id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `trainingname` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `trainingdesc` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `a_training`
--

INSERT INTO `a_training` (`id`, `trainingname`, `website`, `contact`, `trainingdesc`) VALUES
('A2', 'Android Studio 2 days', 'http://bit.ly/androidjsk', '+60129034614', 'The participants will be guided to create an Android app project, and how to handle the project codes and resources. We will add basic GUIs such as textbox, label, button, image, menus, scrrens and activities.\r\nThe second day will be covering multiple screen interactions, local database, and maps. At the end of the training we will generate Android installer file (APK) and publish it to the Google Playstore.'),
('P4', 'PHP & MySQL Basics to Intermediate 4 days', 'http://bit.ly/phpfstm', '+60129034614', 'At the end of the course, participants will be able to develop a complete Web Application that has SCRUD database functionalities.'),
('Web3', 'Joomla! Basics to Intermediate 3 days', 'http://bit.ly/joomlamy', '+60129034614', 'Covers website development using the Joomla CMS. Joomla is ment to simplified complec website development and management.'),
('Web4', 'Buat Website Mini cara Mudah', 'http://bit.ly/miniwebkuis', '+60129034614', 'Responsive web design penting masa sekarang sebab user banyak guna telefon pintar untuk view website. \r\n\r\nNamun, website developer tak boleh abaikan pengguna laptop/pc. Jadi macam mana nak penuhi keperluan saiz skrin yang pelbagai? Responsive design dengan Bootstrap mampu untuk selesaikan masalah tersebut.'),
('G1', 'Adobe Photoshop for Advertisement Artwork 2 days', 'http://bit.ly/sifugrafik', '+60189899072', 'With the rise of Social Media, companies are resorting to market their product in Facebook/Instagram. Hiring graphic editor is not cheap. Ability to edit product artwork ads is critical as ads behaviour are changing rapidly.'),
('G3', 'Adobe Flash for Android Apps 4 days', 'http://bit.ly/flashmobileapp', '+60189899072', 'This course is for beginner Flash developers who want to use their knowledge of Flash and ActionScript to develop AIR Android Applications. Currently, it is specifically focuses on the Android platform, but many of the topics and examples can be used to develop for any AIR mobile platform.'),
('x1', 'mobile apps', 'www.mmm', '98765', 'try'),
('1234', '1234', 'http://1234', '1234', '1234'),
('123', 'abc', 'www.abc.com', 'suzana', 'abc is good'),
('aq1', 'Al-Quran', 'http://kerul.net', '0129034614', 'Baca al-qurqn tajwid betul'),
('P01', 'Pizza making', 'http://kerul.net', '0129121212', 'lorem ipsum'),
('a9', 'android advanced', 'http://123456', '0102939213', '123456'),
('a6', 'android 6', 'http://123456', '0102939213', '123456'),
('a7', 'android 6', 'http://123456', '0102939213', '123456'),
('A99', 'Android Online DB', 'http://kerul.net', '+60199999999', 'Android client with Online database');

-- --------------------------------------------------------

--
-- Table structure for table `a_training_user`
--

CREATE TABLE `a_training_user` (
  `id` int(11) NOT NULL,
  `uname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pwd` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `userlevel` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `a_training_user`
--

INSERT INTO `a_training_user` (`id`, `uname`, `pwd`, `userlevel`, `fullname`, `email`) VALUES
(17, 'kerul', 'e99a18c428cb38d5f260853678922e03', 'admin', 'Khirulnizam Abd Rahman', 'kerul.net@gmail.com'),
(2, 'abc', 'a1zhPm1TbUmro', '', '', '0'),
(12, 'admin', 'e99a18c428cb38d5f260853678922e03', '', '', '0'),
(18, 'ali', '984d8144fa08bfc637d2825463e184fa', 'admin', 'Ali Ahmad', 'ali@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `sd_users`
--

CREATE TABLE `sd_users` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `riderOrDriverSwitch` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sd_users`
--

INSERT INTO `sd_users` (`id`, `name`, `email`, `phone`, `department`, `username`, `password`, `riderOrDriverSwitch`) VALUES
(1, 'Nasrul Hazim', 'nasrulhazim.m@gmail.com', '0197676173', 'IT', '', '', ''),
(2, 'Tuan Norhafizah', 'tn_hafizah@yahoo.com', '0123456789', 'IT', '', '', ''),
(3, 'Aqil', 'abu.qaidaqil@gmail.com', '0197676177', 'HR', '', '', ''),
(4, 'wewtew', 'asdsa@gmail.com', '9123123213', 'IT', '', '', ''),
(5, 'Khirulnizam Abd Rahman', 'kerul.net@gmail.com', '0129034614', 'IT', 'kerul', '31081ad4bbd4f3bd0e159d3d63bf19bb', ''),
(6, 'Ahmad Mohammad', 'ahmad@gmail.com', '0129034614', 'IT', '', '', ''),
(17, 'Kerul.net ', '+60129034614', 'kerul.net @gmail.co ', 'IT', '', '', ''),
(18, '123', '789', '456', 'Acount', '', '', ''),
(19, 'Jasim Uddin', '01671605743', 'jasimcse2011@gmail.com', 'IT', '', '', ''),
(24, 'Nadiah', 'nadiah@gmail.com', '01289819', NULL, 'nadiah', '104a22c63ccb88b07b6762c31c24622d', 'rider'),
(25, NULL, NULL, '999', NULL, 'll', 'nnn', ''),
(26, NULL, NULL, '999', NULL, 'aku', 'aku13', ''),
(27, NULL, 'aku@gmail.com', '', NULL, '', '871237bf25ba34556a2755fdf2f0ee44', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `a_training`
--
ALTER TABLE `a_training`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `a_training_user`
--
ALTER TABLE `a_training_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `a_training_user`
--
ALTER TABLE `a_training_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
