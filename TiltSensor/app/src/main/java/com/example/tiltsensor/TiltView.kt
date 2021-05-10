package com.example.tiltsensor

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText

class TiltView(context: Context?) : View(context) {

    private val greenPaint: Paint=Paint()
    private val blackPaint: Paint=Paint()


    init{
        greenPaint.color= Color.GREEN
        blackPaint.style=Paint.Style.STROKE
    }

    private var cX:Float = 0f
    private var cY:Float = 0f
    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX=w/2f
        cY=h/2f
    }

    fun onSensorEvent(event: SensorEvent){
        yCoord = event.values[0]*20 //움직임 너무 작음
        xCoord = event.values[1]*20

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        val paint=Paint()
        paint.isAntiAlias=true

        canvas?.drawCircle(cX, 0F,30F,blackPaint)
        canvas?.drawCircle(xCoord+cX, 0F,30F,greenPaint)


        canvas?.drawCircle(0F, cY,30F,blackPaint)
        canvas?.drawCircle(0F, yCoord+cY,30F,greenPaint)

        canvas?.drawCircle(cX,cY,100f,blackPaint)
        canvas?.drawCircle(xCoord+cX,yCoord+cY,100f,greenPaint)

        canvas?.drawLine(cX-20,cY,cX+20,cY,blackPaint)
        canvas?.drawLine(cX,cX-20,cX,cY+20,blackPaint)

        canvas?.drawText("X: "+(cX/20).toString()+",     Y: "+(cY/20).toString(),0,0,200F,100F,blackPaint)
    }
}