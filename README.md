# CS478-Project

AntWars
[final submission: inheritAnts](inheritAnts.scala)

##ASCII Inheritance Diagram


                          Block extends Ant
                          Fields:
                            strength: Int
                            health: Int
                            icon: Char
                            gx, gy: Int, Int
                          Methods:
                            lookForEnemy: Direction
                            go: Direction
                            checkEnd: Unit
                            takeTurn: Unit
                          /                \
                         /                  \
      EndBlock extends Block                Sentry Ant extends Block
      Overrides:                            Fields:
        takeTurn                              ox, oy: Int, Int
                                              state: Int
                                            Overrides:
                                              takeTurn


                          Get extends Ant
                          Fields:
                            capacity: Int
                            icon: Char
                          Methods:
                            go: Direction
                            takeTurn: Unit
