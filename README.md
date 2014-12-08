Mapreduce
=========

Friend recommand
Description
One common feature in Social Network site is to recommend people connection. e.g. "People you may know" from Linkedin. The basic idea is, if person A and person B doesn't know each other but they have common friends, then the system would recommend person B to person A and vice versa.
An assumption is friends are bi-directional. If A is friend of B, then B is a friend A.
In this Lab we will implement a MapReduce Java Program to find common friends of every pair of users.
For example, we have five users here: A, B, C, D, E. Their friends lists are stored as Person:[List of Friends], like this
A: B C D B: A C D E C: A B D E D: A B C E E: B C D
All pairs of users are AB, AC, AD, AE, BC, BD, BE, CD, CE, DE. The common friends of AB is C and D. The common friends of AC is B and D.
There will be more than one way to implement this. One method is explained as follows.
Each line of the input will be an argument to a mapper. For example, the first line of the input is
￼￼
“A: B C D”. For every friend in the list of friends, (e.g. B C D), the mapper will output key-value pairs. The key will be the person and a member of the friend list. In the example, the key would be
“AB”, “AC”, “AD” respectively, the value for each key would be the same for all the three keys generated for the first line of input. The value is person A's friend list. (“B C D”)
The key will be sorted by hadoop during the sort-merge phase, so that the friends are in order and all pairs of friends go to the same reducer.
After all the mappers are done running, you'll have a list like this: For map(A -> B C D) :
(A B) -> B C D (A C) -> B C D (A D) -> B C D
For map(B -> A C D E) : (Note that A comes before B in the key)
(A B) -> A C D E (B C) -> A C D E (B D) -> A C D E (B E) -> A C D E
For map(C -> A B D E) :
(A C) -> A B D E (B C) -> A B D E (C D) -> A B D E (C E) -> A B D E
For map(D -> A B C E) :
(A D) -> A B C E (B D) -> A B C E (C D) -> A B C E (D E) -> A B C E
And finally for map(E -> B C D):
(B E) -> B C D (C E) -> B C D (D E) -> B C D
Before hadoop send these key-value pairs to the reducers, hadoop group them by their keys and get:
(A B) -> (A C D E) (B C D) (A C) -> (A B D E) (B C D)
(A D) -> (A B C E) (B C D) (B C) -> (A B D E) (A C D E) (B D) -> (A B C E) (A C D E) (B E) -> (A C D E) (B C D) (C D) -> (A B C E) (A B D E) (C E) -> (A B D E) (B C D) (D E) -> (A B C E) (B C D)
Each line will be passed as an argument to a reducer. The reduce function will simply intersect the lists of values and output the same key with the result of the intersection. For example, reduce((A B) -> (A C D E) (B C D)) will output (A B) : (C D) and means that friends A and B have C and D as common friends.
The result after reduction is:
(A B) -> (C D) (A C) -> (B D) (A D) -> (B C) (B C) -> (A D E) (B D) -> (A C E) (B E) -> (C D) (C D) -> (A B E) (C E) -> (B D) (D E) -> (B C)
Now when D visits B's profile, we can quickly look up (B D) from the result set and see that they have three friends in common, (A C E).
