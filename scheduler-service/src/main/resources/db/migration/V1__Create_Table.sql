CREATE TABLE rowers
(
    rower_id   INT AUTO_INCREMENT PRIMARY KEY,
    rower_name VARCHAR(55) NOT NULL
);

CREATE TABLE sessions
(
    session_id   INT AUTO_INCREMENT PRIMARY KEY,
    day          VARCHAR(10) NOT NULL,
    start_time   TIME        NOT NULL,
    end_time     TIME        NOT NULL,
    squad        ENUM('WOMENS', 'DEVELOPMENT', 'MENS') NOT NULL,
    level        ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR') NOT NULL,
    session_type ENUM('WATER', 'ERG', 'OTHER') NOT NULL
);

CREATE TABLE upcoming_sessions
(
    upcoming_session_id INT AUTO_INCREMENT PRIMARY KEY,
    session_id          INT  NOT NULL,
    date                DATE NOT NULL
);

CREATE TABLE past_sessions
(
    upcoming_session_id INT AUTO_INCREMENT PRIMARY KEY,
    session_id          INT  NOT NULL,
    date                DATE NOT NULL
);

CREATE TABLE upcoming_session_availability
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    upcoming_session_id INT NOT NULL,
    rower_id            INT NOT NULL
);

CREATE TABLE past_session_availability
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    upcoming_session_id INT NOT NULL,
    rower_id            INT NOT NULL
);

-- also need a trigger for a delete/add on sessions, to update the upcoming_sessions and availability!
-- need a way to move any past sessions into an old table before the event to update_upcoming_sessions happens
