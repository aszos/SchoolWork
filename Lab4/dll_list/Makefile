##############################################################################
#### LINUX makefile !!! ######################################################
##############################################################################

##### General libraries and inludes

##############################################################################

MY_LIBS = 
MY_INCS = -I.
MY_LIBDIRS = 

SRCS = main.cpp 

OBJECTS = main.o 

##### Compiler information ###################################################

  CPP	= g++
  CPPFLAGS = -g -w

EXECNAME=list

##### Target compilation #####################################################

all: $(EXECNAME)

install: $(EXECNAME)
	$(INSTALL_PROGRAM) $(EXECNAME) $(BINDIR)

dep:
	makedepend -Y -- $(CPPFLAGS) -- $(SRCS) 2>/dev/null

$(EXECNAME): 	$(OBJECTS)
	$(CPP) $(CPPFLAGS) $(LDFLAGS) $(MY_LIBDIRS) $^ $(MY_LIBS) -o $(EXECNAME) 

.cpp.o:	
	$(CPP) $(CPPFLAGS) $(MY_INCS) -c $<

.cc.o:	
	$(CPP) $(CPPFLAGS) $(MY_INCS) -c $<

clean:
	rm -rf *~ *.o $(EXECNAME) 


