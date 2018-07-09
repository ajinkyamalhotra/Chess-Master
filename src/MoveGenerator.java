import java.util.ArrayList;

public class MoveGenerator {
	
   static GameBoard game = new GameBoard();
   static ArrayList<GamePiece> gamePieces = game.getGamePiecesArray();

		//using same legal move generator for Gideon and user(player)
   public static ArrayList<Integer> Gen(boolean playerOrGideon) {
   		
      ArrayList<Integer> moves = new ArrayList<Integer>();
   		
      for(int i=0; i<gamePieces.size(); ++i) {
      		
         GamePiece currentPiece = gamePieces.get(i);
         int locX = currentPiece.getX()+1;
         int locY = currentPiece.getY()+1;
         int loc = locX*10 + locY;
      		
         moves = addAttackMove(moves, gamePieces, currentPiece, loc, playerOrGideon);
      		
      		//To Calculate all the moves that all the ninja's can perform
         if(currentPiece instanceof Ninja && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
         		
         		//Calculating 2 different moves in 1 step since the Ninja piece can move in left or right direction
            int move1 = loc; int move2 = loc;
         		
         		//boolean flags to stop generating illegal moves, once found any
            boolean move1Flag = true; boolean move2Flag = true;
         		
         		//For Generating user's moves
            if(!playerOrGideon) {
               while(move1+9< 87 || move2+11< 87) {
               		
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
            					
            	//For Generating Gideon's moves
            else if(playerOrGideon) {
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
         	
         else if(currentPiece instanceof MiniNinja && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
         		
            int move1 = loc; int move2 = loc;
         		
         		//For Generating user's moves
            if(!playerOrGideon) {
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
            	
            	//For Generating Gideon's moves
            else if(playerOrGideon) {
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
         	
         else if(currentPiece instanceof Samurai && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
         		
            int move1 = loc;
         		
         		//boolean flags to stop generating illegal moves, once found any
            boolean move1Flag = true;
         		
         		//For Generating user's moves
            if(!playerOrGideon) {
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
            					
            	//For Generating Gideon's moves
            else if(playerOrGideon) {
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
         	
         else if(currentPiece instanceof MiniSamurai && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
         		
            int move1 = loc;
         		
         		//boolean flags to stop generating illegal moves, once found any
            boolean move1Flag = true;
         		
         		//For Generating user's moves
            if(!playerOrGideon) {
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
            					
            	//For Generating Gideon's moves
            else if(playerOrGideon) {
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
   
      return moves;
   }
		
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
		
   private static ArrayList<Integer> addAttackMove(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc, boolean playerOrGideon){
   		
      if(currentPiece instanceof Ninja && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
      		
         int move1 = loc; int move2 = loc;
      		
      		//For Generating user's moves
         if(playerOrGideon) {
            while(move1+9< 87 || move2+11< 87) {
            		
               move1 += 9;
               if(move1%10 == 0)
                  move1 = 999999;
            		
               if(move1 < 87) {
               		
                  moves = negative10(move1, currentPiece, loc, moves);
               		
               }
            		
               move2 += 11;
               if(move2%10 == 8)
                  move2 = 999999;
            		
               if(move2< 87) {
               		
                  moves = negative10(move2, currentPiece, loc, moves);
               	
               }
            
            }
         		
         }
         	
         	//For Generating Gideon's moves
         else if(!playerOrGideon) {
            while(move1-9>10 || move2-11>10) {
            		
               move1 -= 9;
               if(move1%10 == 8)
                  move1 = -999999;
            		
               if(move1 > 10) {
                  moves = plus10(move1, currentPiece, loc, moves);
               
               }
            		
               move2 -= 11;
               if(move2%10 == 0)
                  move2 = -999999;
            		
               if(move2 > 10) {
                  moves = plus10(move2, currentPiece, loc, moves);
               	
               }
            			
            }
         	
         }
      		
      }
      	
      else if(currentPiece instanceof MiniNinja && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
      		
         int move1 = loc; int move2 = loc;
      		
      		//For Generating user's moves
         if(playerOrGideon) {
            if(move1+9< 87 || move2+11< 87) {
            		
               move1 += 9;
               if(move1%10 == 0)
                  move1 = 999999;
            		
               if(move1 < 87) {
                  moves = negative10(move1, currentPiece, loc, moves);
               }
            		
               move2 += 11;
               if(move2%10 == 8)
                  move2 = 999999;
            		
               if(move2< 87) {
                  moves = negative10(move2, currentPiece, loc, moves);
               	
               }
            
            }
         		
         }
         	
         	//For Generating Gideon's moves
         else if(!playerOrGideon) {
            if(move1-9>10 || move2-11>10) {
            		
               move1 -= 9;
               if(move1%10 == 8)
                  move1 = -999999;
            		
               if(move1 > 10) {
                  moves = plus10(move1, currentPiece, loc, moves);
               
               }
            		
               move2 -= 11;
               if(move2%10 == 0)
                  move2 = -999999;
            		
               if(move2 > 10) {
                  moves = plus10(move2, currentPiece, loc, moves);
               	
               }
            			
            }
         	
         }
      		
      }
      	
      else if(currentPiece instanceof Samurai && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
      		
         int move1 = loc; int move2 = loc;
      		
         if(playerOrGideon) {
         	
            while(move1+1< 87 || move2-1> 10) {
            		
               move1 += 1;
               if(move1%10 == 0)
                  move1 = 999999;
            		
               if(move1 < 87) {
                  moves = negative10(move1, currentPiece, loc, moves);
               }
            		
               move2 -= 1;
               if(move2%10 == 8)
                  move2 = -999999;
            		
               if(move2 > 10) {
                  moves = negative10(move2, currentPiece, loc, moves);
               	
               }
            	
            }
         		
         }
         	
         else if(!playerOrGideon) {
         		
            while(move1+1< 87 || move2-1> 10) {
            		
               move1 += 1;
               if(move1%10 == 0)
                  move1 = 999999;
            		
               if(move1 < 87) {
                  moves = plus10(move1, currentPiece, loc, moves);
               }
            		
               move2 -= 1;
               if(move2%10 == 8)
                  move2 = -999999;
            		
               if(move2 > 10) {
                  moves = plus10(move2, currentPiece, loc, moves);
               	
               }
            	
            }
         		
         }
      		
      }
      	
      else if(currentPiece instanceof MiniSamurai && currentPiece.getComputerPlayerFlag() == playerOrGideon) {
      		
         int move1 = loc; int move2 = loc;
      		
         if(playerOrGideon) {
         	
            if(move1+1< 87 || move2-1> 10) {
            		
               move1 += 1;
               if(move1%10 == 0)
                  move1 = 999999;
            		
               if(move1 < 87) {
                  moves = negative10(move1, currentPiece, loc, moves);
               }
            		
               move2 -= 1;
               if(move2%10 == 8)
                  move2 = -999999;
            		
               if(move2 > 10) {
                  moves = negative10(move2, currentPiece, loc, moves);
               	
               }
            	
            }
         		
         }
         	
         else if(!playerOrGideon) {
         		
            if(move1+1< 87 || move2-1> 10) {
            		
               move1 += 1;
               if(move1%10 == 0)
                  move1 = 999999;
            		
               if(move1 < 87) {
                  moves = plus10(move1, currentPiece, loc, moves);
               }
            		
               move2 -= 1;
               if(move2%10 == 8)
                  move2 = -999999;
            		
               if(move2 > 10) {
                  moves = plus10(move2, currentPiece, loc, moves);
               	
               }
            	
            }
         		
         }
      		
      }
   		
      return moves;
   }
		
   private static ArrayList<Integer> plus10(int move, GamePiece currentPiece, int loc, ArrayList<Integer> moves) {
      for(int i=0; i<gamePieces.size(); ++i) {
         int location = (gamePieces.get(i).getX()+1)*10 + (gamePieces.get(i).getY()+1);
         if(move+10 == location && checkLegalMove(move) && currentPiece.getComputerPlayerFlag() != gamePieces.get(i).getComputerPlayerFlag())
            moves.add((loc*100)+move);			
      }
      return moves;
   }
		
   private static ArrayList<Integer> negative10(int move, GamePiece currentPiece, int loc, ArrayList<Integer> moves) {
      for(int i=0; i<gamePieces.size(); ++i) {
         int location = (gamePieces.get(i).getX()+1)*10 + (gamePieces.get(i).getY()+1);
         if(move-10 == location && checkLegalMove(move) && currentPiece.getComputerPlayerFlag() != gamePieces.get(i).getComputerPlayerFlag())
            moves.add((loc*100)+move);			
      }
      return moves;
   }
	
}
