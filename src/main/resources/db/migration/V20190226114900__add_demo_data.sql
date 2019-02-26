-- Add 3 sample patients
INSERT INTO `patient` (`id`, `version`, `created_at`, `updated_at`, `name`, `date_of_birth`)
VALUES
	(1, 0, '2019-02-21 15:25:14', '2019-02-21 15:25:14', 'Peter Gibbons', '1968-03-02'),
	(2, 0, '2019-02-21 15:27:03', '2019-02-21 15:27:03', 'Milton Waddams', '1954-12-14'),
	(3, 0, '2019-02-21 15:27:39', '2019-02-21 15:27:39', 'Bob Slydell', '1967-04-17');
