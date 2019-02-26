-- Create table for entity Patient
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`name` varchar(255) DEFAULT NULL,
	`date_of_birth` date DEFAULT NULL,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


