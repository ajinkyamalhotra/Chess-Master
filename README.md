# Chess-Master

This is a chess-like game with a "Karate" theme.  Each side starts with 3 samurai, 3 ninjas, 3 mini-samurai, 3 mini-ninjas, and a King.  It is played on a 7x8 board, and players "attack" opposing pieces by moving one of their pieces in front of an opponents piece.  When a piece is attacked, it is "demoted".  When a "mini" piece is attacked, it is removed from the board.  The king cannot move, and if it is attacked, then that side loses.

#### Computer Pieces:
Ninja         = Jc
Samurai       = Sc
Mini-samurai  = sc
Mini-Ninja    = jc

#### Player Pieces:
Ninja         = J
Samurai       = S
Mini-Samurai  = s
Mini-Ninja    = j

A list of all possible moves will be generated for player and for Computer player "Gideon".
Player can choose any move from this list where as Gideon uses the list, plugs it in the MiniMax algorithm and computes the best possible move and perform's it on the game board.
