package com.example.mvvm_tmdb.ui.MyPage.Add.contact

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm_tmdb.ui.MyPage.Add.contact.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}