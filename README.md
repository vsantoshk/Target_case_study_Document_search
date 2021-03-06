 <span style="font-family: 'Cambria';background:#00FF00">
 
 ## **Document Search**

#### Problem Statement:
The goal of this exercise is to create a working program to search a set of documents for the given search term or phrase (single token), and return results in order of relevance. 
Relevancy is defined as number of times the exact term or phrase appears in the document. 
Create three methods for searching the documents: 
•	Simple string matching
•	Text search using regular expressions
•	Preprocess the content and then search the index

#### Input:
Prompt the user to enter a search term and search method, execute the search, and return results. For instance:

Enter the search term:<user enter search term
Search Method: 1) String Match 2.)Regular Expressions 3.)Indexed

#### Output:

1. File2.txt - X matches
2. File1.txt - X matches
3. File3.txt - X matches
4. Elapsed time: 40ms

#### System Requirements:
1. JDK Installation
2. Make sure to have the gradle plugin installed in Intellij

#### How to run:
1. Git clone 	https://github.com/vsantoshk/Target_case_study_Document_search.git
2. Run the Application.


#### Conceptualization:
1. The logic of the code is to search the files with three different methods and also elapsed time for each method.
2. This code is written using java concepts of Inheritance and HashMap.
3. Run the performance test for 2M searches with random search terms
   1. Results: Indexed method is very fast in searching the file consists of ~2M words/searches and elapsed time for this is 0ms.


