CREATE DATABASE IF NOT EXISTS hostel_management;
USE hostel_management;

-- Spring Boot JPA will create/update the tables automatically.
-- Optional sample rooms:
INSERT INTO rooms (room_number, type, capacity, occupied_beds)
VALUES ('A101','DOUBLE',2,0), ('A102','TRIPLE',3,0), ('B201','FOUR_SHARING',4,0)
ON DUPLICATE KEY UPDATE room_number=room_number;
