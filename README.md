# BasicIRSystem

This progrm is an experimental implementation of a retrieval system based on vector space model.
For a given set of topics, the system is expected to rank the documents according to deceasing relevance to the topics.

This project is made using JAVA and Maven.

## Run 
 - execute the command line "mvn compile" in the ir01 folder
 - execute the run.sh script with the following arguments :
        ° -q topics.xml     -- a file including topics in the TREC format
        ° -d document.lst   -- a file including document filenames
        ° -df /doc_lang     -- the folder path containing the documents listed by the -d argument
        ° -r run            -- a label identifying the experiment, here, we use only run_0
        ° -o sample.res     -- an output file

        The execution of the program should look like : 
        ./run.sh -q ../A1/topics-test_en.xml -d ../A1/documents_en.lst -df ../A1/documents_en -r run_0 -o ../test.res
