/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  alberto.casanova
 * Created: 13-may-2021
 */

CREATE TABLE `student` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `firstname` varchar(30) NOT NULL,
    `lastname` varchar(30) NOT NULL,
    `email` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;