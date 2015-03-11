Midterm 1 Topics 
====================
#Software Process Models 
	- Waterfall
		- A sequential design process in which progress is seen as flowing steadily 
		downwards through the phases of:
			• Conception
			• Initiation
			• Analysis
			• Design
			• Construction
			• Testing
			• Production / Implementation
			• Maintenance
		- A hardware-oriented model. Was adapted because no other models were in available.
		- One should only move to a phase oece the preceding phase is reviewed and verified.
			-> Because moving back a phase would be costly.
	- Incremental
		- A "multi-waterfall" cycle.
		- Parts of the final product are put through waterfall model.
		- A working version of software is produced during the first module.
			-> Needs good planning and design
			-> Need clear definition of the entire system
			-> Higher cost than a single waterfall
	- Agile 
		- Many different models available, similar goal
		- Keep it Simple, Stupid (KISS)
		- Requirement Prioritization
		- Much more flexible for fast changing environment
		- Difficult to apply to large teams
		- Sharing and collaberation is paramount 
		- Limited based on modeling skills
	- Scrum
		- Agile Software Development framework 
		- "a flexible, holistic product development strategy"
		- Composed of many sprints
			- a "timeboxed" effort
			- lasts between one week to one month (two weeks typical)		
---
#Activities within Software Product 
	- lifecycles
	-  phases 
---
#Prototyping
---
#Functional Requirements
---
#User Stories and Storyboards
---
#libGDX concepts
	- Drawing sprites/text
		- Handled by OpenGL through OpenGL viewport
		- SpriteBatch
			-> Sends all images as one message to GPU
			-> How to use 
				1. Instantiate SpriteBatch
				2. call .begin()
				3. call .draw() images/text on each texture you wish to show
				4. .end() when done drawing
		- Texture
			-> Class that handles images
			-> Decodes images and loads into GPU memory	
		- TextureRegion	
			-> Same thing as texture
			-> Display regions of a texture
		- Sprite
			-> Both a texture region, the geometry of where it will be drawn, and color
	- Handling user input
		- Input Module
			-> Gdx.input...
			-> InputProcessor
				- Interface that has built in methods for user input
				- Create class, implement InputProcessor, instantiate in classes
				where you need to handle user input
				- Gdx.input.setInputProcessor(inputProcessor);	
	- render loop 
		-> Called every 30/50/80 times per second 
			- Depends on Frames per Second
		-> If you disable continious rendering, can only trigger render() when...
			1. An input event is triggered
			2. Gdx.graphics.requestRendering() is called
			3. Gdx.app.postRunnable is called
---
#UML 
	- use cases
	- sequence diagrams
	- class diagrams 
---
#Java Concepts 
	- Exceptions 
		- An event that occurs during the execution of a program that disrupts the normal
		flow of instructions.
		- When an errors occurs within a method, the method creates an object and hands it
		off to the runtime system.
		- Call Stack
			^	Method where error occurred          
			|	Method without an exception handler  
			|	Method with an exception handler     
			|	main 				     
		- Runtime system searches for something in stack to catch the exception
			-> if none is found, runtime system terminates
		- Three types of Exceptions	
			-> Check Exception
				- Check for anything unexpected or not correct input
			-> Runtime Exception
				- Internal runtime problems
			-> Unchecked Exceptions
				- Errors and runtime exceptions 
	- Enumerations
		- Constant values that cannot be changed.
		- public enum <name of enum class>	
		- Instantiate as if it were a normal class, refer to parts as properties
	- Garbage Collection
		- Definition: the process of looking at heap memory, identifying which objects 
		are in use and which are not and deleting unused objects.
		- Steps of Automatic Garbage Collection
			1. Marking
				- Identifying which pieces of memory is being used. 
			2. Normal Deletion
				- Removing of unreferenced objects leaving reference objects and pointers
				 to free space.
				a. Deleting with Compacting
					- Compact remaining objects after deleting
		- JMV Generations
			- Young Generation
				-> New objects are allocated and aged.
				-> When full, causes a minor garbage collection.
					- Stop the World Event!
						- Stops all threads and cleans things up before resuming.
			- Old Generation
				-> Long surviving objects.
				-> There exists some sort of threshold for Young to Old
				-> Major Garbage Collection
			- Permanent Generation
				-> Classes and Methods.
				-> Full Garbage collection.
			- We cannot delete objects! The Garbage collector takes care of that.
	- Polymorphism 
			- See Lab 3!
---
