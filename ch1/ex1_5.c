#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// uses standard library to convert count to string
// uses too much space
// CORRECTNESS: doesn't choose which string is shorter.
char* compress(char* s) {
	int i, j, count = 1, len = strlen(s);
	char* s2 = (char*) malloc(2 * len * sizeof(char) + 1);
	
	
	for (i = 1, j = 0; i <= len; i++) {
		if (s[i] != s[i - 1]) {
			s2[j++] = s[i - 1];
			sprintf(s2 + j, "%d", count);
			while (*(s2 + j) != '\0') j++;
			count = 1;
		}
		else
			count++;
	}
	s2[j] = '\0';

	// s2 = realloc(); // cut down to size
	return s2;
}


int main() {
	char* s = compress("hello");
	printf("%s\n", s);
}

