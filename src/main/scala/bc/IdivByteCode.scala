package bc

import vm.VirtualMachine

/**
  * Created by Casper on 10/03/2017.
  */
class IdivByteCode extends ByteCode{
  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    */
  override val code: Byte = bytecode("idiv")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val x = vm.pop()._1
    val y = vm.pop()._1
    if(y == 0) {
      throw new ArithmeticException(s"Attempted to divide zero: $x%$y")
    }
    vm.push(x/y)
    vm
  }
}
