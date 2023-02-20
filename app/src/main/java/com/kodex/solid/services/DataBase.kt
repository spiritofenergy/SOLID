package com.kodex.solid.services

import android.util.Log

interface DataBase {
    fun save(data: String)
}

class MySqlDataBase:DataBase{
    override fun save(data: String) {
        Log.d("check", "Сохраняю в Базу $data")
    }

}