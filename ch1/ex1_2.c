#include <stdio.h>
#include <string.h>

void swap(char* s, int i, int j) {
	char c = s[i];
	s[i] = s[j];
	s[j] = c;
	return;
}

void reverse(char* str) {
	int i = 0, j = strlen(str) - 1;
	while (j > i) swap(str, i++, j--);
	return;
}


int main() {

	char s[] = "hello";

	reverse(s);

	printf("%s\n", s);


	return 0;
}

