rem Extract and install Sector Engine and its required packages

title Sector Engine Setup
echo Installing Sector Engine and 3 other packages

rem Install Eclipse, Jython, Java, and SectorEngine
echo Installing packages/jython-installer-2.5.3.jar
start "" "%CD%\packages\jython-installer-2.5.3.jar"

echo Detecting system architecture...
IF "%PROCESSOR_ARCHITECTURE%"=="x86" (GOTO x86) else (GOTO x64)

:x86
echo Detected x86 architecture.
echo Installing packages/jre-8u31-windows-i586-iftw.exe
start "" "%CD%/packages\jre-8u31-windows-i586-iftw.exe"

:x64
echo Detected x64 architecture.
echo Installing packages/jre-8u31-windows-x64.exe
start "" "%CD%\packages\jre-8u31-windows-x64.exe"

pause