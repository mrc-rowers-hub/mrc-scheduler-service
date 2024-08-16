ALTER TABLE sessions
    ADD UNIQUE INDEX unique_session_details (day, start_time, end_time, squad, level, session_type);