-- Add 3 sample patients
INSERT INTO `patient` (`id`, `version`, `created_at`, `updated_at`, `name`, `date_of_birth`)
VALUES
	(1, 0, '2019-02-21 15:25:14', '2019-02-21 15:25:14', 'Peter Gibbons', '1968-03-02'),
	(2, 0, '2019-02-21 15:27:03', '2019-02-21 15:27:03', 'Milton Waddams', '1954-12-14'),
	(3, 0, '2019-02-21 15:27:39', '2019-02-21 15:27:39', 'Bob Slydell', '1967-04-17');
	
-- Add clinician reviews for Patient 1
INSERT INTO `clinician_review` (`id`, `version`, `created_at`, `updated_at`, `patient`, `domain`, `clinician_priority`, `patient_readiness`)
VALUES
	(1, 0, '2019-03-14 16:21:59', '2019-02-01 00:00:00', 1, 1, 2, 1),
	(2, 0, '2019-03-15 12:08:43', '2019-01-01 00:00:00', 1, 2, 3, 2),
	(3, 0, '2019-03-15 12:09:27', '2018-10-11 00:00:00', 1, 3, 2, 3),
	(4, 0, '2019-03-15 12:09:48', '2019-02-01 00:00:00', 1, 4, 1, 3),
	(5, 0, '2019-03-15 12:11:55', '2018-12-01 00:00:00', 1, 5, 2, 2),
	(6, 0, '2019-03-15 12:12:24', '2018-04-04 00:00:00', 1, 6, 2, 1),
	(7, 0, '2019-03-15 12:12:37', '2012-03-17 00:00:00', 1, 7, 3, 3);
