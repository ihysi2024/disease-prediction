CHANGES MADE FROM HW2:
1. Exceptions in most methods were either not thrown or thrown in the wrong order,
so I made sure to implement them the way the Javadocs specified.
2. startGame initialized the model's fields in the incorrect order, so they are now set to 
first grab the board's rows and columns and initialize them, before setting each piece of the given
board to the model's board. This aliasing was also consistent throughout the code, and had to be 
adjusted.
3. I allowed mutation of the Piece object, which should have been an immutable object. Instead of 
changing the fields of the piece in methods such as Swap, I now set the Pieces at those positions to 
new Pieces. 
4. I shortened the removeMatch method. It was drawn out to examine the behavior at the board edges, 
corners, and interior, but adjacent pieces of any given piece could be examined through a simple
set of if statements. 
5. I was previously unable to implement the gameOver method correctly, and in this assignment, I
abstracted parts of my removeMatch search method and implemented it in my gameOver search to
determine if pieces could still compose a matching block with no swaps left. 
6. I added more test cases, specifically in determining a random board was correctly generated and 
testing for the possibility of errors arising in game initialization. 