-- Create table for entity PatientReadiness
DROP TABLE IF EXISTS `patient_readiness`;
CREATE TABLE `patient_readiness` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`description` varchar(255) DEFAULT NULL,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `patient_readiness` (`version`, `created_at`, `updated_at`, `description`) VALUES (0, NOW(), NOW(), 'Not Ready'), (0, NOW(), NOW(), 'Motivated'), (0, NOW(), NOW(), 'Ready');
