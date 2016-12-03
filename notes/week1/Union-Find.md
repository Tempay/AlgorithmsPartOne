# Union Find

## Problem

Given a set of N objects
- Union Command: connect tow objects
- Find/connected query: if two objects are connected with a path?

## Modeling the Connections

- "is connected to" is an equivalence relation to:
	- Reflexive: p --> q
	- Symmetric:  p --> q == q --> p
	- Transitive: p --> q & q --> r then p --> r

- Connected components: Maximal **Bold** set of objects that are mutually connected. i.e. group of connected objects

- Find Query: Check if two objects are in the same component.

- Union command: Replace components containing two objects with their union, i.e. merge two components.

## Approching

Goal: Efficiency

- Number of objects N can be huge
- Number of operations M can be huge
- Find queries and union commands may be intermixed

Impletation: Build a class UnionFind, including method union()

### Quick-find: Slow

- Data stucture:
	- id[N]
	- p and q are connected iff they have the same id

- Find: Check if p q have same id

- Union: To merge components have p q, change all entries whose id == p's id or q's id

- Impletations: QuickFind class, id[] field, boolean check(), void union();

- Cost Model: 
	- Initialize: N, store N objects.
	- Union: N. iterate all N objects.
	- Find: 1, check if id[p] & id[q] are same. It's Great!

	The union for QF is too expensive, don't scale. During a union(), the max number of change is N - 1;

### Quick-union: Slow

Lazy approch: do work when have to; QU is lazy approch.

- Data structure:
	- id[N]
	- id[i] is parent of i.
	- Root of i is id[...id[i]...], this value keep change util it doesn't change.

- Find: Check if have same root.

- Union: Just merge the roots, i.e. if union(p, q), just set q's root as p's root (not p)

- Implemetations: QuickUnion class, id[], root(), check(), union();

- Cost Model:
	- Ini: N
	- Union: N (include cost of finding roots)
	- Find: N (worst case)

### Weighted QU

- Avoid putting large tree under smaller tree

- Depth of any object is at most lgN: Find takes time proportional to depth of p and q

- Cost Model
	- Ini: N
	- Union: lgN
	- Find: lgN

### Path Compression

- Dynamically change the root of object in the path

### WQU with PC

Hopcroft-Ulman: any sequence of M union-find ops on N obj makes at most c ( N + M lg * N) array access

Almost linear.

No linear time algrithm for UF. Proved.

## Application

- Percolation
- Dynamic connecivity


