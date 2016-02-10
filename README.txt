CS442 Design Patterns
Fall 2015
PROJECT 5 README FILE
Due Date: Tuesday, December 15, 2015
Submission Date: Tuesday, December 15, 2015
Grace Period Used This Project: 0 Days
Grace Period Remaining: ? Days
Author(s): Evan Young
e-mails(s): eyoung8@binghamton.edu

PURPOSE:
[
  Use the dynamic proxy, reflection, and strategy patterns to 
  serialize and then deserialize two objects.
]

PERCENT COMPLETE:
[
   I believe I have complete 100% of this project.
]

PARTS THAT ARE NOT COMPLETE:
[
   There are no parts that are not complete.
]

BUGS:
[
   I know of no bugs in the project.
]

FILES:
[
   Included with this project are X files:

   README.txt, the text file you are presently reading.
   build.xml, the file used by ant to clean, compile, and run this project.
   Driver.java, contains the main function
   InputFileProcessor.java, processes files for input
   OutputFileProcessor.java, processes files for output
   RestoreI.java, interface for deserializing part of proxy pattern
   StoreI.java, interface for serializing part of proxy pattern
   StoreRestoreI.java, interface extended by StoreI and RestoreI
   MyAllTypesFirst.java, simple object extending SerializableObject
   MyALlTypesSecond.java, simple object extending SerializableObject
   ProxyCreator.java, creates proxy object with necessary handler
   SerializableObject.java, tags objects as serializable
   StoreRestoreHandler.java, handler for serializing and deserializing
   Strategy.java, tags strategy objects and defines read and write methods
   XmlStrategy.java, strategy pattern for de/serializing into xml-like format
]

SAMPLE OUTPUT:
[
    ant -buildfile build.xml run -Darg0='checkpoint.txt' -Darg1='200' -Darg2='serdeser' -Darg3='XML'
    [java] Number of unequal objects = 0
    [java] Success!
]

TO COMPILE:

[
  To compile run the command: ant -buildfile build.xml all
  in the directory: young_evan_assign5
]

TO RUN:

[
  Navigate to the directory young_evan_assign5
  Then run command: ant -buildfile build.xml run -Darg0=<CHECKPOINT_FILE> -Darg1=<NUM_OBJECTS>
		   -Darg2=<'SER'/'DESER'/'SERDESER'> -Darg3=<WIRE_METHOD>
]

EXTRA CREDIT:

[
   N/A
]


BIBLIOGRAPHY:

[
  N/A
]

ACKNOWLEDGEMENT:

[
  N/A
]
