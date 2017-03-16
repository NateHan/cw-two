package bc
import vm.VirtualMachine

/**
  * Created by Casper on 10/03/2017.
  */
class IremByteCode extends ByteCode{
  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    */
  override val code: Byte = bytecode("irem")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val x = vm.pop()
    val y = vm.pop()
    vm.push(x%y)
    this
  }
}