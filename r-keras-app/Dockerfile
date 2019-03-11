FROM rocker/r-ver:3.4.4

COPY . /opt/app

RUN R packrat::restore()

CMD R plumb("src/app.R")$run(port=8000)