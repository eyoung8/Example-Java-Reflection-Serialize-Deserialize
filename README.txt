CS442 Design Patterns
Fall 2015
PROJECT 5 README FILE
Due Date: Tuesday, December 15, 2015
Submission Date: Tuesday, December 15, 2015
Author(s): Evan Young
e-mails(s): eyoung8@binghamton.edu

PURPOSE:
  Use the dynamic proxy, reflection, and strategy patterns to 
  serialize and then deserialize two objects.

SAMPLE OUTPUT:
    ant -buildfile build.xml run -Darg0='checkpoint.txt' -Darg1='200' -Darg2='serdeser' -Darg3='XML'
    [java] Number of unequal objects = 0
    [java] Success!

TO COMPILE:

  To compile run the command: ant -buildfile build.xml all
  in the directory: young_evan_assign5

TO RUN:
  Navigate to the directory young_evan_assign5
  Then run command: ant -buildfile build.xml run -Darg0=<CHECKPOINT_FILE> -Darg1=<NUM_OBJECTS>
		   -Darg2=<'SER'/'DESER'/'SERDESER'> -Darg3=<WIRE_METHOD>
