# Backend Dev Task - Solution

## Task Description

The following repository provides the solution to the following interactive Java program with two features:

- Feature #1 (isAnagram), checks if two texts are anagrams of each other.
- Feature #2 (allMappedAnagrams), out of all inputs from feature #1 provides all the anagrams for a given string.

## Definition

Based on the features, the following definition and assumptions are used to develop the solution:

### Anagram

Word or phrase formed by rearranging the letters of a diffferent word or phrase. Ref: https://en.wikipedia.org/wiki/Anagram.

Based on this reference, we define this assumption:

- Word or phrase is a string input which could contains characters and numbers as well as spaces, comma and so on.
  - "New York Times"
  - "rich-chosen goofy cult"
  - "Satan; cruel, less shy"
  - "restful"

- One phrase or word is anagram to another based only in the letters, IGNORING numbers, space, punctuation, capitalization and others.

## Requirements

The proposed solution requires the following Software requirements:

- Java 8+ . You can install the lastes version of [Java](https://www.java.com/en/download/)

## Run

In order to run the program follow the next steps:

1. Open a terminal where this repository is located
2. Execute the following instruction:
    ```
    java .\src\main\java\AnagramProgram.java
    ```
3. The program will display 3 options to performs
    * Option A performs the feature #1
    * Option B performs the feature #2
    * Option C exits the program
    * Any other inputs will be considered as invalid and the program will request again a proper selection.

4. Option A - Feature #1 will require the inputs of two strings and it will return message that both are anagrams in case the inputs are anagrams each other.
5. Option B - Feature #2 will require the input of one string* and it will return the collection of string that are anagrams based on the previous inputs of feature #1


Remember you can choose between the options multiple times and the results will keep until the program ends (option C). For instance, the next sequence:

Option A (niels)
Option A (n i e l s)
Option B (niels) --> [ n i e l s ]
Option A (lsnie)
Option B (niels) --> [ n i e l s , lsnie ]
