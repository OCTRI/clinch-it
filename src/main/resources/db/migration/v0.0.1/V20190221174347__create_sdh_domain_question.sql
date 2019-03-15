-- Create table for entity SdhDomainQuestion
DROP TABLE IF EXISTS `sdh_domain_question`;
CREATE TABLE `sdh_domain_question` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`sdh_domain` bigint(20) NOT NULL,
	`question_number` int(11) DEFAULT NULL,
	`question_text` varchar(255) DEFAULT NULL,
	primary key(`id`),
	CONSTRAINT sdh_domain_question_sdh_domain_fk FOREIGN KEY (`sdh_domain`) REFERENCES `sdh_domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sdh_domain_question` (`id`, `version`, `created_at`, `updated_at`, `sdh_domain`, `question_number`, `question_text`)
VALUES
	(1, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 1, 1, 'In the last 12 months, were you worried that your food would run out before you got money to buy more?'),
	(2, 0, '2019-02-21 17:54:55', '2019-02-21 17:54:55', 1, 2, 'In the last 12 months, did you ever eat less than you felt you should because there wasn’t enough money for food?'),
	(3, 0, '2019-02-21 17:55:28', '2019-02-21 17:55:28', 1, 3, 'In the last 12 months, were you every hungry but didn’t eat because there wasn’t enough money for food?'),
	(4, 0, '2019-02-21 17:56:01', '2019-02-21 17:56:01', 1, 4, 'In the last 12 months, since last (name of current month), did (you/you or other adults in your household) ever cut the size of your meals or skip meals because there wasn’t enough money for food?');


