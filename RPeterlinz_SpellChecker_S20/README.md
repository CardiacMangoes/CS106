# Spell Checker

Spell Checker that uses a complete, balanced binary tree to store words and give suggestions.

A complete, balanced binary tree is achieved using this equation I developed by analyzing the relationship between the size of a sorted list and the index that would be its root in a complete balanced binary tree

x is the size of the sorted array, r is the index of the root starting at 0

![equation](Recursive Algorithm.pdf)

Using this equation, I can set the root as a pivot and recursively apply the same equation to the left and right sides of the array until there is one or zero values left in the array

[Here's an interactive desmos for the equations](https://www.desmos.com/calculator/bqx7qgdpkf)


For the implementation of this algorithm into the SpellChecker, check lines 79-113 of the [Dictionary Class](https://github.com/CardiacMangoes/CS106/blob/master/RPeterlinz_SpellChecker_S20/src/spellchecker/BasicDictionary.java)
