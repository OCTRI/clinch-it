-- Create table for entity SdhDomainQuestionnaire
DROP TABLE IF EXISTS `sdh_domain_questionnaire`;
CREATE TABLE `sdh_domain_questionnaire` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`version` int(11) NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`sdh_domain` bigint(20) NOT NULL,
	`question_json` TEXT NOT NULL,
	primary key(`id`),
	CONSTRAINT sdh_domain_questionnaire_sdh_domain_fk FOREIGN KEY (`sdh_domain`) REFERENCES `sdh_domain` (`id`),
	UNIQUE(sdh_domain)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sdh_domain_questionnaire` (`id`, `version`, `created_at`, `updated_at`, `sdh_domain`, `question_json`)
VALUES
	(1, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 1, '{"questions": [{"number": 1,"text": "In the past year, have you or any family members you live with been unable to get/afford food when it was really needed?","answers": ["Yes", "No", "I choose not to answer this question"]}]}'),
	(2, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 2, '{"questions": [{"number": 1,"text": "Has lack of transportation kept you from medical appointments, meetings, work, or from getting things needed for daily living? Check all that apply.","answers": ["Yes, it has kept me from medical appointments or from getting my medications.", "Yes, it has kept me from non-medical meetings, appointments, work, or from getting things that I need.", "No", "I choose not to answer this question"]}]}'),
	(3, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 3, '{"questions": [{"number": 1,"text": "What is your housing situation today?","answers":["I have housing", "I do not have housing (staying with others, in a hotel, in a shelter, living outside on the street, on a beach, in a car, or in a park)", "I choose not to answer this question"]}, {"number": 2,"text": "Are you worried about losing your housing?","answers":["Yes", "No", "I choose not to answer this question"]}]}'),
	(4, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 4, '{"questions": [{"number": 1,"text": "In the past year, have you or any family members you live with been unable to get/afford any housing utilities (electricity, gas, etc) when it was really needed?": ["Yes", "No", "I choose not to answer this question"]}]}'),
	(5, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 5, '{"questions": [{"number": 1,"text": "Do you feel physically and emotionally safe where you currently live?", "answers": ["Yes", "No", "Unsure", "I choose not to answer this question"]},{"number": 2,"text": "In the past year, have you been afraid of your partner or ex-partner?", "answers": ["Yes", "No", "Unsure", "I have not had a partner in the past year", "I choose not to answer this question"]}]}'),
	(6, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 6, '{"questions": [{"number": 1,"text": "Behavior","answers":["0","1"]},{"number": 2,"text": "Exercise","answers":["0","1"]}, {"number": 3,"text": "Menopause","answers":["0","1"]}, {"number": 4,"text": "Rectal","answers":["0","1"]}, {"number": 5,"text": "Antibiotics","answers":["0","1"]}, {"number": 6,"text": "Anemia","answers":["0","1"]}, {"number": 7,"text": "Jaundice","answers":["0","1"]}]}'),
	(7, 0, '2019-02-21 17:54:15', '2019-02-21 17:54:15', 7, '{"questions": [{"number": 1,"text": "What is your current work situation?","answers":["Unemployed", "Part-time or temporary work", "Full-time work", "Otherwise unemployed but not seeking work (ex: student, retired, disabled, unpaid primary care giver)", "I choose not to answer this question"]}, {"number": 2,"text": "What is your main insurance?","answers":["None/uninsured", "Medicaid", "CHIP Medicaid", "Medicare", "Other public insurance (not CHIP)","Other Public Insurance (CHIP)", "Private Insurance"]}, {"number": 3,"text": "During the past year, what was the total combined income for you and the family members you live with? This information will help us determine if you are eligible for any benefits","answers":["Under $20,000", "Between $20,000 and $40,000", "Between $40,000 and $60,000", "Over $60,000", "I choose not to answer this question"]}]}');
	

