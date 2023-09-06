package com.leventsurer.denemelerim.presentation.common.database






sealed class DatabaseEvent{

    data class AddNewUser(val userUid:String,val userName:String):DatabaseEvent()
    data class GetTytExams(val userUid: String):DatabaseEvent()
    data class GetAytExams(val userUid: String):DatabaseEvent()
    data class DeleteUserAccount(val userUid: String):DatabaseEvent()


}