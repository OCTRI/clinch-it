-- Create table for entity PatientDomainAssessment
DROP TABLE IF EXISTS `patient_domain_assessment`;
CREATE TABLE `patient_domain_assessment` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`patient` bigint(20) NOT NULL,
	`domain` bigint(20) NOT NULL,
	primary key(`id`),
	CONSTRAINT patient_domain_assessment_patient_fk FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
	CONSTRAINT patient_domain_assessment_domain_fk FOREIGN KEY (`domain`) REFERENCES `sdh_domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

