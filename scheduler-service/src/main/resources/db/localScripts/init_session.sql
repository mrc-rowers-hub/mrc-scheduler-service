USE rowers_hub;

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

INSERT INTO upcoming_sessions (session_id, date)
VALUES
    (1, '2025-05-19'),
    (2, '2025-05-19'),
    (3, '2025-05-19'),
    (4, '2025-05-20'),
    (5, '2025-05-20'),
    (6, '2025-05-20'),
    (7, '2025-05-20'),
    (8, '2025-05-20'),
    (9, '2025-05-21'),
    (10, '2025-05-21'),
    (11, '2025-05-21'),
    (12, '2025-05-22'),
    (13, '2025-05-22'),
    (14, '2025-05-22'),
    (15, '2025-05-23'),
    (16, '2025-05-23'),
    (17, '2025-05-23');

-- Insert the next four upcoming dates for each session day
-- INSERT INTO upcoming_sessions (session_id, date)
-- SELECT
--     s.session_id,
--     DATE_ADD(
--             CURDATE(),
--             INTERVAL
--         (7 + WEEKDAY(CASE s.day
--                 WHEN 'MONDAY' THEN '2026-01-01'
--                 WHEN 'TUESDAY' THEN '2026-01-02'
--                 WHEN 'WEDNESDAY' THEN '2026-01-03'
--                 WHEN 'THURSDAY' THEN '2026-01-04'
--                 WHEN 'FRIDAY' THEN '2026-01-05'
--                 WHEN 'SATURDAY' THEN '2026-01-06'
--                 WHEN 'SUNDAY' THEN '2026-01-07'
--             END) - WEEKDAY(CURDATE())) % 7 + (i*7) DAY
--     ) AS next_date
-- FROM
--     sessions s
--         CROSS JOIN
--     (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) AS intervals
-- WHERE
--     DATE_ADD(
--             CURDATE(),
--             INTERVAL
--         (7 + WEEKDAY(CASE s.day
--                 WHEN 'MONDAY' THEN '2026-01-01'
--                 WHEN 'TUESDAY' THEN '2026-01-02'
--                 WHEN 'WEDNESDAY' THEN '2026-01-03'
--                 WHEN 'THURSDAY' THEN '2026-01-04'
--                 WHEN 'FRIDAY' THEN '2026-01-05'
--                 WHEN 'SATURDAY' THEN '2026-01-06'
--                 WHEN 'SUNDAY' THEN '2026-01-07'
--             END) - WEEKDAY(CURDATE())) % 7 + (i*7) DAY
--     ) > CURDATE()
-- ORDER BY
--     s.session_id, next_date;

INSERT INTO upcoming_session_availability (upcoming_session_id, rower_id)
VALUES
    (1, 1), (1, 4), (1, 15),
    (2, 2), (2, 5),
    (3, 3), (3, 6),
    (4, 7), (4, 8),
    (5, 9), (5, 10),
    (6, 11), (6, 12),
    (7, 13), (7, 14),
    (8, 1), (8, 2),
    (9, 3), (9, 4),
    (10, 5), (10, 6),
    (11, 7), (11, 8),
    (12, 9), (12, 10),
    (13, 11), (13, 12),
    (14, 13), (14, 14),
    (15, 15), (15, 16),
    (16, 17), (16, 18),
    (17, 19), (17, 20);

INSERT INTO rowers (name, squad, level)
VALUES
    ('Addi', 'WOMENS', 'NOVICE'),
    ('Jess C', 'WOMENS', 'INTERMEDIATE'),
    ('Laura', 'WOMENS', 'SENIOR'),
    ('Bea', 'WOMENS', 'NOVICE'),
    ('Sara', 'WOMENS', 'INTERMEDIATE'),
    ('Mia', 'WOMENS', 'SENIOR'),
    ('Tom', 'MENS', 'NOVICE'),
    ('Jack', 'MENS', 'INTERMEDIATE'),
    ('Ryan', 'MENS', 'SENIOR'),
    ('Luke', 'MENS', 'NOVICE'),
    ('Ben', 'MENS', 'INTERMEDIATE'),
    ('Chris', 'MENS', 'SENIOR'),
    ('Alice', 'DEVELOPMENT', 'DEVELOPMENT'),
    ('Zara', 'DEVELOPMENT', 'DEVELOPMENT'),
    ('Nina', 'WOMENS', 'NOVICE'),
    ('Emily', 'WOMENS', 'INTERMEDIATE'),
    ('Sophie', 'WOMENS', 'SENIOR'),
    ('Max', 'MENS', 'NOVICE'),
    ('Liam', 'MENS', 'INTERMEDIATE'),
    ('Owen', 'MENS', 'SENIOR');


INSERT INTO past_sessions (upcoming_session_id, session_id, date)
VALUES
    (1, 1, '2025-05-05'),
    (2, 2, '2025-05-12'),
    (3, 3, '2025-05-15'),
    (4, 4, '2025-05-13'),
    (5, 5, '2025-05-06'),
    (6, 6, '2025-05-13'),
    (7, 7, '2025-05-06'),
    (8, 8, '2025-05-13'),
    (9, 9, '2025-05-14'),
    (10, 10, '2025-05-14'),
    (11, 11, '2025-05-14'),
    (12, 12, '2025-05-15'),
    (13, 13, '2025-05-15'),
    (14, 14, '2025-05-15'),
    (15, 15, '2025-05-16'),
    (16, 16, '2025-05-16'),
    (17, 17, '2025-05-16');


INSERT INTO past_session_availability (upcoming_session_id, rower_id)
VALUES
    (1, 1), (1, 4), (1, 15),
    (2, 2), (2, 5),
    (3, 3), (3, 6),
    (4, 1), (4, 2),
    (5, 7), (5, 10),
    (6, 8), (6, 11),
    (7, 9), (7, 12),
    (8, 1), (8, 13),
    (9, 14), (9, 15),
    (10, 16), (10, 17),
    (11, 18), (11, 19),
    (12, 20), (12, 3),
    (13, 5), (13, 6),
    (14, 7), (14, 8),
    (15, 9), (15, 10),
    (16, 11), (16, 12),
    (17, 13), (17, 14);