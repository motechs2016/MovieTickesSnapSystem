DELIMITER $$

USE `movie_snop`$$

DROP PROCEDURE IF EXISTS `snop_proc`$$

CREATE DEFINER=`cen`@`%` PROCEDURE `snop_proc`(IN v_uid INT,IN v_movie_id INT,IN v_num INT,OUT r_result INT)
BEGIN
	DECLARE change_count INT DEFAULT 0;
	START TRANSACTION ;
	INSERT IGNORE INTO SNAP_RECORD(UID,MOVIE_ID,NUM,SNAP_TIME) VALUES(v_uid,v_movie_id,v_num,NOW());
	SELECT ROW_COUNT() INTO change_count;
	IF(change_count=0) THEN
		ROLLBACK;
		SET r_result=-1;
	ELSEIF(change_count<0) THEN
		SET r_result=-2;
	ELSE
		UPDATE MOVIE_TICKES SET NUM=NUM-v_num WHERE ID=v_movie_id AND NUM>=v_num AND START_TIME<NOW() AND END_TIME>NOW();
		SELECT ROW_COUNT() INTO change_count;
		IF(change_count=0) THEN
			ROLLBACK;
			SET r_result=-3;
		ELSEIF(change_count<0) THEN
			SET r_result=-2;
		ELSE
			COMMIT;
			SET r_result=1;
		END IF;
	END IF;
    END$$

DELIMITER ;