@echo off
set root="D:\nikwo\Documents\db_homework"
cd root
START "" D:\Install\jdk11\bin\javaw.exe --module-path %~dp0\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.media -Dfile.encoding=windows-1251 -jar %~dp0\out\artifacts\LiteMP3\db_homework.jar
exit