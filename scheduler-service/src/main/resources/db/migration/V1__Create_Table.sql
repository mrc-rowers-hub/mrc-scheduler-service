CREATE TABLE rowers (
    rower_id INT AUTO_INCREMENT PRIMARY KEY,
    rower_name VARCHAR(55) NOT NULL
);

CREATE TABLE sessions (
                          session_id INT AUTO_INCREMENT PRIMARY KEY,
                          day VARCHAR(10),
                          start_time TIME,
                          end_time TIME,
                          squad ENUM('WOMENS', 'DEVELOPMENT', 'MENS'),
                          level ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR'),
                          session_type ENUM('WATER', 'ERG', 'OTHER')
);
