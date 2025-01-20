USE edu;

CREATE OR REPLACE TABLE `user` (
	`no` 		INT 			NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`email`		VARCHAR(50) 	NOT NULL,
	`pwd`		VARCHAR(100) 	NOT NULL,
	`name` 		VARCHAR(20)		NOT NULL,
	`phone`		VARCHAR(50)		NULL,
	`delYn`		BOOLEAN 		NOT NULL DEFAULT 0,
	`regDate`	DATETIME		NOT NULL DEFAULT current_timestamp(),
	UNIQUE INDEX `idx_email` (`email`) USING BTREE
);

CREATE OR REPLACE TABLE `role` (
	`no` 	INT 		NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` 	VARCHAR(20)	NOT NULL
);

CREATE OR REPLACE TABLE `user_role` (
	`userNo`	INT NOT NULL,
	`roleNo`	INT NOT NULL,
	INDEX `FK_user_role_user` (`userNo`) USING BTREE,
	INDEX `FK_user_role_role` (`roleNo`) USING BTREE,
	CONSTRAINT `FK_user_role_role` FOREIGN KEY (`roleNo`) REFERENCES `role` (`no`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_user_role_user` FOREIGN KEY (`userNo`) REFERENCES `user` (`no`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO `role` (`name`) VALUES ('ADMIN'),('DEV'),('USER');

COMMIT;
