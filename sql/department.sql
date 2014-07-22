REATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;
