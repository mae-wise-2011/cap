delimiter $$

CREATE TABLE `table_geo_position` (
  `ID` bigint(20) NOT NULL,
  `gp_latitude` double DEFAULT NULL,
  `gp_longitude` double DEFAULT NULL,
  `gp_timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

