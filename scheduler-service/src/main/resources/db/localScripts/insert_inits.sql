

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
