#!/usr/bin/env python
def isSubstring(sub, string):
	return sub in string

def isRotation(s1, s2):
	return s1 in s2 + s2

print(isRotation("hello", "lohel"))



