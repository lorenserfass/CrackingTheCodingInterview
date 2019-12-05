#include <stdio.h>
#include <stdlib.h>

// linear time
// in place
// constant extra space
void changeSpaces(char* s, int trueLength) {
	int i, j, k, numSpaces = 0;

	for (i = 0; i < trueLength; i++)
		if (s[i] == ' ')
			numSpaces++;

	j = trueLength - 1;
	k = trueLength - 1 + 2 * numSpaces;
	for (i = numSpaces - 1; i >= 0; i--) {
		// move everything after space #i. Have to move backwards through the array to avoid overwriting stuff.
		while (s[j] != ' ')
			s[k--] = s[j--];
		s[k--] = '0';
		s[k--] = '2';
		s[k--] = '%';
		j--;
	}

	return;
}


int main() {

	char s[] = "  hello  there mr.             ";

	changeSpaces(s, 19);

	printf("%s\n", s);

	return 0;
}

