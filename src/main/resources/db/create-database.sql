CREATE DATABASE IF NOT EXISTS `eventapp`;

CREATE USER 'eventapp-admin'@'%' IDENTIFIED BY 'admin@2019!';
GRANT ALL PRIVILEGES ON eventapp.* TO 'eventapp-admin'@'%';

CREATE USER 'eventapp-user'@'%' IDENTIFIED BY 'user@2019!';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON eventapp.* TO 'eventapp-user'@'%';


CREATE USER 'eventapp-admin'@'localhost' IDENTIFIED BY 'admin@2019!';
GRANT ALL PRIVILEGES ON eventapp.* TO 'eventapp-admin'@'localhost';

CREATE USER 'eventapp-user'@'localhost' IDENTIFIED BY 'user@2019!';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON eventapp.* TO 'eventapp-user'@'localhost';

FLUSH PRIVILEGES;
