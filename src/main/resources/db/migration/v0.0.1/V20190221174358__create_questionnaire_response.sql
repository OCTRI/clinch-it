-- Create table for entity QuestionnaireResponse
DROP TABLE IF EXISTS `questionnaire_response`;
CREATE TABLE `questionnaire_response` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`patient` bigint(20) NOT NULL,
	`sdh_domain_questionnaire` bigint(20) NOT NULL,
	`answer_json` TEXT NOT NULL,
	`wants_help` bit(1) NOT NULL DEFAULT 0,
	`comments` TEXT,
	primary key(`id`),
	CONSTRAINT questionnaire_response_patient_fk FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
	CONSTRAINT questionnaire_response_sdh_domain_questionnaire_fk FOREIGN KEY (`sdh_domain_questionnaire`) REFERENCES `sdh_domain_questionnaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

