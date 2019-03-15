-- Create table for entity ClinicianPriority
DROP TABLE IF EXISTS `clinician_priority`;
CREATE TABLE `clinician_priority` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`description` varchar(255) DEFAULT NULL,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `clinician_priority` (`version`, `created_at`, `updated_at`, `description`) VALUES (0, NOW(), NOW(), 'High'), (0, NOW(), NOW(), 'Medium'), (0, NOW(), NOW(), 'Low');
