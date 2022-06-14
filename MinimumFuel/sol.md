

No official link is present, so I can't add that. One is there on online judge, but I was unable 
to submit it there due to some unknown runtime error. 
```
The OA consisted of 3 coding questions, out of which 2 were LC medium & 1 was hard, which I couldn't solve. Any idea on how to solve it?
Also, it would be really helpful, if someone would share the resources/links of variations of 
this question.

The question is as below :

Bob is living in Oiland, which has N cities (numbered from 1 to N) and M roads connecting these cities. He wants to visit city B to meet Alice, from city A, where he lives. It is a long trip, and Bob will need to stop at multiple fuel stations to re-fuel his car. The price of fuel is different at different fuel stations, and Bob is ready to take detours to visit a station which charges a lower price. Bob has a map which has the N cities and S fuel stations marked on it. From the map, he knows how much fuel (in litres) is needed to travel between any two cities and the price per litre of fuel at each station. As the capacity of the car’s fuel tank is only C litres, he has to carefully plan his optimal path to minimize his fuel expenditure.

Write a program to help Bob know what is the minimum amount he will need to spend on fuel for the entire trip, if he starts with an empty tank and there is a fuel station at A.

Read the input from STDIN and print the output to STDOUT. Do not write arbitrary strings anywhere in the program, as these contribute to the standard output and test cases will fail.

Constraints:
I) 2 <= N cities <= 1000
II) 1 <= M connecting roads <= 10000
III) 1 <= S fuel stations <= 120
IV) 1 <= C fuel tank capacity <= 100000
V) 1 <= G fuel needed to travel between any two cities <= 100000
VI) 1 <= P price of fuel <= 100

Input Format:
The first line of input contains three integers, N, M and S each separated by a single white space, where N is the number of cities, M is the number of roads between these cities and S is the number of fuel stations.
Next line has an integer C, capacity of the car’s fuel tank in litres.
Next M lines have 3 integers X, Y and G each separated by single white space, indicating that there is a bidirectional road between cities X and Y and G litres of fuel is needed to travel between X and Y.
Next S lines have 2 integers Q and P each separated by a single white space, where Q is the city that has a fuel station and P is the price per litre of fuel at that station.
Next line has two integers A and B each separated by a single white space, where A is the starting city and B is the final destination.

Output Format:
The output contains an integer, which is the minimum amount Bob has to spend on fuel to travel between A and B.

Sample Input1:
3 3 2
2000
1 3 800
1 2 500
2 3 500
1 70
2 40
1 3

Sample Output 1:
55000

Explanation 1:
N=3, M=3, S=2.
C=2000.
A=1 and B=3. Bob wants to go from City 1 to City 3.
The map can be represented as the image given below.

image

From 1 to 3 there are 2 paths : 1- 3 or 1-2-3.
Since Bob has to start with an empty tank, he has to buy fuel from fuel station at 1. Car’s tank has a maximum capacity of 2000 litres.
Amount he has to spend, if he chooses the path 1-3 : 80070= 56000.
Amount he has to spend, if he chooses the path 1-2-3 : 50070+500*40=35000+20000=55000.
Optimal path in terms of cost is 1-2-3. So output is 55000.

Sample Input 2:
5 5 3
1000
1 2 800
2 5 800
1 3 400
3 4 600
4 5 600
1 80
2 90
3 20
1 5

Sample Output 2:
134000

Explanation 2:
N=5, M=5 and S=3
C=1000
A=1 and B=5
The map can be represented as the image given below.

image

To travel between A and B, Bob has two options: 1-2-5 and 1-3-4-5.
Let us consider the route 1-2-5 : Since fuel is costlier at 2 and the car can hold a maximum of 1000 litres, even though Bob needs only 800 litres to travel from 1-2, he fills 1000 litres at 1. At 2, he is left with 200 litres and he needs 600 litres more to reach 5. So total amount = 801000+90600 = 80000 + 54000 = 134000.
Let us consider the second route 1-3-4-5 : Since fuel is cheaper at 3, he plans to fill 400 litres at 1 as he needs 400 litres to reach 3. Once he reaches 3, he can fill the tank to the maximum capacity, which is 1000 litres. However he needs 1200 litres to reach 5 and since there is no fuel station at city 4, he can't fill fuel again. So taking this route is not feasible.
Hence the output is 134000.
```

Sources:
- [CodeForces](https://codeforces.com/blog/entry/45897)
- [OJ](https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=2352)