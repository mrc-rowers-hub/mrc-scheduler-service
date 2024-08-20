INSERT INTO rowers_hub.sessions
( day,
  start_time,
  end_time,
  squad,
  level,
  session_type)
VALUES
    ("MONDAY", "18:00:00", "20:00:00", "WOMENS", "NOVICE", "WATER"),
    ("MONDAY", "18:00:00", "20:00:00", "WOMENS", "INTERMEDIATE", "WATER"),
    ("MONDAY", "18:00:00", "20:00:00", "WOMENS", "SENIOR", "WATER"),
    ("TUESDAY", "06:00:00", "08:00:00", "WOMENS", "INTERMEDIATE", "WATER"),
    ("TUESDAY", "06:00:00", "08:00:00", "MENS", "INTERMEDIATE", "WATER"),
    ("TUESDAY", "18:00:00", "20:00:00", "WOMENS", "NOVICE", "WATER"),
    ("TUESDAY", "18:00:00", "20:00:00", "WOMENS", "INTERMEDIATE", "WATER"),
    ("TUESDAY", "18:00:00", "20:00:00", "WOMENS", "SENIOR", "WATER"),
    ("WEDNESDAY", "18:00:00", "20:00:00", "MENS", "NOVICE", "WATER"),
    ("WEDNESDAY", "18:00:00", "20:00:00", "MENS", "INTERMEDIATE", "WATER"),
    ("WEDNESDAY", "18:00:00", "20:00:00", "MENS", "SENIOR", "WATER"),
    ("THURSDAY", "06:00:00", "08:00:00", "WOMENS", "INTERMEDIATE", "WATER"),
    ("THURSDAY", "06:00:00", "08:00:00", "MENS", "INTERMEDIATE", "WATER"),
    ("THURSDAY", "18:00:00", "20:00:00", "DEVELOPMENT", "DEVELOPMENT", "WATER"),
    ("FRIDAY", "18:00:00", "20:00:00", "MENS", "NOVICE", "WATER"),
    ("FRIDAY", "18:00:00", "20:00:00", "MENS", "INTERMEDIATE", "WATER"),
    ("FRIDAY", "18:00:00", "20:00:00", "MENS", "SENIOR", "WATER");



-- Insert the next four upcoming dates for each session day
INSERT INTO upcoming_sessions (session_id, date)
SELECT
    s.session_id,
    DATE_ADD(
            CURDATE(),
            INTERVAL
        (7 + WEEKDAY(CASE s.day
                WHEN 'MONDAY' THEN '2024-01-01'
                WHEN 'TUESDAY' THEN '2024-01-02'
                WHEN 'WEDNESDAY' THEN '2024-01-03'
                WHEN 'THURSDAY' THEN '2024-01-04'
                WHEN 'FRIDAY' THEN '2024-01-05'
                WHEN 'SATURDAY' THEN '2024-01-06'
                WHEN 'SUNDAY' THEN '2024-01-07'
            END) - WEEKDAY(CURDATE())) % 7 + (i*7) DAY
    ) AS next_date
FROM
    sessions s
        CROSS JOIN
    (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) AS intervals
WHERE
    DATE_ADD(
            CURDATE(),
            INTERVAL
        (7 + WEEKDAY(CASE s.day
                WHEN 'MONDAY' THEN '2024-01-01'
                WHEN 'TUESDAY' THEN '2024-01-02'
                WHEN 'WEDNESDAY' THEN '2024-01-03'
                WHEN 'THURSDAY' THEN '2024-01-04'
                WHEN 'FRIDAY' THEN '2024-01-05'
                WHEN 'SATURDAY' THEN '2024-01-06'
                WHEN 'SUNDAY' THEN '2024-01-07'
            END) - WEEKDAY(CURDATE())) % 7 + (i*7) DAY
    ) > CURDATE()
ORDER BY
    s.session_id, next_date;

INSERT INTO upcoming_session_availability
(upcoming_session_id, rower_id)
VALUES (1, 24);


INSERT rowers
(name, squad, level)
VALUES
('Addi', 'WOMENS', 'NOVICE'),
('Jess C', 'WOMENS', 'INTERMEDIATE'),
('Laura', 'WOMENS', 'SENIOR');

INSERT INTO past_sessions
(upcoming_session_id, session_id, date)
VALUES
(1, 1, '2024-01-01'),
(2, 2, '2024-01-02');

INSERT INTO past_session_availability
(upcoming_session_id, rower_id)
VALUES
(1, 1),
(2, 1),
(1, 3);