CREATE TABLE `contact` (
 `id` int(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) DEFAULT NULL,
 `mobile` varchar(20) DEFAULT NULL,
 `vpmn` varchar(20) DEFAULT NULL,
 `email` varchar(20) DEFAULT NULL,
 `home_address` varchar(20) DEFAULT NULL,
 `office_address` varchar(20) DEFAULT NULL,
 `memo` varchar(100) DEFAULT NULL,
 `job` varchar(40) DEFAULT NULL,
 `job_level` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 PACK_KEYS=0;
