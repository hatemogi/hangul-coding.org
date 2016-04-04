#!/bin/sh
LOCAL=http://0.0.0.0:3000
DEST=gh-pages
GET=http
FILES="유니코드 404 작성자 언어 도구 도와주기 소통"

$GET $LOCAL> $DEST/index.html

for f in $FILES; do
    $GET $LOCAL/$f.html > $DEST/$f.html
done

RES=resources/public

mkdir -p $RES/md $RES/src
cp $RES/묶음.css $RES/묶음.js $DEST/
cp -r $RES/md/* $DEST/md/
cp -r $RES/src/* $DEST/src/
