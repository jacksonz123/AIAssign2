Author: 7671903 Jackson Zaloumis
Solution code Inference Engine for Propositional Logic - Assignment 2

=========FEATURES=========
USAGE:
	iengine <method> <filename>

iengine takes two arguments
The First refers to the method to be used (see table below)
The Second refers to the text file which contains two commands: TELL and ASK, both in Horn Form

Parameter	|Method Name
----------------+----------
TT		|Truth Table
FC		|Forward Chaining
BC		|Backward Chaining


=========KNOWN BUGS=========
* Only works with inputs in Horn Form


==========MISSING===========


=========TEST CASES=========
* test1.txt
TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
ASK
d

* test2.txt
TELL
a=>b; c=>d;
ASK
d

* test3.txt
TELL
a=>b; c & f=>d; e;
ASK
d

* test4.txt
ASK
d
TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;

* test5.txt
TELL
a=>b; a
ASK
b


===ACKNOWLEDGES/RESOURCES===
- Generating Truth Tables: Helped my understanding of initialising a TT in java
https://stackoverflow.com/questions/10723168/generating-truth-tables-in-java