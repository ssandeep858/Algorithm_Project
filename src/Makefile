#Setting Flag to cp for classpath:

JFLAGS = -g
JC = javac -Xlint:unchecked

.SUFFIXES: .java .class


# Using DSTS(Dependency Suffix Target Suffix) rules

.java.class:
	$(JC) $(JFLAGS) *.java


CLASSES = \
	Node.java \
	RB_Tree.java \
	Interval.java \
	IntervalTree.java \
	IntervalShort.java \
	Main.java 
default: classes

classes: $(CLASSES:.java=.class)


# on make clean,removing previously compiled files

clean:
	$(RM) *.class
