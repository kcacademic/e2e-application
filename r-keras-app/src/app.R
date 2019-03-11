# app.R

library(plumber)

r <- plumb("src/func.R")$run(port=8000)
r$run(port=8000)