package vendor

/**
  * Created by Casper on 03/03/2017.
  */
class ProgramParserImpl extends ProgramParser {

  override type InstructionList = Vector[Instruction]

  private def instructionList(inst: Instruction*) = Vector(inst: _*)

  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    *
    *         vendor parser should parse a program file correctly
    *
    */
  override def parse(file: String): InstructionList = {
    import scala.io.Source
    val lines = Source.fromFile(file).getLines
    var iList = instructionList()
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
    *         vendor parser should parse a program string correctly
    *
    */
  //@throws(classOf[java.lang.NumberFormatException])
  override def parseString(string: String): InstructionList = {
    var q: Array[String] = string.split("\n")
    var k = instructionList()

    for (str <- q) {
      var vInt: Vector[Int] = Vector[Int]()
      var wordInst: String = str
      if (str.contains(" ")) {
        val splitted = str.split(" ")
        wordInst = splitted(0)
        vInt = vInt :+ splitted(1).toInt
      }
      k = k :+ new Instruction(wordInst, vInt)
    }
    k
  }
}
