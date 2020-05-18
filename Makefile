#Setting Flag to cp for classpath:

JFLAGS = -cp
JC = javac

.SUFFIXES: .java .class


# Using DSTS(Dependency Suffix Target Suffix) rules

.java.class:
	$(JC) $(JFLAGS)  *.java
CLASSES = \
	src/Interval.java \
	src/IntervalShort.java \
	src/Main.java .java \
	src/Main.java .java \
	src/Node.java \
	src/RB_Tree.java

default: classes


#Replacement of suffixes

classes: $(CLASSES:.java=.class)


# on make clean,removing previously compiled files

clean:
	$(RM) src/*.class
