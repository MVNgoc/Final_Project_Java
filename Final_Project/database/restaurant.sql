-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2022 at 10:06 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_account`
--

CREATE TABLE `admin_account` (
  `id` int(11) NOT NULL COMMENT 'id',
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin_account`
--

INSERT INTO `admin_account` (`id`, `username`, `pass`) VALUES
(51900147, 'admin', '$2a$12$iWE1Epee9DVR0idqIriuAus3TkZklYMqEZVX3i2HSne1jeldtYjXO');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL COMMENT 'id',
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `img_name` varchar(225) COLLATE utf8_unicode_ci DEFAULT 'food-placeholder.png',
  `featured` varchar(225) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `title`, `img_name`, `featured`) VALUES
(1, 'Chay', 'món chay.jpg', 'Thanh đạm và dễ ăn'),
(2, 'Lẩu', 'món lẩu.jpg', 'Ăn vào nhưng ngày lạnh thì không còn gì bằng :3'),
(3, 'Nướng', 'món nướng.jpg', 'Nướng làm đánh bật mùi vị của món ăn'),
(4, 'Xào', 'món xào.jpg', 'Ngon và dễ ăn'),
(5, 'Fastfood', 'fastfood.jpg', 'Vừa ngon vừa tiện lợi');

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL COMMENT 'id',
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `useremail` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `subject` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `contributions` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `timecreate` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `daycreate` varchar(225) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL COMMENT 'id',
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `img_food` varchar(225) COLLATE utf8_unicode_ci DEFAULT 'food-placeholder.png',
  `description_food` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(255) NOT NULL,
  `category_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `title`, `img_food`, `description_food`, `price`, `category_name`) VALUES
(1, 'Canh rong biển hạt sen', 'canh rong biển hạt sen.jpg', 'Canh rong biển hạt sen có tác dụng giải nhiệt rất tốt', 30000, 'Chay'),
(2, 'Canh nấm hạt sen', 'canh nấm hạt sen.jpg', 'Ngon và bổ dưỡng', 30000, 'Chay'),
(3, 'Nem rán chay', 'nem rán chay.jpg', 'Nem rán có hương vị thơm ngon, màu sắc bắt mắt', 5000, 'Chay'),
(4, 'Đậu hủ xào rau củ', 'đậu hủ xào rau củ.jpg', 'Hương vị hấp dẫn', 25000, 'Chay'),
(5, 'Súp đậu đỏ', 'súp đậu đỏ.jpg', 'Ăn súp đậu đỏ thường xuyên sẽ giúp bạn có cơ thể cân đối', 35000, 'Chay'),
(6, 'Đậu hủ kho xả ớt', 'đậu hủ kho xả ớt.jpg', 'Đậu hũ non mềm, chiên vàng đều rồi kho gia vị đậm đà với sả, ớt cay nồng', 25000, 'Chay'),
(7, 'Lẩu thái chua cay', 'lẩu thái chua cay.jpg', 'Một món vừa đẹp mắt lại ngon vô cùng', 110000, 'Lẩu'),
(8, 'Lẩu gà lá giang', 'lẩu gà lá giang.jpg', 'Trời mưa phùn mà được thưởng thức nồi lẩu gà thơm nghi ngút cùng vị chua của lá giang', 120000, 'Lẩu'),
(9, 'Lẩu gà ớt hiểm', 'lẩu gà ớt hiểm.jpg', 'Ai thưởng thức qua một lần rồi chắc chắn không bao giờ quên.', 120000, 'Lẩu'),
(10, 'Lẩu bò', 'lẩu bò.jpg', 'món lẩu bò thơm nức mũi', 130000, 'Lẩu'),
(11, 'Lẩu lươn chua cay', 'lẩu lươn chua cay.jpg', 'Thịt lương không chỉ ngọt mà còn vô cùng nhiều chất dinh dưỡng', 130000, 'Lẩu'),
(12, 'Lẩu đuôi bò', 'lẩu đuôi bò.jpg', 'Đuôi bò dai dai, mềm mềm cùng nước dùng thanh ngọt khó mà chối từ.', 140000, 'Lẩu'),
(15, 'Cánh gà nướng', 'cánh gà nướng.jpg', 'Vừa quen thuộc vừa ngon', 50000, 'Nướng'),
(16, 'Thị bò xiên rau củ', 'thịt bò xiên rau củ.jpg', 'Món nướng xiên que vừa nhiều màu sắc vừa ngon', 15000, 'Nướng'),
(17, 'Thịt bò cuốn nấm kim châm', 'Thịt bò cuốn nấm kim châm.jpg', 'Hấp dẫn và được nhiều người yêu thích', 50000, 'Nướng'),
(18, 'Bạch tuộc nướng sa tế', 'Bạch tuộc nướng sa tế.jpg', 'Cắt thành những miếng nhỏ và chấm gia vị chanh muối vô cùng hấp dẫn', 50000, 'Nướng'),
(19, 'Thịt bò xào cần tây', 'Thịt bò xào cần tây.jpg', 'Ngon và đẹp mắt', 25000, 'Xào'),
(20, 'Thịt heo xào sả nấm', 'Thịt heo xào sả nấm.jpg', 'Nấm hải sản là loại nấm phù hợp nhất với sả.', 25000, 'Xào'),
(21, 'Lòng xào dưa chua', 'Lòng xào dưa chua.jpg', 'Ngon mà không bị quá chua', 35000, 'Xào'),
(22, 'Thịt trâu xào rau muống', 'Thịt trâu xào rau muống.jpeg', 'Dễ ăn, thanh đạm', 30000, 'Xào'),
(23, 'Thịt gà xào giá đỗ', 'Thịt gà xào giá đỗ.jpg', 'Thích hợp để ăn cùng gia đình', 25000, 'Xào'),
(24, 'Lòng già xào nghệ', 'Lòng già xào nghệ.jpeg', 'Món ăn rất hợp để ăn lai rai', 35000, 'Xào'),
(25, 'Pizza Hải Sản Xốt Pesto', 'Pizza Hải Sản Xốt Pesto.jpg', 'Ngon và dễ ăn', 129000, 'Fastfood'),
(26, 'Pizza Thập Cẩm', 'Pizza Thập Cẩm.jpg', 'Mang Hương Vị Truyền Thống Với ThịT Bò, Thịt Xông Khói', 129000, 'Fastfood'),
(27, 'Mì Ý Bò Bằm Xốt Cà Chua', 'Mì Ý Bò Bằm Xốt Cà Chua.jpg', 'Mì Ý Xốt Cà Chua Với Thịt Bò', 120000, 'Fastfood'),
(28, 'Mì Ý Hải Sản Xốt Cà Chua', 'Mì Ý Hải Sản Xốt Cà Chua.jpg', 'Mì Ý Xốt Cà Chua Với Tôm, Mực, Hành Tây Và Ớt Chuông Xanh', 130000, 'Fastfood'),
(29, 'Bánh Mì Bơ Tỏi', 'Bánh Mì Bơ Tỏi.jpg', 'Bánh Mì Bơ Tỏi Nướng Giòn Ngon Phủ Xốt Bơ Tỏi Thơm Nồng', 29000, 'Fastfood'),
(30, 'Xà Lách Trộn Cá Ngừ Và Thịt Xông Khói', 'Xà Lách Trộn Cá Ngừ Và Thịt Xông Khói.jpg', 'Rau Xanh Trộn Với Cá Ngừ,...', 79000, 'Fastfood');

-- --------------------------------------------------------

--
-- Table structure for table `food_order`
--

CREATE TABLE `food_order` (
  `id` int(11) NOT NULL COMMENT 'id',
  `food_name` longtext COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(10) NOT NULL,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `total_price` int(255) NOT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `food_order`
--

INSERT INTO `food_order` (`id`, `food_name`, `quantity`, `username`, `phone_number`, `email`, `user_address`, `total_price`, `status`) VALUES
(28, 'Lẩu thái chua cay,1,Lẩu lươn chua cay,1,Cánh gà nướng,1,', 3, 'admin', '43242626', 'admin@gmail.com', 'zaz', 290000, 'Đang chờ duyệt'),
(29, 'Lẩu gà ớt hiểm,4,Cánh gà nướng,1,', 5, 'admin', '43242626jiji', 'admin@gmail.com', 'ijijijij', 170000, 'Đang chờ duyệt');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL COMMENT 'id',
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `useremail` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `timereservation` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `dayreservation` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `people` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contributions` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `timecreate` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `daycreate` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `reset_password_token` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `username`, `phone`, `name`, `address`, `reset_password_token`, `role`) VALUES
(0, 'admin@gmail.com', '$2a$10$J.2rANIWG/IB2I9uNl/fI.bvU0s0Ct3z8rPHvM.KHDtXdBGbi1xo.', 'admin', '43242626', 'ADMIN', '43/38 Dạ Nam, P2', NULL, 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_account`
--
ALTER TABLE `admin_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food_order`
--
ALTER TABLE `food_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_account`
--
ALTER TABLE `admin_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=51900148;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `food_order`
--
ALTER TABLE `food_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
