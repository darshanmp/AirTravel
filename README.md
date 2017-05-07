# Airtravel Analysis using MapReduce.

Authors:
- Abhilash Udayshankar
- Darshan Masti Prakash


# DataSets used:
Performance Dataset.csv
Prediction Dataset.csv
Airline-Safety dataset.csv

The input arguments are given as program arguments. Our fisrt Input is the performance dataset to the Mapper1.
Input File Locations: 
- args[0] : Prediction dataset
- args[1] : Reducer1 output to input to Mapper2 
- args[2] : Reducer 2 output


Our project has 3 Outputs

- Output 1: Source-destination-airline along with performance , safety rating
- output2: When is the best time to travel, aiports with least delay and airlines with least delays
- Output 3: Prediction model using R and J48 Decision Tree

- The output 1 is available Reducer2 output
- The output 2 is available in DelayOutput folder
- The output 3 is generated from R Script.

Procedure to run the project

- 1) Extract the project files.
- 2) Add the arguments for input and outputas mentioned above in the IDE " Run Configurations "
- 3) Compile and Run the project.
- 4) Outputs will be generated in the output folder as mentioned.
- 5) Using R studio, the R Script can be executed.
- 6) The input for R Script can be changed and tested for various input variables such as source,destination, airline, year, month and day.
- 7) The R Script output will be visible in R Studio. 

		
