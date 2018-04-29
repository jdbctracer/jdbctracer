# jdbctracer
Simple tracing JDBC driver wrapper for any JDBC driver

## Objective

I wanted to have a simple yet sufficient driver-independent aid for tracing JDBC calls. Of course, the various JDBC drivers do this themselves, but each of those does it in a different fashion.... When working with multiple drivers concurrently, this gets annoying.

## Implementation

The jdbctracer JDBC driver is simply a wrapper around any other JDBC driver so that tracing can be enabled dynamically at runtime.
Tracing also involves measurement of timings, but tries to stay away from fancy performance-impacting features. Performance is key!

## Usage

I wanted to create a simple driver without any own functionality besides the tracing. To enable tracing, simply put the drivers code jar into the class path, so that it is picked up by the Java VM.

In order to turn on tracing, specify jdbctracer(INFO) in front of your JDBC connection url. The jdbctracer driver will recognize the format and produce a wrapped connection performing the tracing.

Example: 

```jdbc:oracle://123.456.789.10@scott```  ==>  ```jdbctracer(INFO)jdbc.oracle://123.456.789.10@scott```


In order to control the amount of tracing output, there is three possibilities: 
* Do not specify jdbctracer() in front of the original JDBC url: The original driver is picked up.
* Specify jdbctracer() in front of the original JDBC url: The connections are wrapped using the log level from the jdbctracer logging. 
* Specify jdbctracer(X) in front of the original JDBC url: The log level X is used regardless of the configured logging for the jdbctracer. X=NONE|ERROR|WARN|INFO|DEBUG|ALL (INFO beeing standard, DEBUG giving more information, ALL beeing most verbose)

The jdbctracer driver uses 'JDBC' as its marker. When ```NONE``` is specified as logging level, no wrapping occurs.

## What it does not do

It does not trace every JDBC interface call. I deemed it not worthwhile as it can cause significant overhead and significant amount of output. But hey - open source: If your needs are not fulfilled, either create a pull request (preferred) or fork!

The integration in logging facilies is not completely perfect, but sufficient enough for my needs.

