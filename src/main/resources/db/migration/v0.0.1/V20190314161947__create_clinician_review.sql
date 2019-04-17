-- Create table for entity ClinicianReview
DROP TABLE IF EXISTS `clinician_review`;
CREATE TABLE `clinician_review` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`patient` bigint(20) NOT NULL,
	`domain` bigint(20) NOT NULL,
	`clinician_priority` bigint(20),
	`patient_readiness` bigint(20),
	`referred` bit(1) NOT NULL DEFAULT 0,
	`referral_complete` bit(1) NOT NULL DEFAULT 0,
	`flagged` bit(1) NOT NULL DEFAULT 0,
	primary key(`id`),
	CONSTRAINT clinician_review_patient_fk FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
	CONSTRAINT clinician_review_domain_fk FOREIGN KEY (`domain`) REFERENCES `sdh_domain` (`id`),
	CONSTRAINT clinician_review_clinician_priority_fk FOREIGN KEY (`clinician_priority`) REFERENCES `clinician_priority` (`id`),
	CONSTRAINT clinician_review_patient_readiness_fk FOREIGN KEY (`patient_readiness`) REFERENCES `patient_readiness` (`id`),
	UNIQUE(patient,domain)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

