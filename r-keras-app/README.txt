Package Commands
================
packrat::init()
packrat::snapshot()
packrat::status()
packrat::restore()
packrat::clean()

Install Packages
================
install.packages("packrat")
install.packages("plumber")

Run App
=======
rscript src/app.R

CONTAINERIZATION
================
docker images
docker rmi r-keras:1.0
docker build -t r-keras:1.0 .

docker stop r-keras
docker rm r-keras
docker run --rm --name=r_keras -p 8000:8000 r-keras:1.0