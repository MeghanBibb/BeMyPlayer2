

-- Dumping database structure for BeMyPlayer2 schema
DROP DATABASE IF EXISTS `BeMyPlayer2`;
CREATE DATABASE IF NOT EXISTS `BeMyPlayer2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Relationship`;

-- initialize 'Person' table
DROP TABLE IF EXISTS `Person`;
CREATE TABLE IF NOT EXISTS `Person` (
	`LastName` varchar(25) NOT NULL,
    `FirstName` varchar(25) NOT NULL,
    `Gender` char NOT NULL,
    `CityOfBirth` varchar(25) NOT NULL,
    `StateOfBirth` varchar(25) NOT NULL,
    `CityLive` varchar(25)  DEFAULT NULL,
    `StateLive` varchar(25)  DEFAULT NULL,
    PRIMARY KEY (`LastName`,`FirstName`)
    /* Add foreign key constraints*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table university_db.time_slot: ~13 rows (approximately)
DELETE FROM `Person`;
INSERT INTO `Person` (`LastName`, `FirstName`, `Gender`, `CityOfBirth`,`StateOfBirth`,`CityLive`,`StateLive`) VALUES
	('Doe', 'John', 'M', 'Denver', 'CO', 'Waco', 'TX'),
    ('Doe', 'Jane', 'F', 'San Francisco', 'CA', 'Waco', 'TX'),
    ('Doe', 'Brad', 'M', 'Austin', 'TX', 'Oklahoma City', 'OK'),
    ('Doe', 'Kenny', 'M', 'Waco', 'TX', 'Waco', 'TX'),
    ('Doe', 'Cookie', 'F', 'Austin', 'TX', 'Waco', 'TX'),
    ('Doe', 'Sue', 'F', 'Waco', 'TX', 'Waco', 'TX'),
    ('Rei', 'Doe', 'F', 'Boston', 'MA', 'Oklahoma City', 'OK'),
    ('Jack', 'Weir', 'M', 'Portland', 'OR', 'Waco', 'TX');
    
-- initialize 'BasicRelationship' table
DROP TABLE IF EXISTS `BasicRelationship`;
CREATE TABLE IF NOT EXISTS `BasicRelationship` (
	`LastName1` varchar(25),
    `FirstName1` varchar(25),
    `LastName2` varchar(25),
    `FirstName2` varchar(25),
    `BasicRelationship` char,
    PRIMARY KEY (`LastName1`,`FirstName1`,`LastName2`,`FirstName2`)
	/* Add foreign key constraints*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- initialize `Relationship` table
DROP TABLE IF EXISTS `Relationship`;
CREATE TABLE IF NOT EXISTS `Relationship` (
	`RelationshipName` varchar(25),
    `RelationCount` int,
    `AgeDiff` char,
    PRIMARY KEY (`RelationshipName`)
	/* Add foreign key constraints*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- initialize `RSteps` table
DROP TABLE IF EXISTS `RSteps`;
CREATE TABLE IF NOT EXISTS `RSteps` (
	`RelationshipName` varchar(25),
    `Step` int,
    `BasicRelationship` char,
    PRIMARY KEY (`RelationshipName`,`Step`)
	/* Add foreign key constraints*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;