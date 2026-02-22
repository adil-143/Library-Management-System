javac -cp ".;mysql-connector-j-9.6.0.jar" src\db\*.java src\dao\*.java src\ui\*.java src\Main.java
java -cp ".;mysql-connector-j-9.6.0.jar;src" Main
pause