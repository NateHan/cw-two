package factory

import bc.{ByteCodeFactory, ByteCodeParser, ByteCodeParserImpl}
import vendor.{ProgramParser, ProgramParserImpl}
import vm.{VirtualMachine, VirtualMachineImpl, VirtualMachineParser, VirtualMachineParserImpl}



//import vm.{VirtualMachine, VirtualMachineParser}

/**
  * The `VirtualMachineFactory` follows the *factory pattern*. It provides
  * methods for each of the important parts in this assignment. You must
  * implement each method such that it returns an object of the correct type.
  */
object VirtualMachineFactory {

  def byteCodeFactory: ByteCodeFactory = new ByteCodeFactoryImpl

  def vendorParser: ProgramParser = new ProgramParserImpl()

  def byteCodeParser: ByteCodeParser = new ByteCodeParserImpl

  def virtualMachineParser: VirtualMachineParser = new VirtualMachineParserImpl

  def virtualMachine: VirtualMachine = new VirtualMachineImpl()
}
