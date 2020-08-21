package io.github.dvegasa.cp2020rnd.storage

import android.graphics.drawable.Drawable
import io.github.dvegasa.cp2020rnd.R

/**
 * Created by Ed Khalturin @DVegasa
 */

data class User (
    val name: String,
    val ava: Int,
    val phone: String
)

val users = listOf(
    User("Ольга Побединская", R.drawable.ava1, "9001234561"),
    User("Иван Стародубов", R.drawable.ava2  , "9001234562"),
    User("Катя Побединская", R.drawable.ava3, "9001234563"),
    User("Виктор Иванов", R.drawable.ava4, "9001234564"),
    User("Ангелина Ливцова", R.drawable.ava5, "9001234565"),
    User("Наталья Щербакова", R.drawable.ava6, "9001234566")
)