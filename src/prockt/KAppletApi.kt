package prockt

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import java.awt.Color

open class KAppletApi: PApplet() {

    companion object {
        const val DEFAULT_SIZE = 600
        val BLACK = Color.BLACK.rgb
        val WHITE = Color.WHITE.rgb
    }

    //Sketch

    fun size(size: Int){
        size(size, size)
    }

    fun translate(x: Number, y: Number){
        translate(x.toFloat(), y.toFloat())
    }

    fun overlay(color: Int, alpha: Number){
        fill(color, alpha)
        rect(-1f, -1, width.toFloat() + 1, height.toFloat() + 1)
    }

    fun color(color: String): Int{
        return Color.decode(color).rgb
    }

    fun fill(color: String){
        fill(color(color))
    }

    fun fill(color: String, alpha: Number){
        fill(color(color), alpha.toFloat())
    }

    fun fill(color: Int, alpha: Number){
        fill(color, alpha.toFloat())
    }

    fun stroke(color: String){
        stroke(color(color))
    }

    fun stroke(color: Int, alpha: Number){
        stroke(color, alpha.toFloat())
    }

    fun colorLerp(startColor: Int, endColor: Int, amount: Float): Int{
        return lerpColor(startColor, endColor, amount, PConstants.RGB)
    }

    fun colorLerp(startColor: String, endColor: String, amount: Float): Int{
        return lerpColor(color(startColor), color(endColor), amount, PConstants.RGB)
    }

    fun colorLerp(startColor: String, endColor: String, index: Number, range: Number): Int{
        return lerpColor(color(startColor), color(endColor), index.toFloat()/range.toFloat(), PConstants.RGB)
    }

    //Drawing
    fun ellipse(x: Number, y: Number, w: Number, h: Number){
        ellipse(x.toFloat(), y.toFloat(), w.toFloat(), h.toFloat())
    }

    fun line(x1: Number, y1: Number, x2: Number, y2: Number){
        line(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat())
    }

    fun line(aCoord: Coord, bCoord: Coord){
        line(aCoord.x, aCoord.y, bCoord.x, bCoord.y)
    }

    fun rect(x: Number, y: Number, width: Number, height: Number){
        rect(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat())
    }

    fun square(x: Number, y: Number, diameter: Number){
        rect(x, y, diameter, diameter)
    }

    fun circle(coord: Coord, diameter: Number){
        ellipse(coord.x, coord.y, diameter.toFloat(), diameter.toFloat())
    }

    fun circle(x: Number, y: Number, diameter: Number){
        ellipse(x.toFloat(), y.toFloat(), diameter.toFloat(), diameter.toFloat())
    }

    fun circleLine(x1: Number, y1: Number, x2: Number, y2: Number, gapLength: Number, circleDiam: Number){

        val v1 = Coord(x1.toFloat(), y1.toFloat())
        val v2 = Coord(x2.toFloat(), y2.toFloat())
        val distance = v1.dist(v2)

        val dashCount = distance/gapLength.toFloat()

        for (i in 0..dashCount.toInt()) {
            val x = lerp(x1.toFloat(), x2.toFloat(), (i + 0.5f) / dashCount)
            val y = lerp(y1.toFloat(), y2.toFloat(), (i + 0.5f) / dashCount)
            circle(x, y, circleDiam)
        }
    }

    /*
    For more control and performance use this lib instead: https://github.com/garciadelcastillo/-dashed-lines-for-processing-
 */
    fun dashedLine(x1: Number, y1: Number, x2: Number, y2: Number, dashLength: Number){

        val v1 = Coord(x1.toFloat(), y1.toFloat())
        val v2 = Coord(x2.toFloat(), y2.toFloat())
        val distance = v1.dist(v2)

        val dashCount = distance/dashLength.toFloat()

        for (i in 0..dashCount.toInt()  step 3) {
            val xA = lerp(x1.toFloat(), x2.toFloat(), (i + 0.5f) / dashCount)
            val yA = lerp(y1.toFloat(), y2.toFloat(), (i + 0.5f) / dashCount)
            val xB = lerp(x1.toFloat(), x2.toFloat(), (i + 1.5f) / dashCount)
            val yB = lerp(y1.toFloat(), y2.toFloat(), (i + 1.5f) / dashCount)

            line(xA, yA, xB, yB)
        }
    }

    fun dottedLine(x1: Number, y1: Number, x2: Number, y2: Number, gapLength: Number){

        val v1 = Coord(x1.toFloat(), y1.toFloat())
        val v2 = Coord(x2.toFloat(), y2.toFloat())
        val distance = v1.dist(v2)

        val dashCount = distance/gapLength.toFloat()

        for (i in 0..dashCount.toInt()) {
            val x = lerp(x1.toFloat(), x2.toFloat(), (i + 0.5f) / dashCount)
            val y = lerp(y1.toFloat(), y2.toFloat(), (i + 0.5f) / dashCount)
            point(x, y)
        }
    }

    fun image(image: PImage, x: Number, y: Number){
        image(image, x.toFloat(), y.toFloat())
    }

    fun image(image: PImage){
        image(image, 0f, 0f)
    }

    //Maths

    fun map(value: Number, start1: Number, stop1: Number, start2: Number, stop2: Number): Float{
        return PApplet.map(value.toFloat(), start1.toFloat(), stop1.toFloat(), start2.toFloat(), stop2.toFloat())
    }

    fun random(max: Int): Int{
        return random(max.toFloat()).toInt()
    }

    fun random(min: Int, max: Int): Int{
        return (random(min.toFloat(), max.toFloat()) + 0.5f).toInt()
    }

    //todo - there must be a better way
    fun randomWeightedLarge(max: Int): Int{
        val largeWeights = intArrayOf(90, 80, 80, 70, 70, 70, 60, 60, 60, 60, 50, 50, 50, 50, 50, 40, 40, 40, 40, 40, 40, 30, 30, 30, 30, 30, 30, 30, 20, 20, 20, 20, 20, 20, 20, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10)
        val weights = largeWeights.size
        val weight = largeWeights[random(weights)]
        return ((random(max.toFloat()) /100.toFloat()) * weight).toInt()
    }

    //todo - there must be a better way
    fun randomWeightedSmall(max: Int): Int{
        val smallWeights = intArrayOf(10, 20, 20, 30, 30, 30, 40, 40, 40, 40, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 80, 80, 80, 80, 80, 80, 80, 80, 90, 90, 90, 90, 90, 90, 90, 90, 90)
        val weights = smallWeights.size
        val weight = smallWeights[random(weights)]
        return ((random(max.toFloat()) /100.toFloat()) * weight).toInt()
    }

    //Geometry

    data class Coord(var x: Float, var y: Float){

        constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())

        fun dist(coord: Coord): Float {
            val dx = this.x - coord.x
            val dy = this.y - coord.y
            return Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
        }
    }

    fun randomCircleCoord(radius: Number): Coord {
        val a = random(0.toFloat(), 1.toFloat()) * 2 * PConstants.PI
        val r = radius.toFloat() * sqrt(random(0.toFloat(), 1.toFloat()))
        val x = r * cos(a)
        val y = r * sin(a)
        return Coord(x, y)
    }
}