package com.papice.iptvsimulator.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val code: String,  // ISO 3166-1 alpha-2 code (e.g., "US", "GB")
    val flag: String = "", // emoji flag or flag URL
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
)
