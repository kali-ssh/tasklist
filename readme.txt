
■テーブル作成
CREATE TABLE mytable(
    id VARCHAR(3) PRIMARY KEY,
    message VARCHAR(256)
);

INSERT INTO mytable VALUES('001', 'こんにちは'),('002', 'こんにちは'),('003', 'こんにちは'),('004', 'こんにちは');

select * from mytable;


UPDATE mytable SET message ='Good Morning' WHERE id = '001';



■タスクテーブルを作成
CREATE TABLE tasklist(
    id VARCHAR(8) PRIMARY KEY,
    task VARCHAR(256),
    deadline VARCHAR(10),
    done BOOLEAN
);


select * from tasklist;

INSERT INTO tasklist VALUES('00001','JAVA本の原稿を書く','2021-09-30',FALSE);

UPDATE tasklist SET task='JAVA本の原稿を編集部に送る',deadline='2021-10-03' WHERE id='00001';

DELETE FROM tasklist WHERE id='00001';
