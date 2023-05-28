package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout


class MyAnalogClock @JvmOverloads constructor(
    context: Context ,
    attrs: AttributeSet? = null ,
    defStyleAttr: Int = 0
) : ConstraintLayout(context , attrs , defStyleAttr) {
    val clock = CustomAnalogClock(context , null)

    init {
        addView(clock)
    }

    fun setTime(time: Long) {
        val h = time / 60 / 60
        val m = time / 60 % 60
        val s = time % 60
        clock.setTime(h.toInt() , m.toInt() , s.toInt())
        clock.invalidate()
    }
}

class CustomAnalogClock : View {
    constructor(context: Context , attrs: AttributeSet?) : super(context , attrs)
    constructor(context: Context , attrs: AttributeSet? , defStyleAttr: Int) : super(
        context ,
        attrs ,
        defStyleAttr
    )

    private var mHeight = 0
    private var mWidth: Int = 0
    private val mClockHours = intArrayOf(
        1 ,
        2 ,
        3 ,
        4 ,
        5 ,
        6 ,
        7 ,
        8 ,
        9 ,
        10 ,
        11 ,
        12
    )
    private var mPadding = 0
    private val mNumeralSpacing = 0
    private var mHandTruncation = 0
    private var mHourHandTruncation: Int = 0
    private var mRadius = 0
    private val mRect: Rect = Rect()

    private var curHour = 0
    private var curMin = 0
    private var curSec = 0

    private lateinit var mPaint: Paint

    override fun onDraw(canvas: Canvas) {
        if (!::mPaint.isInitialized) {
            mPaint = Paint()
            mHeight = height
            mWidth = width
            mPadding = mNumeralSpacing + 50
            val minAttr = Math.min(mHeight , mWidth)
            mRadius = minAttr / 2 - mPadding
            mHandTruncation = minAttr / 20
            mHourHandTruncation = minAttr / 17
        }
        canvas.drawColor(BACKGROUND_COLOR)

        mPaint.reset()
        mPaint.setColor(Color.MAGENTA)
        mPaint.setStyle(Paint.Style.STROKE)
        mPaint.setStrokeWidth(3F)
        mPaint.setAntiAlias(true)
        canvas.drawCircle(
            (mWidth / 2).toFloat() ,
            (mHeight / 2).toFloat() , (mRadius + mPadding - 10).toFloat() , mPaint
        )

        mPaint.setStyle(Paint.Style.FILL)
        mPaint.let {
            canvas.drawCircle(
                (mWidth / 2).toFloat() ,
                (mHeight / 2).toFloat() ,
                12F ,
                it
            )
        }
        val fontSize =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP , 12F , resources.displayMetrics)
                .toInt()
        mPaint.textSize = fontSize.toFloat()

        for (hour in mClockHours) {
            val tmp = hour.toString()
            mPaint.getTextBounds(tmp , 0 , tmp.length , mRect)
            val angle = Math.PI / 6 * (hour - 3)
            val x = (mWidth / 2 + Math.cos(angle) * mRadius - mRect.width() / 2).toInt()
            val y = (mHeight / 2 + Math.sin(angle) * mRadius + mRect.height() / 2).toInt()
            canvas.drawText(
                hour.toString() ,
                x.toFloat() ,
                y.toFloat() ,
                mPaint
            )
        }
        drawlines(canvas)

    }

    private fun drawHandLine(canvas: Canvas , momet: Int , handRadius: Int , color: Int) {
        val angle = Math.PI * momet / 30 - Math.PI / 2
        mPaint.color = color
        canvas.drawLine(
            (mWidth / 2).toFloat() ,
            (mHeight / 2).toFloat() ,
            (mWidth / 2 + Math.cos(angle) * handRadius).toFloat() ,
            (mHeight / 2 + Math.sin(angle) * handRadius).toFloat() ,
            mPaint!!
        )
    }

    private fun drawlines(canvas: Canvas) {
        drawHandLine(
            canvas ,
            curHour * 5 ,
            mRadius - mHandTruncation - mHourHandTruncation ,
            HOUR_LINE_COLOR
        )
        drawHandLine(canvas , curMin , mRadius - mHandTruncation , MINUTE_LINE_COLOR)
        drawHandLine(canvas , curSec , mRadius - mHandTruncation , SECOND_LINE_COLOR)
    }

    fun setTime(hour: Int , min: Int , sec: Int) {
        curHour = hour
        curMin = min
        curSec = sec
    }

    companion object {
        private const val BACKGROUND_COLOR = Color.BLUE
        private const val HOUR_LINE_COLOR = Color.WHITE
        private const val MINUTE_LINE_COLOR = Color.RED
        private const val SECOND_LINE_COLOR = Color.YELLOW
    }


}