package com.example.mvvm_tmdb.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite") //테이블 이름 favorite
data class Favorite(

    //영화 아이디로 구분
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "title") //영화제목
    var movie_title: String,

    @ColumnInfo(name = "date") //추가한 날짜
    var date: String,

    @ColumnInfo(name = "memo") //메모
    var memo: String,

    @ColumnInfo(name = "photoPath") //사진경로
    var photoPath: String
) {
    constructor() : this(null, "", "", "","")
}