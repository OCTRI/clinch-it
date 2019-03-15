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
INSERT INTO `sdh_domain` (`version`, `created_at`, `updated_at`, `description`) VALUES (0, NOW(), NOW(), 'Food Security'), (0, NOW(), NOW(), 'Transportation'), (0, NOW(), NOW(), 'Housing Instability'), (0, NOW(), NOW(), 'Utilities'), (0, NOW(), NOW(), 'Interpersonal Violence'), (0, NOW(), NOW(), 'Education'), (0, NOW(), NOW(), 'Financial Strain');
