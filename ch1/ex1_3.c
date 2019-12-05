#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int compar(const void *a, const void *b) {
	if (*(char*)a < *(char*)b)
		return -1;
	if (*(char*)a > *(char*)b)
		return 1;
	return 0;
}

int isPermutation(char* s1, char* s2) {
	int l1 = strlen(s1), l2 = strlen(s2);
	if (l1 != l2)
		return 0;
	char s1cp[l1 + 1], s2cp[l2 + 1];
	strcpy(s1cp, s1);
	strcpy(s2cp, s2);
	qsort(s1cp, l1, 1, compar);
	qsort(s2cp, l2, 1, compar);
	return strcmp(s1cp, s2cp) == 0;
}

int main() {

	printf("%d\n", isPermutation("hello", "there"));
	printf("%d\n", isPermutation("hello", "lloeh"));


	return 0;
}

