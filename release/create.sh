#!/bin/bash

if [ "x$1" = "x" ]; then
    printf "=> Please enter release name\n"
    exit
fi

APP_NAME="GeneralAverage"
DATE=`date "+%Y-%m-%d"`
TMP=$APP_NAME.$DATE.$1

mkdir $TMP
mkdir $TMP/data
mkdir $TMP/preferences

cp ../data/persons_big.dat $TMP/data/demodata.dat
cp -r ../languages $TMP/
cp -r ../src $TMP/
cp -r ../uploads $TMP/
cp -r ../resources $TMP/
cp ../out/artifacts/$APP_NAME/$APP_NAME.jar $TMP/$APP_NAME.$1.jar

zip -r $TMP.zip $TMP
rm -r $TMP
