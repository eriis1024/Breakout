Design goals: The design goals of this project included writing readable code through the use of meaningful names for 
classes, methods, and variables and by avoiding "magic" variables so that the purpose of the code is 
understandable without needing comments. Another design goal was to have modularity by not using public 
instance variables or static variables and to have ultimately flexible code that is not duplicated within 
the project.

Adding new features: In order to add a new level to the game, I would first create a new class called LevelFour that extends 
BlockBreakParent. Within this class I would have a method called setupGame that takes an int for the width 
of the scene, height, and Paint for the color of the background. Within this method I would initialize a scene 
and keyframe and then call setupScene from the BlockBreakParent class. I would also add whatever blocks I desire 
in whatever configuration algorithmically within this method. I would also include a handleMouseInput method 
that starts the animation if the screen is clicked. Then I would include a method that calls step from the 
BlockBreakParent class and includes conditions in which the level should transition to an informational scene 
(such as transitioning to game over or the next level). These transitions could be written as separate methods 
that then get called if a specific condition is met. Finally, I would include a method to specify the various key
 commands for the level. If the key commands for every level were the same then this portion could likely be included 
 within the parent class. In order to just add new objects to every scene, I would just add them to the setupScene method
 within BlockBreakParent which is then called by the level classes and informational scene classes.
 
Major design choices: The first major design decision I made was to completely reorganize and restructure my code from one
main class into various separate classes that extended a parent class. Having this assignment be the first project I have 
ever coded from scratch, I did not initially consider other design options until I got to the point that I wanted to implement
different levels and scenes. At that point I realized it would be more efficient and necessary to create separate classes in
order to set up different scene configurations to represent different levels and informational scenes, and to transition between 
them under specific conditions. There are not really any cons I can think of to separating the code between separate classes rather 
than keeping all of the code within one large class but the pros of doing so include having better organized and simplified code, 
more efficient means of implementing various methods, and creating a more generalized code that can deal with different cases that 
may come up. Therefore, I prefer having separate classes that implement inheritance. A design decision I wish I had made was creating
separate classes for many of the objects within the levels, such as the paddle, balls, blocks, and upgrades. Doing so would have allowed 
my code to be much better organized and simplified and would've allowed me to avoid much of the hard-coding that was necessary to create
and correctly animate each object so that they all interacted together and behaved correctly. I could have created different methods
within each class that I then could have called in the step method so that this method would have been much shorter and less confusing. 
This is definitely a design decision that I would have preferred and implemented had I realized earlier that creating multiple separate 
classes is very beneficial.

 Assumptions: My code assumes that if the color of an object is set to transparent it will ignore any intersections with objects that are
 set to any other color than transparent.  
 