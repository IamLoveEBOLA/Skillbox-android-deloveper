package com.zhigaras.reddit.presentation.screens.subreddits

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.skillbox.reddit.R
import com.skillbox.reddit.databinding.PostItemBinding
import com.skillbox.reddit.domain.model.ImagePostEntity
import com.skillbox.reddit.domain.model.PostEntity
import com.skillbox.reddit.domain.model.TextPostEntity

class PostViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CardView(context, attrs, defStyle) {
    
    private var binding: PostItemBinding
    
    init {
        binding = PostItemBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
    
    fun setupView(
        post: PostEntity ,
        onPostClick: (String) -> Unit ,
        onVoteClick: (Int, String) -> Unit ,
        onAuthorClick: (String) -> Unit
    ) {
        fillView(post)
        setupShareClickListener(post)
        setupOnRootClickListener(post.id, onPostClick)
        setupVoteButtonsClickListener(post.isLikedByUser.isLiked) { dir -> onVoteClick(dir, post.id) }
        setUpOnAuthorClick(post.author, onAuthorClick)
    }
    
    fun fillView(post: PostEntity) {
        with(binding) {
            postScore.text = post.score
            commentsNumber.text = post.numComments.toString()
            if (post.isLikedByUser.isLiked == true) voteUpButton.setColorFilter(context.getColor(R.color.green))
            if (post.isLikedByUser.isLiked == false) voteDownButton.setColorFilter(context.getColor(R.color.red))
            subreddit.text = post.subreddit
            author.text = post.author
            title.text = post.title
            
            if (post is TextPostEntity) {
                contentTextView.visibility = View.VISIBLE
                contentTextView.text = post.selfText
                contentImageView.visibility = View.GONE
            }
            if (post is ImagePostEntity) {
                contentImageView.visibility = View.VISIBLE
                Glide.with(contentImageView)
                    .load(post.imageUrl)
                    .into(contentImageView)
                contentTextView.visibility = View.GONE
            }
        }
    }
    
    fun setupShareClickListener(post: PostEntity) {
        binding.shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).also {
                it.putExtra(Intent.EXTRA_TEXT, post.selfUrl)
                it.type = "text/plain"
            }
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    fun setUpOnAuthorClick(userName: String, onAuthorClick: (String) -> Unit) {
        binding.author.setOnClickListener {
            onAuthorClick(userName)
        }
    }
    
    fun setupOnRootClickListener(postId: String, onPostClick: (String) -> Unit) {
        binding.root.setOnClickListener {
            onPostClick(postId)
        }
    }
    
    fun setupVoteButtonsClickListener(isLikedByUser: Boolean?, onVoteButtonsClick: (Int) -> Unit) {
        binding.voteUpButton.setOnClickListener {
            if (isLikedByUser == true) onVoteButtonsClick(0)
            else onVoteButtonsClick(1)
        }
        binding.voteDownButton.setOnClickListener {
            if (isLikedByUser == false) onVoteButtonsClick(0)
            else onVoteButtonsClick(-1)
        }
    }
}