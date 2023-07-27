package com.leventsurer.denemelerim.presentation.login_screen


sealed class LoginEvent{

    data class SignIn(val email:String,val password:String):LoginEvent()

}
