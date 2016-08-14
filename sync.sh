#!/usr/bin/env bash

sbt "mongoDrop regions"
sbt "mongoDrop promotions"
sbt "mongoDrop companies"

sbt "mongoImport regions"
sbt "mongoImport promotions"
sbt "mongoImport companies"