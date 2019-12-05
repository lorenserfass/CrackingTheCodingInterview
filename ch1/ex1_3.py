#!/usr/bin/env python

def isPermutation(s1, s2):
	return ''.join(sorted(s1)) == ''.join(sorted(s2))


print(isPermutation("hello there", " eethroehll"))


