CREATE TABLE rowers (
    rower_id INT AUTO_INCREMENT PRIMARY KEY,
    rower_name VARCHAR(55) NOT NULL
);

CREATE TABLE sessions (
                          session_id INT AUTO_INCREMENT PRIMARY KEY,
                          day VARCHAR(10) NOT NULL,
                          start_time TIME NOT NULL,
                          end_time TIME NOT NULL,
                          squad ENUM('WOMENS', 'DEVELOPMENT', 'MENS') NOT NULL,
                          level ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR') NOT NULL,
                          session_type ENUM('WATER', 'ERG', 'OTHER') NOT NULL
);
