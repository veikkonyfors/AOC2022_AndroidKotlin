# Advent of Code 2022 Day 16: Proboscidea Volcanium

Solution in Android/Kotlin.

Used Empty Views Activity sample GUI to start with.
Using Fragments, ViewModel, Binding and LiveData.

This was very hard, unable tocome to any solution using BF. 
Ended up mimicking solution of nibarius at GitHub.
Using BFS to calculate hops between all valves having flowrate > 0.
These hops are then used to build a weighted graph to be processed with aStar algorithm.
This is all I can say, NOT understanding each and every move nibarius takes.
At the start of learning curve for grapg travelsal, I am.