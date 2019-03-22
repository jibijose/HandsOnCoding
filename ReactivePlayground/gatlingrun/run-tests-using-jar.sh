#!/usr/bin/env bash
java ${JAVA_OPTS} -cp target/gatlingrun.jar io.gatling.app.Gatling -s gatling.test.example.simulation.ExampleSimulation
