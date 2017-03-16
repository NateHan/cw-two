package bc

import org.scalatest.FunSuite
import factory.VirtualMachineFactory
import vm.VirtualMachine

/**
  * Created by Casper and Nathan on 07/03/2017.
  */
class PublicByteCodeSuite extends FunSuite {

  val vm = VirtualMachineFactory.virtualMachine

  test("[1] an iConst byte takes an integer and pushes it on the vm stack") {
    val iConstByteTest: ByteCode = new IconstByteCode
    iConstByteTest.execute(vm)
    //vm.push(testInt)
    //iConstByteTest.code = 100
    //iConstByteTest.execute(vm)
    assert(vm.state(0) == iConstByteTest.code)
  }
}
