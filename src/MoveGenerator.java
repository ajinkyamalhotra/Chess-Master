import java.util.ArrayList;

public class MoveGenerator {
	
   static ArrayList<GamePiece> gamePieces = null;

   /**
    * 
    * Generates legal moves for Gideon or Player
    * 
    * @param playerOrGideon - true = Generate Gideon's move | false = Generate Player's move
    * @param gamePiecesA - ArrayList storing all the game pieces
    * @return - return's all the moves stored in an ArrayList of Integers
    * 
    */
   public static ArrayList<Integer> Gen(boolean playerOrGideon, ArrayList<GamePiece> gamePiecesA) {
   		
      gamePieces = gamePiecesA;
      
      ArrayList<Integer> moves = new ArrayList<Integer>();
   		
      for(int i=0; i<gamePieces.size(); ++i) {
      		
         GamePiece currentPiece = gamePieces.get(i);
         int locX = currentPiece.getX()+1;
         int locY = currentPiece.getY()+1;
         int loc = locX*10 + locY;
      		
         moves = addAttackMove(moves, gamePieces, currentPiece, loc, playerOrGideon);
      	
         if(currentPiece.getComputerPlayerFlag() == playerOrGideon){
         	
            if(currentPiece instanceof Ninja) {																//To Calculate all the moves that all the ninja's can perform
            	
               int move1 = loc; int move2 = loc;															//Calculating 2 different moves in 1 step since the Ninja piece can move in left or right direction
            	
               boolean move1Flag = true; boolean move2Flag = true;											//boolean flags to stop generating illegal moves, once found any
            	
               if(!playerOrGideon) {																		//For Generating player's moves
            	   
                  while(move1+9< 87 || move2+11< 87) {														//add move1 and move2 flag condition in the while loop and add for the other while loop's
                  	
                     move1 += 9;
                     if(move1%10 == 0)
                        move1 = 999999;
                  		
                     if(move1 < 87 && move1Flag) {
                     	
                        if(checkLegalMove(move1)) 
                           moves.add((loc*100)+move1);
                        
                        else
                           move1Flag = false;
                     
                     }
                  	
                     move2 += 11;
                     if(move2%10 == 8)
                        move2 = 999999;
                  	
                     if(move2< 87 && move2Flag) {
                     	
                        if(checkLegalMove(move2))
                           moves.add((loc*100)+move2);	
                        	
                        else
                           move2Flag = false;
                     
                     }
                  
                  }
               	
               }
               				
               else if(playerOrGideon) {																	//For Generating Gideon's moves
                  while(move1-9>10 || move2-11>10) {
                  	
                     move1 -= 9;
                     if(move1%10 == 8)
                        move1 = -999999;
                  	
                     if(move1 > 10 && move1Flag) {
                     	
                        if(checkLegalMove(move1)) 
                           moves.add((loc*100)+move1);
                        
                        else
                           move1Flag = false;
                     }
                  	
                     move2 -= 11;
                     if(move2%10 == 0)
                        move2 = -999999;
                  	
                     if(move2 > 10 && move2Flag) {
                     	
                        if(checkLegalMove(move2)) 
                           moves.add((loc*100)+move2);
                        
                        else
                           move2Flag = false;
                     	
                     }
                  		
                  }
               
               }
            
            }
            
            else if(currentPiece instanceof MiniNinja) {
            	
               int move1 = loc; int move2 = loc;
            	
               if(!playerOrGideon) {																		//For Generating player's moves
                  if(move1+9< 87 || move2+11< 87) {
                  	
                     move1 += 9;
                     if(move1%10 == 0)
                        move1 = 999999;
                  	
                     if(move1 < 87)
                        if(checkLegalMove(move1))
                           moves.add((loc*100)+move1);
                  	
                  	
                     move2 += 11;
                     if(move2%10 == 8)
                        move2 = 999999;
                  	
                     if(move2< 87)
                        if(checkLegalMove(move2))
                           moves.add((loc*100)+move2);
                  
                  }
               }
               
               else if(playerOrGideon) {																	//For Generating Gideon's moves
                  if(move1-9>10 || move2-11>10) {
                  	
                     move1 -= 9;
                     if(move1%10 == 8)
                        move1 = -999999;
                  	
                     if(move1 > 10)
                        if(checkLegalMove(move1))
                           moves.add((loc*100)+move1);
                  	
                  	
                     move2 -= 11;
                     if(move2%10 == 0)
                        move2 = -999999;
                  	
                     if(move2 > 10)
                        if(checkLegalMove(move2))
                           moves.add((loc*100)+move2);
                  }
               
               }
            	
            }
            
            else if(currentPiece instanceof Samurai) {
            	
               int move1 = loc;
            	
               boolean move1Flag = true;																	//boolean flags to stop generating illegal moves, once found any
            	
               if(!playerOrGideon) {																		//For Generating player's moves
                  while(move1+10< 87) {
                  	
                     move1 += 10;
                     if(move1%10 == 0)
                        move1 = 999999;
                  	
                     if(move1 < 87 && move1Flag) {
                     	
                        if(checkLegalMove(move1)) 
                           moves.add((loc*100)+move1);
                        
                        else
                           move1Flag = false;
                     
                     }
                  
                  }
               	
               }
               				
               else if(playerOrGideon) {																	//For Generating Gideon's moves
                  while(move1-10>10) {
                  	
                     move1 -= 10;
                     if(move1/10 == 0)
                        move1 = -999999;
                  	
                     if(move1 > 10 && move1Flag) {
                     	
                        if(checkLegalMove(move1)) 
                           moves.add((loc*100)+move1);
                        
                        else
                           move1Flag = false;
                     }
                  		
                  }
               
               }
            	
            }
            
            else if(currentPiece instanceof MiniSamurai) {
            	
               int move1 = loc;
            	
               boolean move1Flag = true;																	//boolean flags to stop generating illegal moves, once found any
            	
               if(!playerOrGideon) {																		//For Generating player's moves
                  if(move1+10< 87) {
                  	
                     move1 += 10;
                     if(move1%10 == 0)
                        move1 = 999999;
                  	
                     if(move1 < 87 && move1Flag) {
                     	
                        if(checkLegalMove(move1)) 
                           moves.add((loc*100)+move1);
                        
                        else
                           move1Flag = false;
                     
                     }
                  
                  }
               	
               }
               				
               else if(playerOrGideon) {																	//For Generating Gideon's moves
                  if(move1-10>10) {
                  	
                     move1 -= 10;
                     if(move1/10 == 0)
                        move1 = -999999;
                  	
                     if(move1 > 10 && move1Flag) {
                     	
                        if(checkLegalMove(move1)) 
                           moves.add((loc*100)+move1);
                        
                        else
                           move1Flag = false;
                     }
                  		
                  }
               
               }
            	
            }
         	
         }
      
      }
      
      return moves;
   }
	
   /**
    * 
    * checkLegalMove() checks if there already exists a game piece on the location where we want to move our current piece
    * 
    * @param move1 - stores the move that needs to be validated
    * @return - true = legal move was passed | false = illegal move was passed
    * 
    */
   private static boolean checkLegalMove(int move1) {
   		
      for(int i=0; i<gamePieces.size(); ++i) {
         int X = gamePieces.get(i).getX()+1;
         int Y = gamePieces.get(i).getY()+1;
         int loc = X*10 + Y;
      		
         if(move1 == loc)
            return false;
      		
      }
      return true;
   }

   /**
    * 
    * addAttackMove() adds the attack move for the current game piece
    * 
    * @param moves - ArrayList containing all the moves
    * @param gamePieces - ArrayList containing all the game pieces
    * @param currentPiece - GamePiece for which we are adding attack move
    * @param loc - Location of the current gamePiece
    * @param playerOrGideon - Flag determining if current GamePiece is player's or Gideon's
    * @return - returning the moves ArrayList after adding all the attack moves for the current GamePiece
    * 
    */
   private static ArrayList<Integer> addAttackMove(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc, boolean playerOrGideon){
   		
      if(currentPiece.getComputerPlayerFlag() == playerOrGideon) {
    	  
         boolean move1Flag = true; boolean move2Flag = true;												//boolean flags to stop generating illegal moves, once found any
         
         if(currentPiece instanceof Ninja) {
         	
            int move1 = loc; int move2 = loc;
            
            if(playerOrGideon) {																			//For Generating Gideon's attack moves
               while(move1+9< 87 || move2+11< 87) {
               	
                  move1 += 9;
                  if(move1%10 == 0)
                     move1 = 999999;
               	
                  if(move1 < 87 && move1Flag) {

                     if(checkLegalMove(move1)) 
                    	 moves = negative10(move1, currentPiece, loc, moves);
                  	
                     else
                         move1Flag = false;
                  }
               	
                  move2 += 11;
                  if(move2%10 == 8)
                     move2 = 999999;
               	
                  if(move2< 87 && move2Flag) {
                  	
                	  if(checkLegalMove(move2))
                		  moves = negative10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               
               }
            	
            }
            
            else if(!playerOrGideon) {																		//For Generating player's attack moves
               while(move1-9>10 || move2-11>10) {
               	
                  move1 -= 9;
                  if(move1%10 == 8)
                     move1 = -999999;
               	
                  if(move1 > 10 && move1Flag) {
                	  
                	  if(checkLegalMove(move1))
                		  moves = plus10(move1, currentPiece, loc, moves);
                  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 -= 11;
                  if(move2%10 == 0)
                     move2 = -999999;
               	
                  if(move2 > 10 && move2Flag) {
                     
                	  if(checkLegalMove(move2))
                		  moves = plus10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               		
               }
            
            }
         	
         }
         
         else if(currentPiece instanceof MiniNinja) {
         	
            int move1 = loc; int move2 = loc;
         
            if(playerOrGideon) {																			//For Generating Gideon's attack moves
               if(move1+9< 87 || move2+11< 87) {
               	
                  move1 += 9;
                  if(move1%10 == 0)
                     move1 = 999999;
               	
                  if(move1 < 87 && move1Flag) {
                	  
                	  if(checkLegalMove(move1))
                		  moves = negative10(move1, currentPiece, loc, moves);
                	  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 += 11;
                  if(move2%10 == 8)
                     move2 = 999999;
               	
                  if(move2< 87 && move2Flag) {
                	  
                	  if(checkLegalMove(move2))
                		  moves = negative10(move2, currentPiece, loc, moves);
                  
                	  else 
                		  move2Flag = false;
                  }
               
               }
            	
            }
            
            else if(!playerOrGideon) {																		//For Generating player's attack moves
               if(move1-9>10 || move2-11>10) {
               	
                  move1 -= 9;
                  if(move1%10 == 8)
                     move1 = -999999;
               	
                  if(move1 > 10 && move1Flag) {
                	  
                	  if(checkLegalMove(move1))
                		  moves = plus10(move1, currentPiece, loc, moves);
                  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 -= 11;
                  if(move2%10 == 0)
                     move2 = -999999;
               	
                  if(move2 > 10 && move2Flag) {
                	  
                	  if(checkLegalMove(move2))
                		  moves = plus10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               		
               }
            
            }
         	
         }
         
         else if(currentPiece instanceof Samurai) {
         	
            int move1 = loc; int move2 = loc;
         	
            if(playerOrGideon) {																			//For Generating Gideon's attack moves
            
               while(move1+1< 87 || move2-1> 10) {
               	
                  move1 += 1;
                  if(move1%10 == 0)
                     move1 = 999999;
               	
                  if(move1 < 87 && move1Flag) {
                	  
                	  if(checkLegalMove(move1))
                		  moves = negative10(move1, currentPiece, loc, moves);
                	  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 -= 1;
                  if(move2%10 == 8)
                     move2 = -999999;
               	
                  if(move2 > 10 && move2Flag) {
                	  
                	  if(checkLegalMove(move2))
                		  moves = negative10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               
               }
            	
            }
            
            else if(!playerOrGideon) {																		//For Generating player's attack moves
            	
               while(move1+1< 87 || move2-1> 10) {
               	
                  move1 += 1;
                  if(move1%10 == 0)
                     move1 = 999999;
               	
                  if(move1 < 87 && move1Flag) {
                	  
                	  if(checkLegalMove(move1))
                		   moves = plus10(move1, currentPiece, loc, moves);
                	  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 -= 1;
                  if(move2%10 == 8)
                     move2 = -999999;
               	
                  if(move2 > 10 && move2Flag) {
                	  
                	  if(checkLegalMove(move2))
                		  moves = plus10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               
               }
            	
            }
         	
         }
         
         else if(currentPiece instanceof MiniSamurai) {
         	
            int move1 = loc; int move2 = loc;
         	
            if(playerOrGideon) {																			//For Generating Gideon's attack moves
            
               if(move1+1< 87 || move2-1> 10) {
               	
                  move1 += 1;
                  if(move1%10 == 0)
                     move1 = 999999;
               	
                  if(move1 < 87 && move1Flag) {
                     
                	  if(checkLegalMove(move1))
                		  moves = negative10(move1, currentPiece, loc, moves);
                	  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 -= 1;
                  if(move2%10 == 8)
                     move2 = -999999;
               	
                  if(move2 > 10 && move2Flag) {
                     
                	  if(checkLegalMove(move2))
                		  moves = negative10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               
               }
            	
            }
            
            else if(!playerOrGideon) {																		//For Generating player's attack moves
            	
               if(move1+1< 87 || move2-1> 10) {
               	
                  move1 += 1;
                  if(move1%10 == 0)
                     move1 = 999999;
               	
                  if(move1 < 87 && move1Flag) {
                	 
                	  if(checkLegalMove(move1))
                		  moves = plus10(move1, currentPiece, loc, moves);
                  
                	  else
                		  move1Flag = false;
                  }
               	
                  move2 -= 1;
                  if(move2%10 == 8)
                     move2 = -999999;
               	
                  if(move2 > 10 && move2Flag) {
                	  
                	  if(checkLegalMove(move2))
                		  moves = plus10(move2, currentPiece, loc, moves);
                  
                	  else
                		  move2Flag = false;
                  }
               
               }
            	
            }
         	
         }
      
      }
   		
      return moves;
   }
	
   /**
    * 																		
    * Condition check for adding an attack move for Player's game pieces	
    * 																		
    * @param move - attackMove that needs to be checked before adding to the ArrayList of moves
    * @param currentPiece - gamePiece that attempting to attack another piece
    * @param loc - location of current game piece
    * @param moves - ArraList of moves
    * @return - returning arrayList of moves
    * 
    */
   private static ArrayList<Integer> plus10(int move, GamePiece currentPiece, int loc, ArrayList<Integer> moves) {
      for(int i=0; i<gamePieces.size(); ++i) {
         int location = (gamePieces.get(i).getX()+1)*10 + (gamePieces.get(i).getY()+1);
         if(move+10 == location && checkLegalMove(move) && currentPiece.getComputerPlayerFlag() != gamePieces.get(i).getComputerPlayerFlag())
            moves.add((loc*100)+move);			
      }
      return moves;
   }
		
   /**
    * 
    * Condition check for adding an attack move for Gideon's game piece
    * 
    * @param move - attackMove that needs to be checked before adding to the ArrayList of moves
    * @param currentPiece - gamePiece that attempting to attack another piece
    * @param loc - location of current game piece
    * @param moves - ArraList of moves
    * @return - returning arrayList of moves 
    * 
    */
   private static ArrayList<Integer> negative10(int move, GamePiece currentPiece, int loc, ArrayList<Integer> moves) {
      for(int i=0; i<gamePieces.size(); ++i) {
         int location = (gamePieces.get(i).getX()+1)*10 + (gamePieces.get(i).getY()+1);
         if(move-10 == location && checkLegalMove(move) && currentPiece.getComputerPlayerFlag() != gamePieces.get(i).getComputerPlayerFlag())
            moves.add((loc*100)+move);			
      }
      return moves;
   }
	
}
