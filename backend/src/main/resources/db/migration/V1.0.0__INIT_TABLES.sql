CREATE TABLE IF NOT EXISTS credit_segments (
    personal_code VARCHAR(11) PRIMARY KEY,
    credit_modifier INT NOT NULL,
    debt BOOLEAN NOT NULL
);

INSERT INTO credit_segments VALUES
('49002010965', 0, true),
('11111111111', 0, true),
('49002010976', 100, false),
('22222222222', 100, false),
('49002010987', 300, false),
('33333333333', 300, false),
('49002010998', 1000, false),
('44444444444', 1000, false);