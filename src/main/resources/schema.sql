
CREATE TABLE IF NOT EXISTS `person` (
  `person_uuid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthdate` date NOT NULL,
  `created` int NOT NULL,
  `modified` int NOT NULL,
  UNIQUE KEY `person_uuid` (`person_uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS relations (
  person_uuid  varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  relative  varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  relation_type  varchar(100) COLLATE utf8_unicode_ci NOT NULL
)