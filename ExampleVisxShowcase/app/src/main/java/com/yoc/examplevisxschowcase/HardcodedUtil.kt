package com.yoc.examplevisxschowcase

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.yoc.showcase.model.Article

class HardcodedUtil {

    companion object {
        fun getArticles(context: Context): List<Article> {
            val articleList: ArrayList<Article> = ArrayList()
            var title: String
            var image: Drawable

            for (number in 1..11) {
                val topic = (0..5).random()
                when (topic) {
                    0 -> {
                        title = "Test title banana"
                        image = AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_launcher_foreground
                        )!!
                    }
                    1 -> {
                        title = "Test title orange"
                        image =
                            AppCompatResources.getDrawable(
                                context,
                                R.drawable.ic_launcher_foreground
                            )!!
                    }
                    2 -> {
                        title = "Test title apple"
                        image = AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_launcher_foreground
                        )!!
                    }
                    3 -> {
                        title = "Test title strawberry"
                        image = AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_launcher_foreground
                        )!!
                    }
                    4 -> {
                        title = "Test title plum"
                        image = AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_launcher_foreground
                        )!!
                    }
                    else -> {
                        title = "Test title pear"
                        image =
                            AppCompatResources.getDrawable(
                                context,
                                R.drawable.ic_launcher_foreground
                            )!!
                    }
                }
                articleList.add(
                    Article(
                        title,
                        context.getString(R.string.description_dummy) + " $number",
                        "$number.05.2020",
                        image
                    )
                )
            }
            return articleList
        }

        fun getOneArticle(context: Context): List<Article> {
            val articleList: ArrayList<Article> = ArrayList()
            articleList.add(
                Article(
                    "Test title article",
                    context.getString(R.string.description_dummy) + " 5",
                    "05.05.2022",
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_launcher_foreground
                    )!!
                )
            )
            return articleList
        }
    }

}
