package com.farid.proyekfootballeague.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment).commit()
}

fun AppCompatActivity.replaceFragmentWithTag(fragment: Fragment, tag: String, frameId: Int) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment, tag).commit()
}