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

### Quick-find

- Data stucture:
	- id[] of Size N
	- p and q are connected iff they have the same id

- Find: Check if p q have same id

- Union: To merge components have p q, change all entries whose id == p's id or q's id

