package vendor

import scala.collection.mutable.ListBuffer

/**
  * Created by Casper on 03/03/2017.
  */
class ProgramParserImpl extends ProgramParser{

  override type InstructionList = Vector[Instruction]
  private def InstructionList(inst: Instruction*) = Vector(inst: _*)

  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    *
    * vendor parser should parse a program file correctly
    *
    */
  override def parse(file: String): InstructionList = ???

  /**
    * Parses a string representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param string the string to parse
    * @return an instruction list
    *
    * vendor parser should parse a program string correctly
    *
    */
  override def parseString(string: String): InstructionList = {
    var k = InstructionList()
    var v = Vector[Int](1,2,3,4)
    var q: Array[String] = string.split("\n")
    q.foreach(a => k = k :+ new Instruction(a,v))
    k
  }
}
