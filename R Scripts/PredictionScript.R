#J48

install.packages("RWeka")
install.packages("rJava")
install.packages("pROC")
install.packages("partykit")

library("RWeka")
library("partykit")
library("pROC")
#getwd()

airlinedata <- read.csv("inputfiles/TrainingPredictionDataset.csv")
str(airlinedata)
airlinedata <- lapply(airlinedata, as.factor)
str(airlinedata)
testdata <- read.csv("inputfiles/TestingPredictionDataset.csv")
print(testdata)
fit <- J48(DELAY ~ CARRIER + ORIGIN_CITY_NAME + DEST_CITY_NAME + YEAR + MONTH +  DAY_OF_MONTH, data = airlinedata, control = Weka_control(M=5,R=TRUE))
fit
predict(fit,data.frame(CARRIER = "UA" ,ORIGIN_CITY_NAME = "Denver, CO",DEST_CITY_NAME = "Santa Ana, CA",YEAR = 2018,MONTH=01,DAY_OF_MONTH =12),interval = "predict")
e <- evaluate_Weka_classifier(fit,newdata= testdata ,cost = matrix(c(0,2,1,0), ncol = 2),numFolds = 10, complexity = TRUE,class = TRUE)
summary(e)
e$details
cm <- e$confusionMatrix
cm