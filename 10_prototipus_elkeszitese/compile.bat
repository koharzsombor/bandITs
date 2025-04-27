@echo off
rmdir /s /q out
mkdir out
javac -d out -cp junit-platform-console-standalone-1.12.2.jar Fungorium\*.java