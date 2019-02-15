@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.kishandonga.snackbar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout

class CustomSnackbar(private val context: Context) {

    private var textColor: Int = Color.WHITE
    private var actionTextColor: Int = Color.WHITE
    private var duration: Int = Snackbar.LENGTH_SHORT
    private var drawable = GradientDrawable()
    private var message: String = ""
    private var padding: Int = 0.toPx(context).toInt()
    private var customView: View? = null
    private var action: ((Snackbar) -> Unit)? = null
    private var customViewAction: ((View) -> Unit)? = null
    private var buttonName: String = ""
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var backgroundColor: Int = Color.TRANSPARENT
    private var borderWidth: Int = 0
    private var borderColor: Int = Color.TRANSPARENT
    private var cornerRadius: Float = -1f
    private lateinit var snackbar: Snackbar
    private var coordinateView: View? = null
    private var typeFaceTextView: Typeface? = null
    private var typeFaceButton: Typeface? = null

    constructor(context: Context, view: View) : this(context) {
        coordinateView = view
    }

    fun textTypeface(textTypeface: Typeface) {
        typeFaceTextView = textTypeface
    }

    fun actionTypeface(actionTypeface: Typeface) {
        typeFaceButton = actionTypeface
    }

    fun withCustomView(customViewAction: ((View) -> Unit)?) {
        this.customViewAction = customViewAction
    }

    fun withAction(@StringRes resId: Int, action: (Snackbar) -> Unit) {
        withAction(context.getString(resId), action)
    }

    fun withAction(actionText: String, action: (Snackbar) -> Unit) {
        this.action = action
        this.buttonName = actionText
    }

    fun paddingRes(@DimenRes dimenId: Int) {
        padding(context.resources.getDimension(dimenId).toInt())
    }

    fun padding(padding: Int) {
        this.padding = padding
    }

    fun duration(duration: Int) {
        this.duration = duration
    }

    fun messageRes(@StringRes message: Int) {
        message(context.getString(message))
    }

    fun message(message: String) {
        this.message = message
    }

    fun customView(@LayoutRes layoutResource: Int) {
        customView(inflater.inflate(layoutResource, LinearLayout(context), false))
    }

    fun customView(view: View) {
        this.customView = view
    }

    fun borderRes(@DimenRes dimenId: Int, @ColorRes colorId: Int) {
        border(context.resources.getDimension(dimenId).toInt(), ContextCompat.getColor(context, colorId))
    }

    fun border(width: Int, @ColorInt color: Int) {
        this.borderWidth = width
        this.borderColor = color
    }

    fun cornerRadiusRes(@DimenRes dimenId: Int) {
        cornerRadius(context.resources.getDimension(dimenId))
    }

    fun cornerRadius(cornerRadius: Float) {
        this.cornerRadius = cornerRadius
    }

    fun backgroundColorRes(@ColorRes colorId: Int) {
        backgroundColor(ContextCompat.getColor(context, colorId))
    }

    fun backgroundColor(@ColorInt color: Int) {
        this.backgroundColor = color
    }

    fun textColorRes(@ColorRes colorId: Int) {
        textColor(ContextCompat.getColor(context, colorId))
    }

    fun textColor(@ColorInt color: Int) {
        this.textColor = color
    }

    //TODO: Pending....
    fun actionTextColor(actionTextColor: Int) {
        this.actionTextColor = actionTextColor
    }

    fun show(): CustomSnackbar {
        if (coordinateView != null && coordinateView is CoordinatorLayout) {
            makeSnackbar(coordinateView!!)
        } else {
            (context as Activity).findViewById<View>(android.R.id.content)?.apply {
                makeSnackbar(this)
            }
        }
        snackbar.show()
        return this
    }

    private fun makeSnackbar(view: View) {
        snackbar = Snackbar.make(view, message, duration)
        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout // Frame Layout
        val snackContentLayout = snackbarLayout.children.first() as SnackbarContentLayout // Linear Layout

        if (backgroundColor != 0) {
            drawable.setColor(backgroundColor)
        } else {
            drawable = snackbarLayout.background as GradientDrawable
        }

        if (cornerRadius != -1f) {
            drawable.cornerRadius = cornerRadius
        } else {
            drawable.cornerRadius = 0f
        }

        if (borderWidth != 0 && borderColor != 0) {
            drawable.setStroke(borderWidth, borderColor)
        } else {
            drawable.setStroke(0, Color.TRANSPARENT)
        }

        val pLeft = snackbarLayout.paddingLeft
        val pRight = snackbarLayout.paddingRight

        snackbarLayout.setBackgroundColor(Color.TRANSPARENT)
        snackbarLayout.setPadding(0, 0, 0, 0)
        snackContentLayout.setPadding(pLeft, 0, pRight, 0)
        snackContentLayout.background = drawable

        if (padding > 0) {
            snackbarLayout.setPadding(padding, 0, padding, padding)
        }

        if (customView == null) {
            val contentLayoutIterator = snackContentLayout.children.iterator()
            val tvSnackbarTextView = contentLayoutIterator.next() as AppCompatTextView
            tvSnackbarTextView.setTextColor(textColor)
            if (typeFaceTextView != null) {
                tvSnackbarTextView.typeface = typeFaceTextView
            }
            val btnSnackbarActionButton = contentLayoutIterator.next() as AppCompatButton
            if (typeFaceButton != null) {
                btnSnackbarActionButton.typeface = typeFaceButton
            }

            if (action != null) {
                snackbar.setAction(buttonName) {
                    action?.invoke(snackbar)
                }
            }
        } else {
            snackContentLayout.visibility = View.GONE
            snackbarLayout.addView(customView)
            customViewAction?.invoke(customView!!)
        }
    }

    inline fun show(func: CustomSnackbar.() -> Unit): CustomSnackbar {
        this.func()
        return this.show()
    }

    private fun Int.toPx(context: Context): Float {
        return this * context.resources.displayMetrics.density
    }

    fun getView(): View? {
        return customView
    }

    fun dismiss() {
        if (::snackbar.isInitialized) {
            snackbar.dismiss()
        }
    }
}