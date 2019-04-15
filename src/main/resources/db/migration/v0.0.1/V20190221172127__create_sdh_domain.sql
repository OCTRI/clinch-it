-- Create table for entity SdhDomain
DROP TABLE IF EXISTS `sdh_domain`;
CREATE TABLE `sdh_domain` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`description` varchar(255) NOT NULL,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sdh_domain` (`id`, `version`, `created_at`, `updated_at`, `description`) VALUES (1, 0, NOW(), NOW(), 'Food Insecurity'), (2, 0, NOW(), NOW(), 'Transportation'), (3, 0, NOW(), NOW(), 'Housing Instability'), (4, 0, NOW(), NOW(), 'Utilities'), (5, 0, NOW(), NOW(), 'Interpersonal Violence'), (6, 0, NOW(), NOW(), 'Health Literacy'), (7, 0, NOW(), NOW(), 'Financial Strain');
