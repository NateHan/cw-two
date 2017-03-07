package bc

import org.scalatest.FunSuite
import factory.VirtualMachineFactory

/**
  * Created by Casper on 07/03/2017.
  */


class PublicByteCodeSuite extends FunSuite with ByteCode{

  val vm = VirtualMachineFactory.virtualMachine

}
