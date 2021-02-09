package com.example.todo.todonotes.Model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Items_table")
data class Item (
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="content") val content: String,

)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}