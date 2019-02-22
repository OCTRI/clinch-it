-- Create table for entity PatientDomainAssessment
DROP TABLE IF EXISTS `patient_domain_assessment`;
CREATE TABLE `patient_domain_assessment` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`patient` bigint(20) NOT NULL,
	`domain` bigint(20) NOT NULL,
	`clinician_priority` bigint(20) DEFAULT NULL,
	`patient_readiness` bigint(20) DEFAULT NULL,
	`clinician_notes` TEXT DEFAULT NULL,
	primary key(`id`),
	CONSTRAINT patient_domain_assessment_patient_fk FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
	CONSTRAINT patient_domain_assessment_domain_fk FOREIGN KEY (`domain`) REFERENCES `sdh_domain` (`id`),
	CONSTRAINT patient_domain_assessment_clinician_priority_fk FOREIGN KEY (`clinician_priority`) REFERENCES `clinician_priority` (`id`),
	CONSTRAINT patient_domain_assessment_patient_readiness_fk FOREIGN KEY (`patient_readiness`) REFERENCES `patient_readiness` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

