/**
 * Author: Claude Abounegm
 */

#include "../Shadow.h"
#include <Windows.h>

static char newLine[2] = { '\r', '\n' };

void __ShadowConsole_Initialize(void)
{
	SetConsoleCP(65001);
	SetConsoleOutputCP(65001);
}

void __ShadowConsole_ReadByte(ShadowByte* value, ShadowBoolean* eof)
{
	DWORD bytesRead = 0;
	HANDLE handle = GetStdHandle(STD_INPUT_HANDLE);
	ReadFile(handle, value, 1, &bytesRead, NULL);
	
	*eof = 0;
	if(bytesRead == 0) {
		*value = 0;
		*eof = 1;
	}
}

static void printString(int handleId, ShadowString string)
{
	HANDLE handle = GetStdHandle(handleId);
	
	String str;
	__getDataFromShadowString(string, &str);
	
	DWORD bytesWritten = 0;
	WriteFile(handle, str.chars, str.size, &bytesWritten, NULL);
}

void __ShadowConsole_Print(ShadowString string)
{
	printString(STD_OUTPUT_HANDLE, string);
}

void __ShadowConsole_PrintError(ShadowString string)
{
	printString(STD_ERROR_HANDLE, string);
}

static void printLine(int handleId)
{
	HANDLE handle = GetStdHandle(handleId);

	DWORD bytesWritten = 0;
	WriteFile(handle, newLine, sizeof(newLine), &bytesWritten, NULL);
}

void __ShadowConsole_PrintLine(ShadowString string)
{
	printLine(STD_OUTPUT_HANDLE);
}

void __ShadowConsole_PrintErrorLine(ShadowString string)
{
	printLine(STD_ERROR_HANDLE);
}