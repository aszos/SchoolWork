
##### Source files and executable ############################################

SRCS 		= wordbyword.cpp 

OBJECTS 	= main.o 

EXECNAME 	= wordbyword

##### Libraries and paths ####################################################

LIBS            = 
INCDIRS 	= 
LIBDIRS 	= 
 
##### Compiler information ###################################################

CPP		= g++
CPPFLAGS 	= -std=c++0x  

##### Target compilation #####################################################

all:$(EXECNAME)

$(EXECNAME): 	$(OBJECTS)
	$(CPP) $(CPPFLAGS) $(LIBDIRS) $^ $(LIBS) -o $(EXECNAME) 

.cpp.o:	
	$(CPP) $(CPPFLAGS) $(INCDIRS) -c $<

.cc.o:	
	$(CPP) $(CPPFLAGS) $(INCDIRS) -c $<

clean:
	rm -rf *~ *.o *.a $(EXECNAME) 

##############################################################################

