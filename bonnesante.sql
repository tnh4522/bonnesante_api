-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 08, 2024 lúc 04:36 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bonnesante`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `schdule_id` int(11) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'Scheduled',
  `schedule_id` bigint(20) DEFAULT NULL,
  `doctor_entity_id` bigint(20) DEFAULT NULL,
  `patient_entity_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `appointment`
--

INSERT INTO `appointment` (`id`, `patient_id`, `doctor_id`, `schdule_id`, `date`, `start_time`, `end_time`, `description`, `status`, `schedule_id`, `doctor_entity_id`, `patient_entity_id`) VALUES
(13, 2, 2, NULL, '8/6/2024', '10:00', NULL, NULL, 'waiting', 13, NULL, NULL),
(14, 2, 2, NULL, '8/6/2024', '14:00', '4:10', NULL, 'denied', 14, NULL, NULL),
(15, 6, 2, NULL, '11/6/2024', '10:00', NULL, NULL, 'pending', 15, NULL, NULL),
(16, 6, 2, NULL, '13/6/2024', '14:00', NULL, NULL, 'pending', 16, NULL, NULL),
(17, 9, 2, NULL, '10/6/2024', '9:00', NULL, NULL, 'waiting', 17, NULL, NULL),
(18, 9, 2, NULL, '14/6/2024', '12:00', NULL, NULL, 'pending', 18, NULL, NULL),
(19, 2, 2, NULL, '6/6/2024', '12:00', NULL, NULL, 'finished', 19, NULL, NULL),
(20, 5, 2, NULL, '10/6/2024', '9:00', '10:23', NULL, 'denied', 20, NULL, NULL),
(21, 5, 2, NULL, '8/6/2024', '15:00', NULL, NULL, 'pending', 21, NULL, NULL),
(22, 6, 2, NULL, '9/6/2024', '9:00', NULL, NULL, 'pending', 19, NULL, NULL),
(23, 2, 2, NULL, '9/6/2024', '9:00', NULL, NULL, 'pending', 20, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doctor`
--

CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `hospital` varchar(255) DEFAULT NULL,
  `time_slot` varchar(255) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `doctor`
--

INSERT INTO `doctor` (`id`, `user_id`, `name`, `age`, `email`, `phone`, `address`, `hospital`, `time_slot`, `specialization`) VALUES
(1, 2, 'doctor', 30, 'doctor@gmail.com', '0905848274', 'Nguyen Van Linh, Da Nang', 'Hoan My Da Nang', '9:00-11:00', 'Tim Mạch'),
(2, 4, 'Phan Nguyên Đạt', 40, 'pnd@gmail.com', '0901313131', 'Me Suot, Da Nang', 'Hoan My Da Nang', NULL, 'Thần Kinh'),
(3, 5, 'Đào Hoài Linh ', 32, 'dhl@gmail.com', '0903211313', 'Thanh Khe, Da Nang', 'Da Khoa Da Nang', NULL, 'Phụ Sản'),
(4, 6, 'Trần Ngọc Huy', 33, 'tnh@gmail.com', '0989123123', 'Au Co, Da Nang', 'Da Khoa Ho Chi Minh', NULL, 'Nội Khoa'),
(5, 7, 'Lê Thị Lương', 38, 'lthl@gmail.com', '0934312424', 'Savanakhet, Laos', 'Trung Uong Hue', NULL, 'Ngoại Khoa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doctor_schedule`
--

CREATE TABLE `doctor_schedule` (
  `id` bigint(20) NOT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `doctor_schedule`
--

INSERT INTO `doctor_schedule` (`id`, `doctor_id`, `date`, `time`, `status`) VALUES
(1, 1, '8/6/2024', '10:00', 'Unavailable'),
(2, 1, '8/6/2024', '12:00', 'Unavailable'),
(3, 1, '9/6/2024', '11:00', 'Unavailable'),
(4, 2, '8/6/2024', '9:00', 'Unavailable'),
(5, 2, '8/6/2024', '15:00', 'Unavailable'),
(6, 2, '8/6/2024', '16:00', 'Unavailable'),
(7, 4, '8/6/2024', '13:00', 'Unavailable'),
(8, 4, '8/6/2024', '15:00', 'Unavailable'),
(9, 4, '8/6/2024', '10:00', 'Unavailable'),
(10, 4, '8/6/2024', '14:00', 'Unavailable'),
(11, 3, '8/6/2024', '7:00', 'Unavailable'),
(12, 3, '8/6/2024', '10:00', 'Unavailable'),
(13, 2, '8/6/2024', '10:00', 'Unavailable'),
(14, 2, '8/6/2024', '14:00', 'Unavailable'),
(15, 2, '11/6/2024', '10:00', 'Unavailable'),
(16, 2, '13/6/2024', '14:00', 'Unavailable'),
(17, 2, '10/6/2024', '9:00', 'Unavailable'),
(18, 2, '14/6/2024', '12:00', 'Unavailable'),
(19, 2, '9/6/2024', '9:00', 'Available'),
(20, 2, '9/6/2024', '9:00', 'Available'),
(21, 2, '10/6/2024', '9:00', 'Unavailable');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `patient`
--

CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `health_id` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `list_doctor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `patient`
--

INSERT INTO `patient` (`id`, `user_id`, `name`, `age`, `gender`, `email`, `phone`, `address`, `health_id`, `blood_group`, `weight`, `height`, `list_doctor`) VALUES
(1, 3, 'Alice InBorderLand', 25, 0, 'patient@gmail.com', '0901117828', '123 Abc St, San Francisco, CA', '123456', 'O+', 50, 160, NULL),
(2, 8, 'Đào Hoài Linh', 24, 0, 'hoailinh@gmail.com', '0932152152', '13 Nui Thanh, Da Nang', '125125', 'O+', 79, 175, '{\"doctorIds\":[2,3]}'),
(3, 9, 'Hoài Linh Đào', 32, 0, 'linhdao@gmail.com', '0987125125', '28 Dien Bien Phu, Da Nang', '136426', 'O-', 53, 172, '{\"doctorIds\":[2]}'),
(4, 10, 'Đình Hoài Lao', 12, 1, 'hoailao@gmail.com', '0934241255', '100 Ky Dong, Da Nang', '757456', 'B-', 46, 169, NULL),
(5, 11, 'Trần Ngọc Huy', 6, 1, 'ngochuy@gmail.com', '0963512215', '12 Phan Boi Chau, Da Nang', '742733', 'B+', 75, 213, '{\"doctorIds\":[2,3,4]}'),
(6, 12, 'Huy Ngọc Trần', 45, 0, 'ngoctran@gmail.com', '0904214156', '15 Phan Dinh Phung, Da Nang', '125167', 'AB+', 65, 189, '{\"doctorIds\":[2,5]}'),
(7, 13, 'Phan Đạt', 79, 1, 'phandat@gmail.com', '0931251251', '332 Nguyen Tat Thanh, Da Nang', '125412', 'A-', 87, 150, '{\"doctorIds\":[2,4]}'),
(8, 14, 'Nguyễn Đạt', 30, 0, 'nguyendat@gmail.com', '0909151251', '20 Hoa Khanh, Da Nang', '126316', 'A+', 89, 177, NULL),
(9, 15, 'Lê Thị Hoài Lương', 18, 1, 'hoailuong@gmail.com', '0902125125', '50 Nguyen Luong Bang, Da Nang', '469326', 'B+', 78, 165, '{\"doctorIds\":[2,3,1]}'),
(10, 16, 'Lương Lê', 45, 1, 'luongle@gmail.com', '0931251612', '315 Nguyen Luong Bang, Da Nang', '125161', 'O+', 65, 160, '{\"doctorIds\":[2]}');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `register_doctor`
--

CREATE TABLE `register_doctor` (
  `id` bigint(20) NOT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `patient_entity_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `register_doctor`
--

INSERT INTO `register_doctor` (`id`, `doctor_id`, `patient_id`, `status`, `patient_entity_id`) VALUES
(5, 2, 2, 'registered', NULL),
(6, 3, 2, 'registered', NULL),
(7, 2, 3, 'denied', NULL),
(8, 2, 5, 'registered', NULL),
(9, 3, 5, 'pending', NULL),
(10, 4, 5, 'pending', NULL),
(11, 2, 6, 'registered', NULL),
(12, 5, 6, 'pending', NULL),
(13, 2, 7, 'pending', NULL),
(14, 4, 7, 'pending', NULL),
(15, 2, 9, 'registered', NULL),
(16, 3, 9, 'pending', NULL),
(17, 1, 9, 'pending', NULL),
(18, 2, 3, 'registered', NULL),
(20, 3, 2, 'pending', NULL),
(21, 2, 10, 'denied', NULL),
(22, 3, 10, 'pending', NULL),
(23, 2, 10, 'registered', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `result_measure`
--

CREATE TABLE `result_measure` (
  `id` int(11) NOT NULL,
  `appointment_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `resp` int(11) DEFAULT NULL,
  `ecg` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 0),
(2, 'doctor', 'doctor', 1),
(3, 'patient', 'patient', 2),
(4, 'datdr', 'nguyendat', 1),
(5, 'hoailinhdr', 'hoailinh', 1),
(6, 'ngochuydr', 'ngochuy', 1),
(7, 'hoailuongdr', 'hoailuong', 1),
(8, 'hoailinh1', '123456', 2),
(9, 'hoailinh2', '123456', 2),
(10, 'hoailinh3', '123456', 2),
(11, 'huytran1', '123456', 2),
(12, 'huytran2', '123456', 2),
(13, 'datphan1', '123456', 2),
(14, 'datphan2', '123456', 2),
(15, 'hoailuong1', '123456', 2),
(16, 'hoailuong2', '123456', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1xaeq7nue4256p5icx7qln0bs` (`doctor_entity_id`),
  ADD UNIQUE KEY `UKmtx8ecmfmow5hm3m0stfx5gu` (`patient_entity_id`);

--
-- Chỉ mục cho bảng `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `doctor_schedule`
--
ALTER TABLE `doctor_schedule`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `register_doctor`
--
ALTER TABLE `register_doctor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKitrnf4anb4olyp9ydlled1uec` (`patient_entity_id`);

--
-- Chỉ mục cho bảng `result_measure`
--
ALTER TABLE `result_measure`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `doctor_schedule`
--
ALTER TABLE `doctor_schedule`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `patient`
--
ALTER TABLE `patient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `register_doctor`
--
ALTER TABLE `register_doctor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `result_measure`
--
ALTER TABLE `result_measure`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `FK67rfsgv0ow0gtrgpttds32bd5` FOREIGN KEY (`patient_entity_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `FK7rrx7q7a92vw9ud464oi3cf9u` FOREIGN KEY (`doctor_entity_id`) REFERENCES `doctor` (`id`);

--
-- Các ràng buộc cho bảng `register_doctor`
--
ALTER TABLE `register_doctor`
  ADD CONSTRAINT `FK2hxwmkc1vjk1j8ekhaodbio1` FOREIGN KEY (`patient_entity_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
