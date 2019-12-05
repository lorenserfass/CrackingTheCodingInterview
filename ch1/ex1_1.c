#include <stdio.h>


int hasUniqueCharacters(char* s) {
	int i;
	char counts[256];
	for (i = 0; i < 256; i++) {
		counts[i] = 0;
	}
	for (i = 0; s[i] != '\0'; i++) {
		counts[s[i]]++;
		// return false on first duplicate
		if (counts[s[i]] > 1)
			return 0;
	}

	return 1;
}


int main() {
	printf("%d\n", hasUniqueCharacters("hello"));
	printf("%d\n", hasUniqueCharacters("helbo"));

	return 0;
}
