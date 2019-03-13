# app.R

library(plumber)

r <- plumb("src/func.R")
r$run(port=5000)