-- Create table for entity ClinicianReview
DROP TABLE IF EXISTS `clinician_review`;
CREATE TABLE `clinician_review` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`patient` bigint(20) NOT NULL,
	`domain` bigint(20) NOT NULL,
	`clinician_priority` bigint(20) DEFAULT NULL,
	`patient_readiness` bigint(20) DEFAULT NULL,
	primary key(`id`),
	CONSTRAINT clinician_review_patient_fk FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
	CONSTRAINT clinician_review_domain_fk FOREIGN KEY (`domain`) REFERENCES `sdh_domain` (`id`),
	CONSTRAINT clinician_review_clinician_priority_fk FOREIGN KEY (`clinician_priority`) REFERENCES `clinician_priority` (`id`),
	CONSTRAINT clinician_review_patient_readiness_fk FOREIGN KEY (`patient_readiness`) REFERENCES `patient_readiness` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

