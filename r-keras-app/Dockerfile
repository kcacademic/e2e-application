FROM rocker/r-ver:3.5.0

WORKDIR /opt/app

RUN r -e "options(repos = \
  list(CRAN = 'http://mran.revolutionanalytics.com/snapshot/2019-03-12')); \
  install.packages('plumber')"
  
COPY ./src /opt/app/src

CMD r -e "source('src/app.R')"