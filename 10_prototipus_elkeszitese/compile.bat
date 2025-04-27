cd Fungrorium
rmdir /s /q out
mkdir out
javac -cp .;junit-jupiter-api-5.8.1.jar;junit-jupiter-engine-5.8.1.jar -d out *.java
pause