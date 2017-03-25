package vm
import bc.{ByteCode, ByteCodeParserImpl, ByteCodeValues, InvalidBytecodeException}
import vendor.ProgramParserImpl



/**
  * Created by Casper on 25/03/2017.
  */
class VirtualMachineParserImpl extends VirtualMachineParser with ByteCodeValues{
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
    val programParser = new ProgramParserImpl
    val instructionVector = programParser.parse(file)
    var resultBCVector =  Vector[ByteCode]()

    for(instruction <- instructionVector){
      resultBCVector = resultBCVector :+ parseString(instruction.name)

      if(instruction.args.nonEmpty){
        resultBCVector = resultBCVector :+ byteCodeParser.parse(instruction.args.head.toByte)
      }

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
    byteCodeParser.parse(Vector[Byte](bytecode(str)))
  }
}
