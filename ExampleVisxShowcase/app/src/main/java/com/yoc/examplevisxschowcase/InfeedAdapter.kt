package com.yoc.examplevisxschowcase

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.yoc.examplevisxschowcase.databinding.ItemListArticleBinding
import com.yoc.examplevisxschowcase.databinding.ItemListCreativeAdBinding
import com.yoc.showcase.model.Article
import com.yoc.visx.sdk.VisxAdManager

class InfeedAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var articleList = mutableListOf<Article>()
    private var adViewContainer: View? = null

    companion object {
        private const val INFEED_ADAPTER_TAG = "---> VISX.Feed.Adapter"
        private const val LIST_AD_POSITION_INDEX = 6
        private const val CONTENT = 0
        private const val AD = 1
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CONTENT) {
            ContentHolder(
                ItemListArticleBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        } else {
            AdHolder(
                ItemListCreativeAdBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }
    }

    fun addAll(results: List<Article>?) {
        if (results?.size != 0) {
            articleList.clear()
            results?.let { articleList.addAll(it) }
            notifyDataSetChanged()
        }
    }

    fun displayCreative(visxAdManager: VisxAdManager?) {
        if (visxAdManager != null && visxAdManager.adContainer != null) {
            (context as Activity).runOnUiThread {
                adViewContainer = visxAdManager.adContainer
                Log.i(INFEED_ADAPTER_TAG, "AD Ready for ADAPTER")
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        var additionalContent = 0
        if (articleList.size > 0 && LIST_AD_POSITION_INDEX > 0 && articleList.size > LIST_AD_POSITION_INDEX) {
            additionalContent = articleList.size / LIST_AD_POSITION_INDEX
        }
        return articleList.size + additionalContent
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ContentHolder) {
            viewHolder.bind(articleList[getRealPosition(position)], getRealPosition(position))
        } else if (viewHolder is AdHolder) {
            viewHolder.setIsRecyclable(false)
            viewHolder.bind()
        }
    }

    private fun getRealPosition(position: Int): Int {
        return if (LIST_AD_POSITION_INDEX == 0) {
            position
        } else {
            position - position / LIST_AD_POSITION_INDEX
        }
    }

    /**
     * Getting the type of the item
     * @see ContentHolder or
     * @see AdHolder
     *
     * @param position
     */
    override fun getItemViewType(position: Int): Int {
        if (position > 0 && position % LIST_AD_POSITION_INDEX == 0) {
            return AD
        }
        return CONTENT
    }

    /**
     * Inner class for processing Article Item Content
     */
    inner class ContentHolder(private val binding: ItemListArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var article: Article
        private var pos: Int = 0

        /**
         * Binding the data with this item
         * @see onBindViewHolder
         * @param article data
         * @param position in list
         */
        fun bind(article: Article, position: Int) {
            this.article = article
            this.pos = position

            Log.i(INFEED_ADAPTER_TAG, "ViewHolder == DISPLAY CONTENT")
            binding.title.text = article.title
            binding.description.text = article.description
            binding.publishedOn.text = article.publishedOn
            binding.imageHome.setImageDrawable(article.image)
            binding.containerItem.tag = "article_item $pos"
            binding.containerItem.contentDescription = "article_item"
        }
    }

    /**
     * Inner class for processing AD Creative Item Content
     */
    inner class AdHolder(private val binding: ItemListCreativeAdBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binding and showing AD Creative on screen in list
         */
        fun bind() {
            Log.i(INFEED_ADAPTER_TAG, "ViewHolder == DISPLAY AD")
            if (adViewContainer != null) {
                binding.containerItemCreative.visibility = View.VISIBLE
                binding.containerItemCreative.tag = "visx_ad"
                binding.containerItemCreative.contentDescription = "visx_ad"
                val container =
                    binding.root.findViewById<RelativeLayout>(R.id.containerItemCreative)
                if (adViewContainer!!.parent != null) {
                    (adViewContainer!!.parent as ViewGroup).removeView(adViewContainer)
                }
                container.addView(adViewContainer)
            } else {
                Log.w(INFEED_ADAPTER_TAG, "Ad View Container is NULL")
            }
        }
    }
}