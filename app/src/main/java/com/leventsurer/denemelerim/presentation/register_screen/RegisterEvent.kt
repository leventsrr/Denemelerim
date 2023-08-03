package com.leventsurer.denemelerim.presentation.register_screen



sealed class RegisterEvent{

    data class SignUp(val userName:String,val email:String,val password:String,val passwordConfirm:String):RegisterEvent()

}
