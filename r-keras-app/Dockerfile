FROM rocker/r-ver:3.4.4

COPY . /opt/app

RUN r packrat::restore()

CMD rscript src/app.R