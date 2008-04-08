@if "%GWT_HOME%"=="" goto needGWTHome
@java -Xmx128M -cp "%~dp0\..\..\src;%~dp0\..\..\bin;%GWT_HOME%\gwt-user.jar;%GWT_HOME%\gwt-dev-windows.jar;..\..\..\..\gwt-google-apis.jar" com.google.gwt.dev.GWTCompiler -out "%~dp0\..\..\www" %* com.google.gwt.maps.sample.maps.HelloMaps
@exit /B %ERRORLEVEL%

:needGWTHome
@echo The environment variable GWT_HOME is not defined, it should point to a valid GWT installation.
@exit /B 1
