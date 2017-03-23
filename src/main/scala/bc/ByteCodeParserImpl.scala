package bc

import factory.ByteCodeFactoryImpl

/**
  * Created by nathanhanak and Casper on 3/16/17.
  */
class ByteCodeParserImpl extends ByteCodeParser with ByteCodeValues{

  val bcFactory : ByteCodeFactory = new ByteCodeFactoryImpl
  var byteCodeList: Vector[ByteCode] = Vector[ByteCode]()

  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * You should use [[ByteCodeValues.bytecode]] to help translate
    * the individual `Byte`s into a correponding [[ByteCode]].
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
  override def parse(bc: Vector[Byte]): Vector[ByteCode] = {
    for ( byte <- bc ) {

      if (bytecode("iconst") == byte){
        byteCodeList = byteCodeList :+ bcFactory.make(byte, bc.indexOf(byte +1))
      }else {
        byteCodeList = byteCodeList :+ bcFactory.make(byte)
      }
    }
    byteCodeList
  }

}

/*
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
*/