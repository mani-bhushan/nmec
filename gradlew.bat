@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar


@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%GRADLE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega









import os
import re

def reverse_read_lines(filepath, encoding='utf-8'):
    with open(filepath, 'rb') as f:
        f.seek(0, os.SEEK_END)
        position = f.tell()
        line = bytearray()
        
        while position > 0:
            position -= 1
            f.seek(position)
            byte = f.read(1)
            
            if byte == b'\n':
                if line:
                    yield line[::-1].decode(encoding, errors='ignore')
                    line = bytearray()
            else:
                line.append(byte[0])

        if line:
            yield line[::-1].decode(encoding, errors='ignore')

def get_replacement_array_from_tsv(tsv_path):
    array_values = []
    with open(tsv_path, 'r', encoding='utf-8') as tsv:
        for line in tsv:
            parts = line.strip().split('\t')
            if len(parts) >= 2:
                array_values.append(parts[1].strip())
    return array_values

def build_array_block(indent, values):
    inner = ',\n'.join([f'{indent}"{v}"' for v in values])
    return f'[\n{inner}\n]'

def replace_multiline_array_bottom_up(file_path, tsv_path, key='"abc.key"'):
    replacement_values = get_replacement_array_from_tsv(tsv_path)
    
    lines = []
    buffer = []
    collecting = False
    modified = False

    for line in reverse_read_lines(file_path):
        stripped = line.strip()

        if not modified:
            if not collecting and stripped.startswith(']'):
                # Start collecting the multiline array block from the end
                collecting = True
                buffer.append(line)
                continue

            if collecting:
                buffer.append(line)
                if key in line:
                    # Found the start of the array block
                    collecting = False
                    modified = True

                    # Determine the base indent
                    indent_match = re.match(r'^(\s*)', line)
                    indent = indent_match.group(1) if indent_match else ""

                    # Build replacement block
                    new_array_block = build_array_block(indent + "  ", replacement_values)
                    replacement_line = re.sub(
                        r'("abc\.key"\s*:\s*)
