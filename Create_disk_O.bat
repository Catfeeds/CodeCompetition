@echo off

set newDrive=O:

if exist %newDrive% (
	subst %newDrive% /d 
) else (
	subst %newDrive% %cd%
)

::pause