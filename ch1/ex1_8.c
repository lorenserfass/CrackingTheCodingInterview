#include <stdio.h>
#include <string.h>

int isSubstring(char* needle, char* haystack) {
	return strstr(haystack, needle) != NULL;
}

int isRotation(char* s1, char* s2) {
	// TODO: make a string that is s2 concatenated with itself
	// then test whether s1 is a substring of s2s2

	

	if (strlen(s1) != strlen(s2))
		return false;

	
}


int main() {

	return 0;
}

