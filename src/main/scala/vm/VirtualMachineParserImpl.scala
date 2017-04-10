package vm

import java.util.NoSuchElementException

import vendor.{Instruction, ProgramParserImpl}
import bc.ByteCode
import bc.ByteCodeValues
import bc.ByteCodeParserImpl
import bc.InvalidBytecodeException

/**
  * Created by Casper on 25/03/2017.
  */
class VirtualMachineParserImpl extends VirtualMachineParser with ByteCodeValues {

  val byteCodeParser = new ByteCodeParserImpl

  /**
    * Returns a vector of [[ByteCode]].
    *
    * This method parses a file into a vector of bytecode objects.
    * Note, this method should throw a [[InvalidBytecodeException]]
    * if it fails to parse a program file correctly.
    *
    * @param file the file containing a program
    * @return a vector of bytecodes
    */
  override def parse(file: String): Vector[ByteCode] = {
    try {
      val programParser = new ProgramParserImpl()
      val instructionVector: Vector[Instruction] = programParser.parse(file)
      var resultBCVector = Vector[ByteCode]()

      for (instruction <- instructionVector) {
        var instructionString = instruction.name
        if (instruction.args.nonEmpty) {
          instructionString += " " + instruction.args.head.toString
        }
        resultBCVector = resultBCVector ++: parseString(instructionString)
      }
      resultBCVector
    } catch {
      case ex: java.util.NoSuchElementException => ex.printStackTrace()
        throw new InvalidBytecodeException("Trying to parse invalid line")
      case ex2: java.lang.NumberFormatException => ex2.printStackTrace()
        throw new InvalidBytecodeException("Trying to parse invalid line argument")
      case ex3: java.lang.ArrayIndexOutOfBoundsException => ex3.printStackTrace()
        throw new InvalidBytecodeException("Trying to parse line with too many spaces")
    }
  }

  /**
    * Returns a vector of [[ByteCode]].
    *
    * This method parses a string into a vector of bytecode objects.
    * Note, this method should throw a [[InvalidBytecodeException]]
    * if it fails to parse a program string correctly.
    *
    * @param str a string containing a program
    * @return a vector of bytecodes
    */
  override def parseString(str: String): Vector[ByteCode] = {
    var result = Vector[ByteCode]()
    var linesArray = str.split("\n")
    for (line <- linesArray) {
      result = result ++ lineParser(line)
    }
    result
  }

  /**
    * Returns a Vector of [[ByteCode]].
    *
    * This method parses a string that is a single line and turns it into
    * a Vector[ByteCode]. Method will split an iConst instruction
    * into the instruction and its argument,
    * otherwise all others will just be one single entry.
    *
    * @param line a string containing a program
    * @return a vector of bytecodes
    */
  private def lineParser(line: String): Vector[ByteCode] = {
    var result = Vector[ByteCode]()
    if (line.contains(" ")) {
      val wordValue = bytecode(line.split(" ")(0))
      val numValue = line.split(" ")(1).toByte
      result = byteCodeParser.parse(Vector(wordValue, numValue))
    } else {
      val wordValue = bytecode(line)
      result = byteCodeParser.parse(Vector(wordValue))
    }
    result
  }

}
