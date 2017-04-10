package bc

import factory.ByteCodeFactoryImpl

import scala.collection.mutable.ListBuffer

/**
  * Created by nathanhanak and Casper on 3/16/17.
  */
class ByteCodeParserImpl extends ByteCodeParser with ByteCodeValues {

  val bcFactory: ByteCodeFactory = new ByteCodeFactoryImpl

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
    var byteCodeList: Vector[ByteCode] = Vector[ByteCode]()

    var inconstIsPresent: Boolean = false
    for (i <- bc.indices) {

      if (!inconstIsPresent) {
        if (bytecode("iconst") == bc(i)) {
          inconstIsPresent = true
          byteCodeList = byteCodeList :+ bcFactory.make(bc(i), bc(i + 1).toInt)
        } else {
          byteCodeList = byteCodeList :+ bcFactory.make(bc(i))
        }

      } else {
        inconstIsPresent = false
      }

    }
    byteCodeList
  }

}
