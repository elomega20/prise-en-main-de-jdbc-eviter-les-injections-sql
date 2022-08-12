# prise-en-main-de-jdbc-eviter-les-injections-sql
c'est un petit programme qui permet de verifie si une personne est oui ou non dans la base de donnees.

manipuler une base de donnees a travers un programme java a l'aide de JDBC:

C'est un petit demo pour manipuler quelque methodes de l'API JDBC.
Le fichier scrpit.sql contient le schema de la base de donnee. 
Le fichier conf.properties est le fichier de configuration de la base de donnees,
elle permet de migres d'une base(mysql , postgres , oracle) a une autre sans toucher les fichiers java.

ici on n'a utiliser la classe preparedStatement au lieu de la classe Statement pour eviter les injection sql.
