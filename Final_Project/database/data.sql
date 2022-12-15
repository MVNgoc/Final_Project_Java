CREATE DATABASE IF NOT EXISTS restaurant DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE restaurant;

CREATE TABLE admin_account (
  id varchar(15) NOT NULL,
  username varchar(64) NOT NULL,
  pass varchar(255) NOT NULL
);

CREATE TABLE user (
  id varchar(20) NOT NULL,
  email varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  password varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  username varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  phone varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  name varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  address varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  reset_password_token varchar(45),
  role varchar(10) NOT NULL
);

CREATE TABLE category (
  id varchar(15) NOT NULL,
  title varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  img_name varchar(225) COLLATE utf8_unicode_ci DEFAULT 'food-placeholder.png',
  featured varchar(225) COLLATE utf8_unicode_ci NOT NULL
);

CREATE TABLE food_order (
  id varchar(15) NOT NULL,
  food_name varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  img_food varchar(225) COLLATE utf8_unicode_ci DEFAULT 'food-placeholder.png',
  quantity int(10) NOT NULL,
  username varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  phone_number varchar(15) NOT NULL,
  email varchar(64) NOT NULL,
  user_address varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  total_price int(255) NOT NULL
);

CREATE TABLE food (
  id varchar(15) NOT NULL,
  title varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  img_food varchar(225) COLLATE utf8_unicode_ci DEFAULT 'food-placeholder.png',
  description_food varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  price int(255) NOT NULL,
  category_name varchar(64) COLLATE utf8_unicode_ci NOT NULL
);

CREATE TABLE contact (
  id varchar(15) NOT NULL,
  username varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  useremail varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  subject varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  contributions varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  timecreate varchar(225) NOT NULL,
  daycreate varchar(225) NOT NULL

);

CREATE TABLE reservation (
  id varchar(15) NOT NULL,
  username varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  useremail varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  phone_number varchar(15) NOT NULL,
  timereservation varchar(225) NOT NULL,
  dayreservation varchar(225) NOT NULL,
  people varchar(3),
  contributions varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  timecreate varchar(225) NOT NULL,
  daycreate varchar(225) NOT NULL

);

-- pass: 123456
INSERT INTO user (email, password, username, phone, name, address, role) VALUES 
('admin@gmail.com', '$2a$10$J.2rANIWG/IB2I9uNl/fI.bvU0s0Ct3z8rPHvM.KHDtXdBGbi1xo.', 'admin', '43242626', 'ADMIN', '43/38 Dạ Nam, P2', 'ADMIN');

INSERT INTO admin_account (id, username, pass) VALUES
('51900147', 'admin', '$2a$12$iWE1Epee9DVR0idqIriuAus3TkZklYMqEZVX3i2HSne1jeldtYjXO');


INSERT INTO category (id,title, img_name, featured) VALUES
('1','Chay', 'món chay.jpg', 'Thanh đạm và dễ ăn'),
('2','Lẩu', 'món lẩu.jpg', 'Ăn vào nhưng ngày lạnh thì không còn gì bằng :3'),
('3','Nướng', 'món nướng.jpg', 'Nướng làm đánh bật mùi vị của món ăn'),
('4','Xào', 'món xào.jpg', 'Ngon và dễ ăn'),
('5','Fastfood', 'fastfood.jpg', 'Vừa ngon vừa tiện lợi');

INSERT INTO food (id,title, img_food, description_food, price, category_name) VALUES
('1','Canh rong biển hạt sen', 'canh rong biển hạt sen.jpg', 'Canh rong biển hạt sen có tác dụng giải nhiệt rất tốt', '30000', 'Chay'),
('2','Canh nấm hạt sen', 'canh nấm hạt sen.jpg', 'Ngon và bổ dưỡng', '30000', 'Chay'),
('3','Nem rán chay', 'nem rán chay.jpg', 'Nem rán có hương vị thơm ngon, màu sắc bắt mắt', '5000', 'Chay'),
('4','Đậu hủ xào rau củ', 'đậu hủ xào rau củ.jpg', 'Hương vị hấp dẫn', '25000', 'Chay'),
('5','Súp đậu đỏ', 'súp đậu đỏ.jpg', 'Ăn súp đậu đỏ thường xuyên sẽ giúp bạn có cơ thể cân đối', '35000', 'Chay'),
('6','Đậu hủ kho xả ớt', 'đậu hủ kho xả ớt.jpg', 'Đậu hũ non mềm, chiên vàng đều rồi kho gia vị đậm đà với sả, ớt cay nồng', '25000', 'Chay'),
('7','Lẩu thái chua cay', 'lẩu thái chua cay.jpg', 'Một món vừa đẹp mắt lại ngon vô cùng', '110000', 'Lẩu'),
('8','Lẩu gà lá giang', 'lẩu gà lá giang.jpg', 'Trời mưa phùn mà được thưởng thức nồi lẩu gà thơm nghi ngút cùng vị chua của lá giang', '120000', 'Lẩu'),
('9','Lẩu gà ớt hiểm', 'lẩu gà ớt hiểm.jpg', 'Ai thưởng thức qua một lần rồi chắc chắn không bao giờ quên.', '120000', 'Lẩu'),
('10','Lẩu bò', 'lẩu bò.jpg', 'món lẩu bò thơm nức mũi', '130000', 'Lẩu'),
('11','Lẩu lươn chua cay', 'lẩu lươn chua cay.jpg', 'Thịt lương không chỉ ngọt mà còn vô cùng nhiều chất dinh dưỡng', '130000', 'Lẩu'),
('12','Lẩu đuôi bò', 'lẩu đuôi bò.jpg', 'Đuôi bò dai dai, mềm mềm cùng nước dùng thanh ngọt khó mà chối từ.', '140000', 'Lẩu'),
('13','Tôm nướng muối ớt', 'tôm nướng muối ớt.jpg', 'Còn gì tuyệt hơn khi vào ngày lạnh được quây quần cùng bạn bè và gia đình (1 phần 5 con)', '50000', 'Nướng'),
('14','Ba chỉ nướng BBQ', 'ba chỉ nướng bbq.jpg', 'Thịt ba chỉ do được xen lẫn cả nạc và mỡ nên khi nướng lên có vị ngậy rất ngon', '15000', 'Nướng'),
('15','Cánh gà nướng', 'cánh gà nướng.jpg', 'Vừa quen thuộc vừa ngon', '50000', 'Nướng'),
('16','Thị bò xiên rau củ', 'thịt bò xiên rau củ.jpg', 'Món nướng xiên que vừa nhiều màu sắc vừa ngon', '15000', 'Nướng'),
('17','Thịt bò cuốn nấm kim châm', 'Thịt bò cuốn nấm kim châm.jpg', 'Hấp dẫn và được nhiều người yêu thích', '50000', 'Nướng'),
('18','Bạch tuộc nướng sa tế', 'Bạch tuộc nướng sa tế.jpg', 'Cắt thành những miếng nhỏ và chấm gia vị chanh muối vô cùng hấp dẫn', '50000', 'Nướng'),
('19','Thịt bò xào cần tây', 'Thịt bò xào cần tây.jpg', 'Ngon và đẹp mắt', '25000', 'Xào'),
('20','Thịt heo xào sả nấm', 'Thịt heo xào sả nấm.jpg', 'Nấm hải sản là loại nấm phù hợp nhất với sả.', '25000', 'Xào'),
('21','Lòng xào dưa chua', 'Lòng xào dưa chua.jpg', 'Ngon mà không bị quá chua', '35000', 'Xào'),
('22','Thịt trâu xào rau muống', 'Thịt trâu xào rau muống.jpeg', 'Dễ ăn, thanh đạm', '30000', 'Xào'),
('23','Thịt gà xào giá đỗ', 'Thịt gà xào giá đỗ.jpg', 'Thích hợp để ăn cùng gia đình', '25000', 'Xào'),
('24','Lòng già xào nghệ', 'Lòng già xào nghệ.jpeg', 'Món ăn rất hợp để ăn lai rai', '35000', 'Xào'),
('25','Pizza Hải Sản Xốt Pesto', 'Pizza Hải Sản Xốt Pesto.jpg', 'Ngon và dễ ăn', '129000', 'Fastfood'),
('26','Pizza Thập Cẩm', 'Pizza Thập Cẩm.jpg', 'Mang Hương Vị Truyền Thống Với ThịT Bò, Thịt Xông Khói', '129000', 'Fastfood'),
('27','Mì Ý Bò Bằm Xốt Cà Chua', 'Mì Ý Bò Bằm Xốt Cà Chua.jpg', 'Mì Ý Xốt Cà Chua Với Thịt Bò', '120000', 'Fastfood'),
('28','Mì Ý Hải Sản Xốt Cà Chua', 'Mì Ý Hải Sản Xốt Cà Chua.jpg', 'Mì Ý Xốt Cà Chua Với Tôm, Mực, Hành Tây Và Ớt Chuông Xanh', '130000', 'Fastfood'),
('29','Bánh Mì Bơ Tỏi', 'Bánh Mì Bơ Tỏi.jpg', 'Bánh Mì Bơ Tỏi Nướng Giòn Ngon Phủ Xốt Bơ Tỏi Thơm Nồng', '29000', 'Fastfood'),
('30','Xà Lách Trộn Cá Ngừ Và Thịt Xông Khói', 'Xà Lách Trộn Cá Ngừ Và Thịt Xông Khói.jpg', 'Rau Xanh Trộn Với Cá Ngừ,...', '79000', 'Fastfood');



ALTER TABLE admin_account
  ADD PRIMARY KEY (id);

ALTER TABLE admin_account
  MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

ALTER TABLE user
  ADD PRIMARY KEY (id);

ALTER TABLE user
  MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

ALTER TABLE category
ADD PRIMARY KEY (id);

ALTER TABLE category
  MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

ALTER TABLE food_order
ADD PRIMARY KEY (id);

ALTER TABLE food_order
MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

ALTER TABLE food
ADD PRIMARY KEY (id);

ALTER TABLE food
  MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

ALTER TABLE contact
ADD PRIMARY KEY (id);

ALTER TABLE contact
  MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;

ALTER TABLE reservation
ADD PRIMARY KEY (id);

ALTER TABLE reservation
  MODIFY id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=20;