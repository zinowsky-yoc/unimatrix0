package com.yoc.showcase.model

import android.graphics.drawable.Drawable

/**
 * This class defines an Creative data object used create an AD Object.
 */
class Creative
/**
 * Setting of the Article exclusively done through the constructor.
 * @param id            ID of the AD Unit
 * @param title         Title of the creative
 * @param description   Description of the creative
 * @param image         AD image
 */
(val id: Int, val title: String, val description: String, val image: Drawable, val tag: String)