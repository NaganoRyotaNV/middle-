CREATE TABLE hotels (
    id SERIAL PRIMARY KEY,
    hotel_name VARCHAR(100) NOT NULL,
    nearest_station VARCHAR(100),
    price INTEGER NOT NULL
);

INSERT INTO hotels (hotel_name, nearest_station, price) VALUES
('横浜ベイホテル東急', '桜木町駅', 10000),
('渋谷エクセルホテル東急', '渋谷駅', 10000),
('ホテルローズガーデン新宿', '新宿駅', 5000);

CREATE TABLE clothes (
    id SERIAL PRIMARY KEY,
    genre VARCHAR(100) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    size VARCHAR(10),
    price INTEGER NOT NULL
);

INSERT INTO clothes (genre, gender, color, size, price) VALUES
('ジャケット', 'Man', '赤', 'S', 10000),
('シャツ', 'Man', '青', 'M', 5000),
('パンツ', 'Man', '白', 'L', 7000),
('コート', 'Man', '黄', 'M', 15000),
('ワンピース', 'Woman', '赤', 'M', 12000),
('ブラウス', 'Woman', '青', 'S', 6000),
('スカート', 'Woman', '白', 'M', 8000),
('カーディガン', 'Woman', '黄', 'L', 9000);
