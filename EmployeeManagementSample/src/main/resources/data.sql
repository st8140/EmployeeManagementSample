INSERT INTO employees (
employee_id,
employee_name,
gender,
employee_phone,
employee_email,
password,
date_of_entry,
start_date,
end_date
) VALUES
(1,'foobar', 1, '080-1234-5678','foobar@a.com', 'password', '2000-04-01', '2000-04-01', '2000-04-01'),
(2,'john Doe', 1, '090-1234-5678','johndoe@b.com', 'password', '2000-04-01', '2000-04-01', '2000-04-01'),
(3,'hoge', 2, '080-5678-1234','hoge@a.com', 'password', '2022-02-01', '2022-02-01', '2022-02-01'),
(4,'hogehoge', 2, '090-5678-1234','hogehoge@a.com', 'password', '2001-03-01', '2001-03-01', '2001-03-01'),
(5,'山田太郎', 1, '080-9876-1234','t-yamada@d.com', 'password', '2007-04-01', '2007-04-01', '2007-04-01'),
(6,'山田花子', 2, '090-9876-1234','h-yamada@e.com', 'password', '2007-04-01', '2007-04-01', '2007-04-01'),
(7,'斎藤一郎', 1, '090-7890-6543','i-saitou@f.com', 'password', '2020-08-01', '2020-08-01', '2020-08-01'),
(8,'渡辺大貴', 1, '080-2233-6785','d-watanabe@g.com', 'password', '2021-02-01', '2020-02-01', '2020-02-01');

INSERT INTO departments (
	department_id,
	department_name
) VALUES
	(1, '営業一課'),
	(2, '営業二課'),
	(3, '営業三課'),
	(4, '営業四課');
	
INSERT INTO members (
	id,
	employee_id,
	department_id,
	sales,
	district_in_charge,
	customers,
	updated_at
) VALUES
	(1, 1 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '新宿', FLOOR(1 + RAND() * 20), '2000-04-01' ),
	(2, 2 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '池袋', FLOOR(1 + RAND() * 20), '2000-04-01' ),
	(3, 3 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '蒲田', FLOOR(1 + RAND() * 20), '2022-02-01' ),
	(4, 4 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '渋谷', FLOOR(1 + RAND() * 20), '2001-03-01' ),
	(5, 5 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '原宿', FLOOR(1 + RAND() * 20), '2022-03-01' ),
	(6, 6 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '川崎', FLOOR(1 + RAND() * 20), '2022-03-01' ),
	(7, 7 , FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '練馬', FLOOR(1 + RAND() * 20), '2021-06-01' ),
	(8, 8 ,FLOOR(1 + RAND() * 4), FLOOR(1 + RAND() * 1000000), '大久保', FLOOR(1 + RAND() * 20), '2020-02-01' );
