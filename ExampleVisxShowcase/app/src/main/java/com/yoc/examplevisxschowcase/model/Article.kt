package com.yoc.showcase.model

import android.graphics.drawable.Drawable

/**
 * This class defines an Article data object used to populate list.
 */
class Article
/**
 * Setting of the Article exclusively done through the constructor.
 * @param title         name given to the article
 * @param description   description given to the article
 * @param publishedOn   date of publishing the article
 * @param image         article image
 */
(var title: String, val description: String, var publishedOn: String, var image: Drawable)