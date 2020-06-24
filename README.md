[![Verify Build Workflow](https://github.com/Apicurio/apicurio-data-models/workflows/Verify%20Build%20Workflow/badge.svg)](https://github.com/Apicurio/apicurio-data-models/actions?query=workflow%3A%22Verify+Build+Workflow%22)
[![Automated Release Notes by gren](https://img.shields.io/badge/%F0%9F%A4%96-release%20notes-00B2EE.svg)](https://github-tools.github.io/github-release-notes/)

# Apicurio Data Models Library (Java & Typescript)

## What is it?
This project is a library (Java and JavaScript compatible) to implement data models for OpenAPI and AsyncAPI.

You can use this library to read an OpenAPI or AsyncAPI document, resulting in an instance of a data model. 
The data model can then be read or manipulated. It can also be validated.

The data model can be accessed directly, but there is also a robust visitor pattern available for more 
advanced analysis or transformation of the model.

This project is licensed under the [Apache License 2.0](LICENSE).

## Usage
For details on how to use the library, see the documentation included with the library on
npmjs.com:

  [https://www.npmjs.com/package/apicurio-data-models](https://www.npmjs.com/package/apicurio-data-models)

This documentation can also be found in this repository here:

  [./module/README.md](https://github.com/Apicurio/apicurio-data-models/blob/master/module/README.md)

## Project Goals

### Purpose
The primary goal of this project is to be the official library to perform operations on OpenAPI and 
AsyncAPI documents.  Both for the purpose of analyzing/processing (including validation) a document 
as well as making changes to it.

The library should be usable as broadly as possible, both as a dependency of other Java, Typescript or
Javascript projects, as well as usable in a Java, Node.js, or browser based application.

### Usage Scenarios
Some example usage scenarios for this library include:
 
* An editor which can be used to create new documents (or modify existing ones).
* Auto-generate documentation from a document.
* Transform documents into other versions/formats.
* Auto-generate API clients.

### Dependencies
An important design decision is that this project does not have any runtime dependencies.  This means that 
you can use the library directly without including any other libraries. It also means you can use the 
project as a dependency in your projects without worrying about conflicts or dependency bloat.

## Project Status
Currently, the data model fully supports the following formats:

* OpenAPI 2.0
* OpenAPI 3.0.x
* AsyncAPI 2.0.0

Barring oversights or bugs, the full OpenAPI/AsyncAPI document can be read and written successfully.  In 
addition, the visitor pattern has been fully implemented (along with both up and down traversing).

Validation of the OpenAPI/AsyncAPI specification exists, with all of the rules outlined in the spec having 
been implemented.

Please refer to the github repository's Issues and other resources for more information on the
current status of the project.

## OpenAPI Versions
One of the design goals of this library is to simultaneously support multiple versions of the OpenAPI
specification.  At the time of this writing, there are two versions of the OpenAPI specification:

* [OpenAPI Version 2.0](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md)
* [OpenAPI Version 3.0.2](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.2.md)

Both of these versions are supported by the library.  As new versions of the specification are 
released, the library will be updated to include support for them.

## AsyncAPI Versions
As of the time of this writing, the AsyncAPI specification is in the process of releasing version 2.0.0.
This will be the first version of AsyncAPI supported by this library.  As other versions are released,
the library will be updated to support them.  There are currently no plans to support older versions.

* [AsyncAPI Version 2.0.0](https://www.asyncapi.com/docs/specifications/2.0.0/)

## Building the Library
This section explains how to build, package, test, and publish the library.  If you are a developer
looking to make changes, this is a great place to start.

### Pre-Requisites
In order to build the library you will need to install the following tools:

* Java 8 (versions of Java greater than 8 can be used when **running** the library, but not for building it)
* Maven 3.5+
* Node.js 8.11+
* Yarn 1.15+

### Clone and Configure
The first thing to do (obviously) is clone the repository.  Once you've cloned the git repository,
you can use maven to build the library into its two forms:  a Java JAR and a javascript library module.
This library is written using Java, but is then [transpiled from Java into Typescript using jsweet](http://www.jsweet.org/)
and then compiled and bundled using typescript and rollup.  All of this is done as part of the standard
maven build process:

```
git clone https://github.com/Apicurio/apicurio-data-models.git
cd apicurio-data-models
mvn clean package
```

### Test the Library
All testing is integrated into the maven build, so the standard `mvn clean package` will also execute
all unit tests.  If the maven build succeeds, then you know everything worked!


## Contribute Fixes and Features
This project is open source, and we welcome anybody who wants to participate and contribute!

### Get the code
The easiest way to get started with the code is to [create your own fork](http://help.github.com/forking/)
of this repository, and then clone your fork:

```bash
$ git clone git@github.com:<you>/apicurio-data-models.git
$ cd apicurio-data-models
$ git remote add upstream git://github.com/Apicurio/apicurio-data-models.git
```

At any time, you can pull changes from the upstream and merge them onto your master:

```bash
$ git checkout master       # switches to the 'master' branch
$ git pull upstream master  # fetches all 'upstream' changes and merges 'upstream/master' onto your 'master' branch
$ git push origin           # pushes all the updates to your fork, which should be in-sync with 'upstream'
```

The general idea is to keep your 'master' branch in-sync with the 'upstream/master'.

### Track Your Change
If you want to fix a bug or make any changes, please log an issue in the github 
[Issue Tracker](https://github.com/Apicurio/apicurio-data-models/issues) describing the bug or new 
feature. Then we highly recommend making the changes on a topic branch named with the issue 
number. For example, this command creates a branch for issue #7:

```bash
$ git checkout -b apicurio-data-models-7
```

After you're happy with your changes and all unit tests run successfully, commit your changes 
on your topic branch. Then it's time to check for and pull any recent changes that were made in
the official repository since you created your branch:

```bash
$ git checkout master         # switches to the 'master' branch
$ git pull upstream master    # fetches all 'upstream' changes and merges 'upstream/master' onto your 'master' branch
$ git checkout apicurio-data-models-7  # switches to your topic branch
$ git rebase master           # reapplies your changes on top of the latest in master
                              # (i.e., the latest from master will be the new base for your changes)
```

If the pull grabbed a lot of changes, you should rerun the tests to make sure your changes are 
still good.  You should then push your changes to your fork, and then 
[generate a pull-request](http://help.github.com/pull-requests/) to submit your contribution:

```bash
$ git push origin apicurio-data-models-7         # pushes your topic branch into your public fork
```
