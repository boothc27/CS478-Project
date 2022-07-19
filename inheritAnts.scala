/*
	Chris Booth
  Zach Royal
	CS478 Antwars Team
*/

package players.inheritAnts

import scant._
import awannotation.AntWarsPlayer

@AntWarsPlayer
class inheritAntsQueen extends Queen {
  var alt = true
  var c = 0
  def takeTurn {
    if (time < 5) layEgg(new Block(40,-2))
    else if (time < 30) {
      if (alt) {
        alt = false
        layEgg(new Get(20,-11))
      }
      else {
        alt = true
        layEgg(new Get(20,11))
      }
    }
  	else if (time < 500) {
	  	if (c < 6 && canMove(North)) {
        c = c+1
        if (c < 2) layEgg(new Block(21,0))
    	  if (c < 3) layEgg(new Block(21,-10))
        if (c < 4) layEgg(new Block(21,10))
        if (c < 5) layEgg(new Sentry(35,0))
        if (c < 6) layEgg(new Sentry(8,0))
      }
        if (countAnts < 14) {
  	    if (alt) {
          alt = false
          layEgg(new Get(20,-11))
        }
	    	else {
          alt = true
          layEgg(new Get(20,11))
        }
	    }
	}
	else {
		if (countAnts < 2) {
			layEgg(new EndBlock(1,1))
		}
	}
  }
}

class Block(x: Int, y: Int) extends Ant {
	strength = 3
	health = 20
	icon = '+'
	var (gx,gy) = (x,y)
  def lookForEnemy: Direction = {
    for (d <- List(North,South,East,West)) {
      if (lookForEnemy(d)) return d
    }
    return Here
  }
  def go(gx:Int,gy:Int,cx:Int,cy:Int): Direction = {
    if (cy > gy && canMove(South)) return South
    else if (cy < gy && canMove(North)) return North
    else if (cx < gx && canMove(East)) return East
    else if (cx > gx && canMove(West)) return West
    else if (cx == gx && cy == gy) return Here
    else if (canMove(North)) return North
    else if (canMove(South)) return South
    else if (canMove(East)) return East
    else if (canMove(West)) return West
    else return Here
  }
  def checkEnd = {
    if (time > 500) {
      gx = 40
      gy = 0
    }
  }
	def takeTurn {
    val (cx,cy) = (pos.x,pos.y)
		checkEnd
		val e = lookForEnemy
    if (e != Here) attack(e)
    move(go(gx,gy,cx,cy))
	}
}

class EndBlock(x: Int, y: Int) extends Block(x,y) {
  override def takeTurn {
    val (cx,cy) = (pos.x,pos.y)
    for (d <- List(North,South,East,West)) {
      if (lookForEnemy(d)) attack(d)
    }
    if (cx < gx) {
			if (canMove(East)) move(East)
		}
		if (cy > gy) {
			if (canMove(South)) move(South)
		}
		if (cy < gy) {
			if (canMove(North)) move(North)
		}
		if (cx == x && cy == y) {}
    else {
      if(canMove(North)) move(North)
      if(canMove(South)) move(South)
      if(canMove(East)) move(East)
    }
  }
}

class Sentry(x: Int, y: Int) extends Block(x,y) {
  val (ox,oy) = (x,y)
  var state = 0
  override def takeTurn {
    val (cx,cy) = (pos.x,pos.y)
    checkEnd
    if (state == 0 && gx == cx && gy == cy) {
      gx = ox+1
      gy = oy+3
      state = 1
    }
    if (state == 1 && gx == cx && gy == cy) {
      gx = ox-1
      gy = oy-3
      state = 0
    }
    //if (state == 2)
    val e = lookForEnemy
    if (e != Here) attack(e)
    val g = go(gx,gy,cx,cy)
    if (g != Here) move(g)
  }
}

class Get(x: Int, y: Int) extends Ant {
	capacity = 224
	icon = 'X'
	var (gx,gy) = (x,y)
  def go(gx:Int,gy:Int,cx:Int,cy:Int): Direction = {
    for (d <- List(North,South,East,West)) {
      if (lookForFood(d) > 0) return d
    }
    if (cx < gx && canMove(East)) return East
    else if (cy < gy && canMove(North)) return North
    else if (cy > gy && canMove(South)) return South
    else if (cx > gx && canMove(West)) return West
    else if (canMove(East)) return East
    else if (canMove(North)) return North
    else if (canMove(South)) return South
    else if (canMove(West)) return West
    else return Here
  }
	def takeTurn {
		val (cx,cy) = (pos.x,pos.y)
		if (time > 500) {
			gx = 41
			gy = 0
		}
		if (food > 0) { //return food
			if (cx <= 1 && cy <= 1 && cy >= -1) drop
			if (cy > 1 && canMove(South)) move(South)
			if (cy < -1 && canMove(North)) move(North)
      if (cx > 1 && canMove(West)) move(West)
			if(canMove(North)) move(North)
			if(canMove(South)) move(South)
			if(canMove(East)) move(East)
		}
    if (gx == 41) {
      for (d <- List(North,South,East,West)) {
        if (lookForEnemy(d)) return attack(d)
      }
    }
		if (lookForFood(Here) > 0) pickup
		if (cx == gx && cy == gy) {
      if (lookForFood(Here) < 1) {
        gx = 41
  			gy = 0
      }
		}
    move(go(gx,gy,cx,cy))
	}
}
