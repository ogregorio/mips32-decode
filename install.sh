#!/bin/bash
cd src/
javac call/*.java
javac decoders/*.java
javac files/*.java
javac identifier/*.java
javac treatment/*.java
javac *.java
jar cvfm ../mips32-decode.jar META-INF/Manifest.mf call/Mips32Decode.class decoders/DecodeOpcode.class decoders/DecodeRegister.class files/ReadArchive.class files/WriteArchive.class files/Test.class identifier/RegisterIdentifier.class treatment/ASCIIToInt.class treatment/BinaryConversor.class treatment/InstructionTreatment.class MIPS32.class
