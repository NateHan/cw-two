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
  override def parse(file: String): InstructionList = {
    import scala.io.Source
    val lines = Source.fromFile(file).getLines
    var iList = InstructionList()
    for (line <- lines) {
      iList = iList ++: parseString(line)
    }
    iList
  }

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
    var q: Array[String] = string.split("\n")
    var k = InstructionList()

    for ( str <- q) {
      var vInt: Vector[Int] = Vector[Int]()
      var wordInst: String = str
      if (str.contains(" ")) {
        vInt :+ str.split(" ")(1).toInt
        wordInst = str.split(" ")(0)
      }
      k = k :+ new Instruction(wordInst, vInt)
    }
    k
  }
}
