INSERT INTO employees (
employee_id,
employee_name,
gender,
employee_phone,
employee_email,
password,
date_of_entry,
start_date,
end_date,
sales,
district_in_charge,
customers,
updated_at,
department_id
) VALUES
(1,'foobar', 1, '080-1234-5678','foobar@a.com', 'password', '2000-04-01', '2000-04-01', '2000-04-01'
	,FLOOR(1 + RAND() * 1000000), '新宿', FLOOR(1 + RAND() * 20), '2000-04-01', FLOOR(1 + RAND() * 4)),
(2,'john Doe', 1, '090-1234-5678','johndoe@b.com', 'password', '2000-04-01', '2000-04-01', '2000-04-01'
	,FLOOR(1 + RAND() * 1000000), '渋谷', FLOOR(1 + RAND() * 20), '2000-04-01',  FLOOR(1 + RAND() * 4)),
(3,'hoge', 2, '080-5678-1234','hoge@a.com', 'password', '2022-02-01', '2022-02-01', '2022-02-01'
	,FLOOR(1 + RAND() * 1000000), '蒲田', FLOOR(1 + RAND() * 20), '2022-02-01', FLOOR(1 + RAND() * 4)),
(4,'hogehoge', 2, '090-5678-1234','hogehoge@a.com', 'password', '2001-03-01', '2001-03-01', '2001-03-01'
	,FLOOR(1 + RAND() * 1000000), '池袋', FLOOR(1 + RAND() * 20), '2001-03-01', FLOOR(1 + RAND() * 4));

INSERT INTO departments (
	department_id,
	department_name
) VALUES
	(1, '営業一課'),
	(2, '営業二課'),
	(3, '営業三課'),
	(4, '営業四課');
